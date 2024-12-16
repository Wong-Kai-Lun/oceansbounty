package com.example.oceansbounty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface RequestData {

    @GET("api/Reservations/{id}")
    Call<Reservation> getAccounts(@Path("id") int id);

    @GET("api/Reservations")
    Call<List<Reservation>> getAllData();

    @POST("api/Reservations")
    Call<Reservation> createAccount(@Body PostUserRequest newAccount);

    @PATCH("api/Reservations/{id}")
    Call<Reservation> putBooking(
            @Path("id") int id,
            @Body PatchBookingRequest newBooking);

    @PATCH("api/Reservations/{id}")
    Call<Reservation> createBooking(
            @Path("id") int id,
            @Body PatchBookingRequest newBooking);

    @DELETE("api/Reservations/{id}")
    Call<Void> deleteAccount(@Path("id") int id);

}