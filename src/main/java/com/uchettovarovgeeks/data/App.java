package com.uchettovarovgeeks.data;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.jsonModel.JsonGetter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App INSTANCE;

    private static JsonGetter apiHolder;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.UrlToServer))
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build();
        try {
            apiHolder = retrofit.create(JsonGetter.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonGetter getApiHolder() {
        return apiHolder;
    }

    public static Context getInstance() {
        return INSTANCE;
    }

    private Gson gson() {
        return new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).excludeFieldsWithoutExposeAnnotation().create();
    }

}
