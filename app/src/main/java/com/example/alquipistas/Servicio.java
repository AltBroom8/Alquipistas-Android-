package com.example.alquipistas;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Servicio {
    //POSIBLES CONSULTAS POR POST A LA API

    @POST("/existeSocioPorUsername")
    Call<Boolean>CompruebaUser(@Body JsonObject datos);

    @POST ("/escuelasPorUsuario")
    Call<List<Escuela>> getEscuelas(@Body JsonObject datos);

    @POST("/registroMovil")
    Call<Boolean> registro(@Body JsonObject datos);

    @POST("/quitarEscuela")
    Call<Boolean>quitarEscuela(@Body JsonObject datos);

    @POST ("/listadoInscripciones")
    Call<List<Escuela>> getInscripciones(@Body JsonObject datos);

    @POST("/inscribeUser")
    Call<Boolean>inscribeUser(@Body JsonObject datos);

    @POST("/getSocioPorUsername")
    Call<Socio>getSocio(@Body JsonObject datos);

    @POST("/updateSocioMovil")
    Call<Boolean>updateUser(@Body JsonObject datos);


}
