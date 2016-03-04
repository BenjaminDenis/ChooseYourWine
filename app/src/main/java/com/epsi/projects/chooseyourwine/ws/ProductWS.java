package com.epsi.projects.chooseyourwine.ws;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductWS {

    // Serialize names
    public static final String PAGE_SIZE = "page_size";
    public static final String COUNT = "count";
    public static final String SKIP = "skip";
    public static final String PAGE = "page";
    public static final String PRODUCTS = "products";

    // Attributes
    @SerializedName(PAGE_SIZE)
    @Expose
    private int pageSize;
    @SerializedName(COUNT)
    @Expose
    private String count;
    @SerializedName(SKIP)
    @Expose
    private int skip;
    @SerializedName(PAGE)
    @Expose
    private int page;
    @SerializedName(PRODUCTS)
    @Expose
    private ArrayList<JsonElement> products;

    public ProductWS() {
        super();
    }

    public ProductWS(int pageSize, String count, int skip, int page, ArrayList<JsonElement> products) {
        super();
        this.pageSize = pageSize;
        this.count = count;
        this.skip = skip;
        this.page = page;
        this.products = products;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<JsonElement> getProduct() {
        return products;
    }

    public void setProduct(ArrayList<JsonElement> products) {
        this.products = products;
    }

}
