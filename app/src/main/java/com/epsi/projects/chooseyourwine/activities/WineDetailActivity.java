package com.epsi.projects.chooseyourwine.activities;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epsi.projects.chooseyourwine.ImageLoader;
import com.epsi.projects.chooseyourwine.R;
import com.epsi.projects.chooseyourwine.beans.Product;
import com.epsi.projects.chooseyourwine.ws.ProductWS;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class WineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_detail);

        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("product");


        ImageView view = (ImageView)findViewById(R.id.product_img);
        new ImageLoader(view).execute(p.getImgUrl());

        TextView name = (TextView) findViewById(R.id.product_name);
        name.setText(p.getName());

        TextView code = (TextView) findViewById(R.id.product_code);
        code.setText(p.getBarcode());
        String[] items = { "Boeuf bourguignon", "Bigmac"};
        ListView list = (ListView) findViewById(R.id.meal_list);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }
}
