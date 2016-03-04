package com.epsi.projects.chooseyourwine.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Benjamin on 21/01/2016.
 * Product object
 */
public class Product implements Serializable {

    // Attributes
    private String barcode;
    private String name;
    private String imgUrl;
    private float notation;
    private String imgUrlSmall;

    /**
     * Blank product's constructor
     */
    public Product() {
        super();
    }

    /**
     * Product's constructor
     * @param barcode : Product's barcode
     * @param name : Product's name
     * @param imgUrl : Product's image url
     * @param notation : Product's notation between 0 & 5 (stars)
     */
    public Product(String barcode, String name, String imgUrl, float notation) {
        super();
        this.barcode = barcode;
        this.name = name;
        this.imgUrl = imgUrl;
        this.notation = notation;
        this.imgUrlSmall = imgUrlSmall;
    }

    public String getImgUrlSmall() {
        return imgUrlSmall;
    }

    public void setImgUrlSmall(String imgUrlSmall) {
        this.imgUrlSmall = imgUrlSmall;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getNotation() {
        return notation;
    }

    public void setNotation(float notation) {
        this.notation = notation;
    }
}
