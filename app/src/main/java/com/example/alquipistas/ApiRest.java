package com.example.alquipistas;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRest {
    //DECLARACIONES
    String baseUrl = "http://10.0.2.2:3000/";
    Retrofit retrofit;
    Servicio servicio;

    //CONSTRUCTOR
    public ApiRest(){
        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        this.servicio = retrofit.create(Servicio.class);
    }
    //METODO PARA TESTING DE LA LIBRERIA RETROFIT
    public void obtenerDatosTest() {
        Call<JsonObject> call = servicio.test();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject resultado = response.body();
                    String msg = resultado.get("mensaje").getAsString();
                    Log.d("ApiRest", "Resultado de la solicitud: " + msg);
                } else {
                    Log.d("ApiRest", "Error en la solicitud: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
               Log.d("ApiError",t.getMessage());

            }
        });
    }
//METODO INICIO SESION
    public void inicioSesion(String usuario, String password,final Consumer<Boolean> callback){
        JsonObject json = new JsonObject();
        json.addProperty("username", usuario);
        json.addProperty("password", password);

        Call<Boolean> call = servicio.login(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean loginSuccessful = response.body();
                    Log.d("ApiRest", "Booleano es: " + loginSuccessful);
                    callback.accept(loginSuccessful);
                } else {
                    Log.d("ApiRest", "Error en el login arriba: " + response.message());
                    callback.accept(false);

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en el login abajo: " + t.getMessage());
                callback.accept(false);

            }
        });
    }
//METODO PARA REGISTRAR UN NUEVO USUARIO
    public void registro(String nombre,String usuario,String email, String password,final Consumer<Boolean> callback){
        JsonObject json = new JsonObject();
        json.addProperty("name", nombre);
        json.addProperty("usuario", usuario);
        json.addProperty("correo", email);
        json.addProperty("pass", password);

        Call<Boolean> call = servicio.registro(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean loginSuccessful = response.body();
                    Log.d("ApiRest", "Booleano es: " + loginSuccessful);
                    callback.accept(loginSuccessful);
                } else {
                    Log.d("ApiRest", "Error en el registro arriba: " + response.message());
                    callback.accept(false);

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en el registro abajo: " + t.getMessage());
                callback.accept(false);

            }
        });
    }
//METODO QUE COMPRUEBA SI UN USERNAME YA ESTA EN LA BBDD
    public void CompruebaUser(String usuario,final Consumer<Boolean> callback){
        JsonObject json = new JsonObject();
        json.addProperty("username", usuario);

        Call<Boolean> call = servicio.CompruebaUser(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean userLibre = response.body();
                    Log.d("ApiRest", "Booleano user es: " + userLibre);
                    callback.accept(userLibre);
                } else {
                    Log.d("ApiRest", "Error en el user arriba: " + response.message());
                    callback.accept(false);

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en el user abajo: " + t.getMessage());
                callback.accept(false);

            }
        });
    }
//METODO QUE COMPRUEBA SI UN MAIL YA ESTA EN LA BBDD
    public void compruebaMail(String email,final Consumer<Boolean> callback){
        JsonObject json = new JsonObject();
        json.addProperty("correo", email);

        Call<Boolean> call = servicio.compruebaMail(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean respuesta = response.body();
                    Log.d("ApiRest", "Booleano email es: " + respuesta);
                    callback.accept(respuesta);
                } else {
                    Log.d("ApiRest", "Error en el email arriba: " + response.message());
                    callback.accept(false);

                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en el email abajo: " + t.getMessage());
                callback.accept(false);

            }
        });
    }
}
