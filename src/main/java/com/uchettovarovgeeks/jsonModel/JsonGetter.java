package com.uchettovarovgeeks.jsonModel;

import com.uchettovarovgeeks.jsonModel.auth.Token;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.jsonObjects.NewItem;
import com.uchettovarovgeeks.jsonModel.responses.ResponseCategories;
import com.uchettovarovgeeks.jsonModel.responses.ResponseItemByID;
import com.uchettovarovgeeks.jsonModel.responses.ResponseItemsList;
import com.uchettovarovgeeks.jsonModel.responses.ResponseStores;
import com.uchettovarovgeeks.jsonModel.responses.ResponseSuppliers;
import com.uchettovarovgeeks.jsonModel.responses.ResponseUnits;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonGetter {

    @POST("api/v1/auth/login")
    Call<Token> getToken(@Body Map<String, String> options);

    @GET("api/v1/items/{id}")
    Call<ResponseItemByID> loadItem(@Header("Authorization") String token, @Path("id") int ID);

    @GET("api/v1/units")
    Call<ResponseUnits> loadUnits(@Header("Authorization") String token);

    @GET("api/v1/categories")
    Call<ResponseCategories> loadCategories(@Header("Authorization") String token);

    @GET("api/v1/suppliers")
    Call<ResponseSuppliers> loadSuppliers(@Header("Authorization") String token);

    @GET("api/v1/items")
    Call<ResponseItemsList> loadAll(@Header("Authorization") String token, @Query("page") int page, @Query("size") int size);

    @GET("api/v1/storehouses")
    Call<ResponseStores> loadStores(@Header("Authorization") String token);

    @POST("api/v1/items")
    Call<Item> newItem(@Header("Authorization") String token, @Body NewItem item);

    @PUT("api/v1/items/{id}")
    Call<Item> updateItem(@Header("Authorization") String token, @Path("id") int ID, @Body NewItem item);

    @DELETE("api/v1/items/{id}")
    Call<Item> delItem(@Header("Authorization") String token, @Path("id") int ID);

}
