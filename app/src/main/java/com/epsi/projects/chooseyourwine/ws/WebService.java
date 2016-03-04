package com.epsi.projects.chooseyourwine.ws;

import com.epsi.projects.chooseyourwine.beans.Product;
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Benjamin on 03/03/2016.
 * Interface d'accès au web service
 */
public interface WebService {

    @GET("/code/{code}.json")
    Call<ProductWS> getProduct(@Path("code") String code);

}
