package com.uchettovarovgeeks.ui.list;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.uchettovarovgeeks.data.App;
import com.uchettovarovgeeks.ui.MainActivity;
import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.data.LoginRepository;
import com.uchettovarovgeeks.data.AuthData;
import com.uchettovarovgeeks.jsonModel.JsonGetter;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.responses.ResponseCategories;
import com.uchettovarovgeeks.jsonModel.responses.ResponseData;
import com.uchettovarovgeeks.jsonModel.responses.ResponseItemByID;
import com.uchettovarovgeeks.jsonModel.responses.ResponseItemsList;
import com.uchettovarovgeeks.jsonModel.responses.ResponseStores;
import com.uchettovarovgeeks.jsonModel.responses.ResponseSuppliers;
import com.uchettovarovgeeks.jsonModel.responses.ResponseUnits;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private RecyclerView recyclerViewItemList;
    private Item[] items;
    private JsonGetter apiholder = App.getApiHolder();
    private AuthData authData;
    private int currentPage;

    Button buttonNext;
    Button buttonPrev;
    TextView textViewPages;
    Snackbar snackbar;
    Boolean nextAvailable = false;
    Boolean prevAvailable = false;
    FloatingActionButton fabAdd;

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).setCurrentPage(currentPage);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentPage = ((MainActivity) getActivity()).getCurrentPage();
        getData(false);
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authData = LoginRepository.getInstance().getAuthData();

        fabAdd = view.findViewById(R.id.fabadd);
        if (!authData.getAdmin()) {
            fabAdd.setVisibility(View.INVISIBLE);
        }

        fabAdd.setOnClickListener(v -> {
            ((MainActivity) getActivity()).setCurrentItem(
                    new Item());

            NavHostFragment.findNavController(ListFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        recyclerViewItemList = view.findViewById(R.id.recycledViewProductList);
        recyclerViewItemList.setHasFixedSize(true);
        recyclerViewItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        items = new Item[0];
        ItemListAdapter ila = new ItemListAdapter(items, LayoutInflater.from(getContext()));

        recyclerViewItemList.setAdapter(ila);
        ila.SetOnItemClickListener(new ItemListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Item item) {
                loadAndSetCurrentItem(item, false, false);
            }

            @Override
            public void onItemClickQts(View v, Item item) {
                loadAndSetCurrentItem(item, false, true);
            }
        });

        recyclerViewItemList.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                if (velocityY > 0) {
                    if (nextAvailable) {
                        currentPage++;
                        getData(false);
                    }

                } else {
                    if (prevAvailable) {
                        currentPage--;
                        getData(false);
                    }
                }
                return true;
            }
        });

        buttonNext = view.findViewById(R.id.button_next);
        buttonNext.setOnClickListener(v -> {
            currentPage++;
            getData(false);
        });

        buttonPrev = view.findViewById(R.id.button_prev);
        buttonPrev.setOnClickListener(v -> {
            currentPage--;
            getData(false);
        });

        textViewPages = view.findViewById(R.id.textViewPages);
        snackbar = Snackbar.make(view, "", BaseTransientBottomBar.LENGTH_LONG);

        getCatalogs(false);
    }

    private void getCatalogs(boolean authStarted) {
        apiholder.loadStores("Bearer " + authData.getToken().getToken()).enqueue(new Callback<ResponseStores>() {
            @Override
            public void onResponse(Call<ResponseStores> call, Response<ResponseStores> response) {
                if (response.body() != null) {
                    ((MainActivity) getActivity()).setStorehouses(response.body().getStorehouse());
                    ((MainActivity) getActivity()).generateStringArrays("store");
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        getCatalogs(true);
                    } else {
                        snackbar.setText(getString(R.string.GettingDataError)).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseStores> call, Throwable t) {
                t.printStackTrace();
            }
        });

        apiholder.loadUnits("Bearer " + authData.getToken().getToken()).enqueue(new Callback<ResponseUnits>() {
            @Override
            public void onResponse(Call<ResponseUnits> call, Response<ResponseUnits> response) {
                if (response.body() != null) {
                    ((MainActivity) getActivity()).setUnits(response.body().getUnits());
                    ((MainActivity) getActivity()).generateStringArrays("unit");
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        getCatalogs(true);
                    } else {
                        snackbar.setText(getString(R.string.GettingDataError)).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUnits> call, Throwable t) {
                t.printStackTrace();
            }
        });
        apiholder.loadCategories("Bearer " + authData.getToken().getToken()).enqueue(new Callback<ResponseCategories>() {
            @Override
            public void onResponse(Call<ResponseCategories> call, Response<ResponseCategories> response) {
                if (response.body() != null) {
                    ((MainActivity) getActivity()).setCategories(response.body().getCategories());
                    ((MainActivity) getActivity()).generateStringArrays("category");
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        getCatalogs(true);
                    } else {
                        snackbar.setText(getString(R.string.GettingDataError) + 1).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCategories> call, Throwable t) {
                t.printStackTrace();
            }
        });
        apiholder.loadSuppliers("Bearer " + authData.getToken().getToken()).enqueue(new Callback<ResponseSuppliers>() {
            @Override
            public void onResponse(Call<ResponseSuppliers> call, Response<ResponseSuppliers> response) {
                if (response.body() != null) {
                    ((MainActivity) getActivity()).setSuppliers(response.body().getSuppliers());
                    ((MainActivity) getActivity()).generateStringArrays("supplier");
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        getCatalogs(true);

                    } else {
                        snackbar.setText(getString(R.string.GettingDataError) + 2).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSuppliers> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void getData(Boolean authStarted) {
        apiholder.loadAll("Bearer " + authData.getToken().getToken(), currentPage, getResources().getInteger(R.integer.valueItemsOnPage)).enqueue(new Callback<ResponseItemsList>() {

            @Override
            public void onResponse(Call<ResponseItemsList> call, Response<ResponseItemsList> response) {
                if (response.body() != null) {
                    ResponseData responseData = response.body().getResponseData();
                    ((ItemListAdapter) recyclerViewItemList.getAdapter()).setListItems(responseData.getItems());
                    recyclerViewItemList.getAdapter().notifyDataSetChanged();
                    nextAvailable = currentPage == (responseData.getTotalPages() - 1) ? false : true;
                    prevAvailable = currentPage == 0 ? false : true;

                    buttonPrev.setVisibility(prevAvailable ? View.VISIBLE : View.INVISIBLE);
                    buttonNext.setVisibility(nextAvailable ? View.VISIBLE : View.INVISIBLE);
                    textViewPages.setText(responseData.getNumber() + 1 + "/" + responseData.getTotalPages());
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        getData(true);
                    } else {
                        snackbar.setText(getString(R.string.GettingDataError) + 3).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseItemsList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadAndSetCurrentItem(Item item, boolean authStarted, Boolean store) {

        apiholder.loadItem("Bearer " + authData.getToken().getToken(), item.getId()).enqueue(new Callback<ResponseItemByID>() {
            @Override
            public void onResponse(Call<ResponseItemByID> call, Response<ResponseItemByID> response) {
                if (response.body() != null) {
                    ((MainActivity) getActivity()).setCurrentItem(response.body().getItem());
                    if (store) {
                        NavHostFragment.findNavController(ListFragment.this)
                                .navigate(R.id.action_FirstFragment_to_ThirdFragment);
                    } else {
                        NavHostFragment.findNavController(ListFragment.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment);
                    }
                } else {
                    if ((response.raw().code() == 403) || (response.raw().code() == 401)) {
                        if (!authStarted) {
                            ((MainActivity) getActivity()).reAuthUser();
                        }
                        loadAndSetCurrentItem(item, true, store);

                    } else {
                        snackbar.setText(getString(R.string.GettingDataError) + " " + response.raw().code()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseItemByID> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}