package com.epsi.projects.chooseyourwine.ws;

import android.content.Context;
import android.util.Log;

import com.epsi.projects.chooseyourwine.Common;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.joda.time.DateTime;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Benjamin on 03/03/2016.
 * Class de l'API, pour l'accès au ws
 */
public class ApiClient {

    public static final String TAG = "Logs recognition";

    private static WebService mWs;

    /**
     * Constructeur de l'API
     * @param ctx : contexte en cours
     */
    public ApiClient(Context ctx) {
        if (mWs == null) {
            OkHttpClient okHttpClient = new OkHttpClient();

            // Build API Client
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Common.API_END_POINT)
                    .addConverterFactory(
                            GsonConverterFactory
                                    .create(
                                            new GsonBuilder()
                                                    .registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
                                                    .create()
                                    )
                    )
                    .client(okHttpClient)
                    .build();
            mWs = retrofit.create(WebService.class);
            Log.v(TAG, "WS connection ok");
        }
    }

    /**
     * Récupère un produit via son code
     * @param code
     * @return Call
     */
    public Call<ProductWS> getProduct(String code) {
        Log.v(TAG, "getProduct");
        return mWs.getProduct(code);
    }

    /**
     * Récupère une liste de produits via une categorie
     * @param category
     * @return Call
     */
    public Call<ProductWS> getProducts(String category) {
        Log.v(TAG, "getProducts");
        return mWs.getProducts(category);
    }
}
