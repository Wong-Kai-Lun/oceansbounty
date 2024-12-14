package com.example.oceansbounty;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    // follow chatgpt's example for instantiating
    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

//https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();