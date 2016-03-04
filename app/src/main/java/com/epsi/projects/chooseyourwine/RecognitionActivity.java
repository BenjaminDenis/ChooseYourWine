package com.epsi.projects.chooseyourwine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.epsi.projects.chooseyourwine.activities.WineDetailActivity;
import com.epsi.projects.chooseyourwine.activities.ListWinesActivity;
import com.epsi.projects.chooseyourwine.beans.Product;
import com.epsi.projects.chooseyourwine.ws.ApiClient;
import com.epsi.projects.chooseyourwine.ws.ProductWS;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RecognitionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "Logs recognition";
    private ApiClient mApiClient;
    private ProductWS mProductWS;
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition);

        // Launch activity on click
        findViewById(R.id.read_barcode).setOnClickListener(this);
        findViewById(R.id.show_wines).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Log.v(TAG, "Launch barcode capture");
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        } else if (v.getId() == R.id.show_wines) {
            // launch wines list
            Log.v(TAG, "Launch wines list");
            Intent intent = new Intent(this, ListWinesActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Default launcher
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);

                    // Message logs
                    Log.v(TAG, "Barcode value : " + barcode.displayValue);

                    // Get product
                    mProduct = new Product();
                    mApiClient = new ApiClient(this);
                    fetchProduct(barcode.displayValue);
                } else {
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Get even from the API, given the id
     *
     * @param barcode Id to look for
     */
    private void fetchProduct(final String barcode) {
        final Context context = this;
        mApiClient.getProduct("8715297180695").enqueue(new Callback<ProductWS>() {

            @Override
            public void onResponse(Response<ProductWS> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // Get product infos
                    mProductWS = response.body();
                    ArrayList<JsonElement> gg = mProductWS.getProduct();
                    try {
                        JsonObject productJson = gg.get(0).getAsJsonObject();

                        String name = productJson.get("product_name").getAsString();
                        String code = productJson.get("code").getAsString();
                        String img = productJson.get("image_front_url").getAsString();
                        String imgSmall = productJson.get("image_front_small_url").getAsString();
                        mProduct.setName(name);
                        mProduct.setBarcode(code);
                        mProduct.setImgUrl(img);
                        mProduct.setImgUrlSmall(imgSmall);

                        Log.v(TAG, "Product ok : " + mProduct.getName());
                        Intent intent = new Intent(context, WineDetailActivity.class);
                        intent.putExtra("product", mProduct);
                        startActivity(intent);

                    } catch (Exception e) {
                        Log.v(TAG, "Product not ok : " + e.getLocalizedMessage());
                    }
                } else {
                    Log.e(TAG, String.format("error finding %s event", barcode));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, String.format("Failure when getting event: %s", t.getLocalizedMessage()));
            }
        });
    }
}
