package com.example.oceansbounty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface RequestData {

    @GET("api/Reservations/{uid}")
    Call<Reservation> getData(@Path("uid") int uid);
}

