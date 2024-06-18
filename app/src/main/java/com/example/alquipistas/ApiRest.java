package com.example.alquipistas;

import android.util.Log;

import androidx.core.util.Consumer;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

//METODO PARA REGISTRAR UN NUEVO USUARIO
    public void registro(String nombre,String usuario,String email,final Consumer<Boolean> callback){
        JsonObject json = new JsonObject();
        json.addProperty("nombre", nombre);
        json.addProperty("username", usuario);
        json.addProperty("email", email);

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
    public void getEscuelas(String username,final Consumer<List<Escuela>> callback){
        JsonObject json = new JsonObject();
        json.addProperty("username", username);

        Call<List<Escuela>> call = servicio.getEscuelas(json);
        call.enqueue(new Callback<List<Escuela>>() {
            @Override
            public void onResponse(Call<List<Escuela>> call, Response<List<Escuela>>response) {
                if (response.isSuccessful()) {
                    List<Escuela> cursos = response.body();
                    callback.accept(cursos);
                } else {
                    Log.d("ApiRest", "Error en el email arriba: " + response.message());
                    callback.accept(null);

                }
            }
            @Override
            public void onFailure(Call<List<Escuela>> call, Throwable t) {
                Log.d("ApiRest", "Error en el email abajo: " + t.getMessage());
                callback.accept(null);

            }
        });
    }

    public void quitarEscuela(int idEscuela, int idUser){
        JsonObject json = new JsonObject();
        json.addProperty("idEscuela", idEscuela);
        json.addProperty("idUser" , idUser);

        Call<Boolean> call = servicio.quitarEscuela(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean userLibre = response.body();
                    Log.d("ApiRest", "Escuela eliminada es: " + userLibre);

                } else {
                    Log.d("ApiRest", "Error en la escuela arriba: " + response.message());

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en el user abajo: " + t.getMessage());
            }
        });
    }

    public void getInscripciones(String username,final Consumer<List<Escuela>> callback){
        JsonObject json = new JsonObject();
        json.addProperty("username", username);

        Call<List<Escuela>> call = servicio.getInscripciones(json);
        call.enqueue(new Callback<List<Escuela>>() {
            @Override
            public void onResponse(Call<List<Escuela>> call, Response<List<Escuela>>response) {
                if (response.isSuccessful()) {
                    List<Escuela> cursos = response.body();
                    callback.accept(cursos);
                } else {
                    Log.d("ApiRest", "Error en la inscripcion arriba: " + response.message());
                    callback.accept(null);

                }
            }
            @Override
            public void onFailure(Call<List<Escuela>> call, Throwable t) {
                Log.d("ApiRest", "Error en la inscripcion abajo: " + t.getMessage());
                callback.accept(null);

            }
        });
    }

    public void inscribeUser(int idEscuela, String username){
        JsonObject json = new JsonObject();
        json.addProperty("escuelaId", idEscuela);
        json.addProperty("user" , username);

        Call<Boolean> call = servicio.inscribeUser(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean userLibre = response.body();
                    Log.d("ApiRest", "Inscripcion es: " + userLibre);

                } else {
                    Log.d("ApiRest", "Error en la inscripcion arriba: " + response.message());

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en la inscripcion abajo: " + t.getMessage());
            }
        });
    }

    public void getSocio(String username,final Consumer<Socio> callback){
        JsonObject json = new JsonObject();
        json.addProperty("username" , username);

        Call<Socio> call = servicio.getSocio(json);
        call.enqueue(new Callback<Socio>() {
            @Override
            public void onResponse(Call<Socio> call, Response<Socio>response) {
                if (response.isSuccessful()) {
                    Socio socio = response.body();
                    callback.accept(socio);
                } else {
                    Log.d("ApiRest", "Error en la inscripcion arriba: " + response.message());
                    callback.accept(null);

                }
            }
            @Override
            public void onFailure(Call<Socio> call, Throwable t) {
                Log.d("ApiRest", "Error en la inscripcion abajo: " + t.getMessage());
                callback.accept(null);

            }
        });
    }

    public void updateUser(String nombre,String apellidos,int cp,String email, Date date,int id){
        JsonObject json = new JsonObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = dateFormat.format(date);
        json.addProperty("nombre", nombre);
        json.addProperty("apellidos" , apellidos);
        json.addProperty("cp", cp);
        json.addProperty("email" , email);
        json.addProperty("date", dateString);
        json.addProperty("id", id);

        Call<Boolean> call = servicio.updateUser(json);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean userLibre = response.body();
                    Log.d("ApiRest", "Update es: " + userLibre);

                } else {
                    Log.d("ApiRest", "Error en la update arriba: " + response.message());

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ApiRest", "Error en la update abajo: " + t.getMessage());
            }
        });
    }






}
