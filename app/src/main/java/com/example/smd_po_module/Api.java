package com.example.smd_po_module;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "http://webhook.site/";

    @GET("2031e2bc-dd1d-4ac6-9ab4-17a9ef182a4b")
    Call<List<Hero>> getData();
}
