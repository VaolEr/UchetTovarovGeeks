package com.uchettovarovgeeks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.data.App;
import com.uchettovarovgeeks.data.LoginRepository;
import com.uchettovarovgeeks.data.AuthData;
import com.uchettovarovgeeks.jsonModel.JsonGetter;
import com.uchettovarovgeeks.jsonModel.auth.Token;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Category;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Storehouse;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Supplier;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Unit;
import com.uchettovarovgeeks.ui.authentefication.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private int currentPage;
    private Item currentItem;
    private Toolbar toolbar;
    private Unit[] units;
    private String[] unitsString;
    private Category[] categories;
    private String[] categoriesString;
    private Supplier[] suppliers;
    private String[] suppliersString;
    private Storehouse[] storehouses;
    private String[] storehousesString;
    private JsonGetter apiholder = App.getApiHolder();
    private AuthData authData = LoginRepository.getInstance().getAuthData();

    public Toolbar getToolbar() {
        return toolbar;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }


    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Supplier[] getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Supplier[] suppliers) {
        this.suppliers = suppliers;
    }

    public String[] getUnitsString() {
        return unitsString;
    }

    public void setUnitsString(String[] unitsString) {
        this.unitsString = unitsString;
    }

    public String[] getCategoriesString() {
        return categoriesString;
    }

    public void setCategoriesString(String[] categoriesString) {
        this.categoriesString = categoriesString;
    }

    public String[] getSuppliersString() {
        return suppliersString;
    }

    public void setSuppliersString(String[] suppliersString) {
        this.suppliersString = suppliersString;
    }

    public Storehouse[] getStorehouses() {
        return storehouses;
    }

    public void setStorehouses(Storehouse[] storehouses) {
        this.storehouses = storehouses;
    }

    public String[] getStorehousesString() {
        return storehousesString;
    }

    public void setStorehousesString(String[] storehousesString) {
        this.storehousesString = storehousesString;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeBlue);

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        units = new Unit[0];
        categories = new Category[0];
        suppliers = new Supplier[0];
        currentPage = 0;

    }

    @Override
    public void onBackPressed() {
        if ((navController.getCurrentDestination().toString().contains("Item")) || (navController.getCurrentDestination().toString().contains("Store"))) {
            navController.navigate(R.id.action_SecondFragment_to_FirstFragment);
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void generateStringArrays(String array) {
        switch (array) {
            case "store":
                storehousesString = new String[storehouses.length];
                for (int i = 0; i < storehouses.length; i++) {
                    storehousesString[i] = storehouses[i].getName();
                }
                break;
            case "unit":
                unitsString = new String[units.length];
                for (int i = 0; i < units.length; i++) {
                    unitsString[i] = units[i].getName();
                }
                break;
            case "supplier":
                suppliersString = new String[suppliers.length];
                for (int i = 0; i < suppliers.length; i++) {
                    suppliersString[i] = suppliers[i].getName();
                }
                break;
            case "category":
                categoriesString = new String[categories.length];
                for (int i = 0; i < categories.length; i++) {
                    categoriesString[i] = categories[i].getName();
                }
                break;
        }

    }

    public void reAuthUser() {

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("email", authData.getEmail());
        hashMap.put("password", authData.getPassword());

        Call<Token> getToken = apiholder.getToken(hashMap);
        getToken.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                if (response.body() != null) {
                    authData.setToken((Token) response.body());
                } else {
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.GettingDataError + 4, Toast.LENGTH_LONG).show();
            }
        });
    }
}