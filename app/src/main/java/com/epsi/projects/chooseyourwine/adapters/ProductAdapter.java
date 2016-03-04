package com.epsi.projects.chooseyourwine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epsi.projects.chooseyourwine.ImageLoader;
import com.epsi.projects.chooseyourwine.R;
import com.epsi.projects.chooseyourwine.beans.Product;
import com.epsi.projects.chooseyourwine.holders.ProductViewHolder;

import java.util.List;

/**
 * Created by Benjamin on 04/03/2016.
 * Adapter pour la liste des produits
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_row_layout,parent, false);
        }

        ProductViewHolder viewHolder = (ProductViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ProductViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.product_name);
            viewHolder.code = (TextView) convertView.findViewById(R.id.product_code);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.product_img);
            convertView.setTag(viewHolder);
        }

        // On récupère l'item à la position donnée
        Product product = getItem(position);

        // On rempli la vue
        if (product.getName().isEmpty())
            viewHolder.name.setText("Unknown");
        else
            viewHolder.name.setText(product.getName());
        viewHolder.code.setText(product.getBarcode());
        new ImageLoader(viewHolder.image).execute(product.getImgUrl());

        return convertView;
    }

}
