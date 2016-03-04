package com.epsi.projects.chooseyourwine.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epsi.projects.chooseyourwine.R;
import com.epsi.projects.chooseyourwine.adapters.ProductAdapter;
import com.epsi.projects.chooseyourwine.beans.Product;
import com.epsi.projects.chooseyourwine.ws.ApiClient;
import com.epsi.projects.chooseyourwine.ws.ProductWS;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Benjamin on 04/03/2016.
 */
public class ListWinesActivity extends AppCompatActivity {

    // Local
    private static final String TAG = "Logs list products";
    public ListView mListView;
    public ApiClient mApiClient;
    public ProductWS mProductWS;
    public ArrayList<Product> listP;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wines_list);
        Log.v(TAG, "Activity wines list processing...");

        // Récupération listView
        mListView = (ListView) findViewById(R.id.listProducts);

        // Récupération de la liste des produits
        mApiClient = new ApiClient(this);
        this.constructListProducts("wines");
    }

    /**
     * Méthode permettant de récupérer une liste de produits en fonction d'une catégorie
     * @param category
     * @return tableau de produits
     */
    public void constructListProducts(final String category) {
        final Context ctx = this;

        // Récupération
        mApiClient.getProducts(category).enqueue(new Callback<ProductWS>() {
            @Override
            public void onResponse(Response<ProductWS> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // Get product infos
                    mProductWS = response.body();
                    ArrayList<JsonElement> productsJSON = mProductWS.getProduct();
                    try {
                        listP = new ArrayList<Product>();

                        // On boucle sur les produits en on les ajoutes au tableau
                        for (int i = 0; i < productsJSON.size(); i++) {
                            JsonObject productJson = productsJSON.get(i).getAsJsonObject();
                            Log.v(TAG, "Product : " + productJson.get("product_name").toString());
                            listP.add(new Product(productJson.get("code").getAsString(), productJson.get("product_name").getAsString(),
                                    productJson.get("image_front_small_url").getAsString(), 0));
                        }
                        Log.v(TAG, "Products list ok : " + listP.size() + " produits trouvés");

                        // Adapter pour la liste
                        ProductAdapter adapter = new ProductAdapter(ctx, listP);
                        mListView.setAdapter(adapter);
                        Log.v(TAG, "Adapter ok");
                    } catch (Exception e) {
                        Log.v(TAG, "Products list not ok : " + e.getLocalizedMessage());
                    }
                } else {
                    Log.e(TAG, String.format("error finding %s category", category));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, String.format("Failure when getting category : %s", t.getLocalizedMessage()));
            }
        });
    }
}
