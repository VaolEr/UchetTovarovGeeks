package com.uchettovarovgeeks.ui.store;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.uchettovarovgeeks.data.App;
import com.uchettovarovgeeks.ui.MainActivity;
import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.data.LoginRepository;
import com.uchettovarovgeeks.data.AuthData;
import com.uchettovarovgeeks.databinding.FragmentStoreBinding;
import com.uchettovarovgeeks.jsonModel.JsonGetter;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.jsonObjects.NewItem;
import com.uchettovarovgeeks.jsonModel.jsonObjects.StorehousesBalance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class StoreFragment extends Fragment {
    Item item;
    private RecyclerView recyclerViewStoreList;
    private FragmentStoreBinding ui;
    private final JsonGetter apiholder = App.getApiHolder();
    private AuthData authData;
    private Snackbar snackbar;
    private StoreAdapter storeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = FragmentStoreBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        item = ((MainActivity) getActivity()).getCurrentItem();
        snackbar = Snackbar.make(view, "", BaseTransientBottomBar.LENGTH_LONG);

        recyclerViewStoreList = ui.recycledViewStoreList;
        recyclerViewStoreList.setHasFixedSize(true);
        recyclerViewStoreList.setLayoutManager(new LinearLayoutManager(getContext()));


        ArrayList<StorehousesBalance> store = new ArrayList<>();
        Collections.addAll(store, item.getStorehousesBalance());

        storeAdapter = new StoreAdapter(store, Arrays.asList(((MainActivity) getActivity()).getStorehouses()),
                (((MainActivity) getActivity()).getStorehousesString()), item);
        recyclerViewStoreList.setAdapter(storeAdapter);

        ui.buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(StoreFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });
        ui.fabaddStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StorehousesBalance> storehousesBalanceList = storeAdapter.getmValues();
                storehousesBalanceList.add(new StorehousesBalance(0, "", 1001));
                storeAdapter = new StoreAdapter(storehousesBalanceList, Arrays.asList(((MainActivity) getActivity()).getStorehouses()),
                        (((MainActivity) getActivity()).getStorehousesString()), item);
                recyclerViewStoreList.setAdapter(storeAdapter);
                storeAdapter.notifyDataSetChanged();
            }
        });
        ((MainActivity) getActivity()).getToolbar().setTitle(item.getName());

        authData = LoginRepository.getInstance().getAuthData();

        if (!authData.getAdmin()) {
            ui.fabSaveStore.setVisibility(View.INVISIBLE);
            ui.fabaddStore.setVisibility(View.INVISIBLE);
        }
        ui.fabSaveStore.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            updateValues();
            saveCurrentItem(false);

        });
    }

    private void updateValues() {
        StorehousesBalance[] storehousesBalance = new StorehousesBalance[storeAdapter.getItemCount()];
        int i = 0;

        for (StorehousesBalance storeBalance : storeAdapter.getmValues()
        ) {
            storehousesBalance[i] = new StorehousesBalance(storeBalance.getQuantity(),
                    storeBalance.getName(), storeBalance.getId());
            i++;
        }
        item.setStorehousesBalance(storehousesBalance);
    }

    private void saveCurrentItem(boolean authStarted) {
        apiholder.updateItem("Bearer " + authData.getToken().getToken(), item.getId(), (NewItem) item).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.body() != null) {
                    snackbar.setText(getString(R.string.item_updated) + " " + response.raw().code()).show();
                    NavHostFragment.findNavController(StoreFragment.this)
                            .navigate(R.id.action_ThirdFragment_to_FirstFragment);
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        saveCurrentItem(true);
                    } else {
                        snackbar.setText(getString(R.string.item_updated) + " " + response.raw().code()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                t.printStackTrace();
                snackbar.setText(getString(R.string.item_updated) + " " + t.getMessage()).show();
            }
        });
    }
}