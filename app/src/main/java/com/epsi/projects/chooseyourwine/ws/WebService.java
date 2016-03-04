package com.epsi.projects.chooseyourwine.ws;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebService {

    @GET("/code/{code}.json")
    Call<ProductWS> getProduct(@Path("code") String code);

    @GET("/category/{category}.json")
    Call<ProductWS> getProducts(@Path("category") String category);

}
