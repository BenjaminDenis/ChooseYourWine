package com.epsi.projects.chooseyourwine.ws;

import android.content.Context;
import android.util.Log;

import com.epsi.projects.chooseyourwine.Common;
import com.epsi.projects.chooseyourwine.beans.Product;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.joda.time.DateTime;

import java.util.List;

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
            Log.v(TAG, "Connexion ok");
        }

    }

    /**
     * Récupère un produit via son id
     * @param code
     * @return Call
     */
    public Call<ProductWS> getProduct(String code) {
        Log.v(TAG, "getProduct");
        return mWs.getProduct(code);
    }
}
