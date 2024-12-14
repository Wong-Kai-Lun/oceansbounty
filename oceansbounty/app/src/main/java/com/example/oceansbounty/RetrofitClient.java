package com.example.oceansbounty;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // follow chatgpt's example for instantiating

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

//https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations