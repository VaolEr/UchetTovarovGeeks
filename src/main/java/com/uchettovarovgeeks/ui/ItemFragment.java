package com.uchettovarovgeeks.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.uchettovarovgeeks.data.App;
import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.data.LoginRepository;
import com.uchettovarovgeeks.data.AuthData;
import com.uchettovarovgeeks.databinding.FragmentItemBinding;
import com.uchettovarovgeeks.jsonModel.JsonGetter;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Category;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.jsonObjects.NewItem;
import com.uchettovarovgeeks.jsonModel.jsonObjects.StorehousesBalance;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Supplier;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Unit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemFragment extends Fragment {

    private Item item;
    private FragmentItemBinding ui;
    private final JsonGetter apiholder = App.getApiHolder();
    private AuthData authData;
    private Snackbar snackbar;

    @Override
    public View onCreateView(
            @NotNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ui = FragmentItemBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ui.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ItemFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        item = ((MainActivity) getActivity()).getCurrentItem();
        ui.editTextSku.setText(item.getSku());
        ui.editTextID.setText("" + item.getId());
        ui.editTextName.setText(item.getName());

        ArrayAdapter<String> adapterUnit = new ArrayAdapter<>(getContext(), R.layout.spiner_raw,
                ((MainActivity) getActivity()).getUnitsString());
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ui.spinnerUnit.setAdapter(adapterUnit);

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(getContext(), R.layout.spiner_raw,
                ((MainActivity) getActivity()).getCategoriesString());
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ui.spinnerCategory.setAdapter(adapterCategory);
        ArrayAdapter<String> adapterSup = new ArrayAdapter<>(getContext(), R.layout.spiner_raw,
                ((MainActivity) getActivity()).getSuppliersString());
        adapterSup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ui.spinnerSupplier.setAdapter(adapterSup);

        if (item.getId() != 0) {
            ui.spinnerUnit.setSelection(adapterUnit.getPosition(item.getUnit().getName()));
            ui.spinnerCategory.setSelection(adapterCategory.getPosition(item.getCategories()[0].getName()));
            ui.spinnerSupplier.setSelection(adapterSup.getPosition(item.getSuppliers().getName()));
        }
        ((MainActivity) getActivity()).getToolbar().setTitle(item.getName());

        snackbar = Snackbar.make(view, "", BaseTransientBottomBar.LENGTH_LONG);
        authData = LoginRepository.getInstance().getAuthData();

        if (!authData.getAdmin()) {
            ui.fabSave.setVisibility(View.INVISIBLE);
        }
        ui.fabSave.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            updateValues();
            if (!ui.editTextID.getText().equals("0")) {
                SaveCurrentItem(false);
            } else {


                StorehousesBalance[] storehousesBalances = new StorehousesBalance[0];
                item.setStorehousesBalance(storehousesBalances);

                SaveCurrentItemAsNew(false);
            }
        });

        ui.fabDel.setOnClickListener(i -> {
            apiholder.delItem("Bearer " + authData.getToken().getToken(), item.getId()).enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    if (response.raw().code() < 300) {
                        Snackbar.make(view, R.string.item_deleted, BaseTransientBottomBar.LENGTH_LONG);
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        NavHostFragment.findNavController(ItemFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FirstFragment);
                    } else {
                        Snackbar.make(view, getString(R.string.GettingDataError) + " " + response.raw().code(), BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    t.printStackTrace();
                    Snackbar.make(view, getString(R.string.GettingDataError) + " " + t.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
                }
            });

        });
    }


    private void updateValues() {
        item.setName(ui.editTextName.getText().toString());
        item.setSku(ui.editTextSku.getText().toString());
        Category[] categories = new Category[1];
        for (Category category : ((MainActivity) getActivity()).getCategories()
        ) {
            if (category.getName().equals(ui.spinnerCategory.getSelectedItem())) {
                categories[0] = category;
            }
        }

        item.setCategories(categories);
        Unit[] units = ((MainActivity) getActivity()).getUnits();
        for (int i = 0; i < units.length; i++) {
            if (units[i].getName().equals(ui.spinnerUnit.getSelectedItem())) {
                item.setUnit(units[i]);
            }
        }

        Supplier[] suppliers = ((MainActivity) getActivity()).getSuppliers();
        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i].getName().equals(ui.spinnerSupplier.getSelectedItem())) {
                item.setSuppliers(suppliers[i]);
            }
        }
    }


    private void SaveCurrentItemAsNew(boolean authStarted) {
        apiholder.newItem("Bearer " + authData.getToken().getToken(), (NewItem) item).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.body() != null) {
                    snackbar.setText(getString(R.string.ItemAdded) + " " + response.raw().code()).show();
                    NavHostFragment.findNavController(ItemFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        SaveCurrentItemAsNew(true);
                    } else {
                        snackbar.setText(getString(R.string.item_updated) + " " + response.raw().code()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                t.printStackTrace();
                snackbar.setText(getString(R.string.GettingDataError) + " " + t.getMessage()).show();
            }
        });
    }

    private void SaveCurrentItem(boolean authStarted) {
        apiholder.updateItem("Bearer " + authData.getToken().getToken(), item.getId(), (NewItem) item).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.body() != null) {
                    snackbar.setText(getString(R.string.item_updated) + " " + response.raw().code()).show();
                    NavHostFragment.findNavController(ItemFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        SaveCurrentItem(true);
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