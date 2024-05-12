package com.example.alquipistas;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Servicio {
    //POSIBLES CONSULTAS POR POST A LA API
    @POST("/login")
    Call<Boolean> login(@Body JsonObject datos);

    @POST("/actualizaPass")
    Call<Void> actualizaPass(@Body String datos);

    @POST("/compruebaUsername")
    Call<Boolean>CompruebaUser(@Body JsonObject datos);

    @POST("/compruebaMail")
    Call<Boolean> compruebaMail(@Body JsonObject datos);

    @POST("/registro")
    Call<Boolean> registro(@Body JsonObject datos);

    @POST("/enviaEmail")
    Call<Boolean> enviaEmail(@Body String datos);
    @POST("/inicializar")
    Call<Boolean> inicializar(@Body String datos);
    @POST("/test")
    Call<JsonObject> test();
}
