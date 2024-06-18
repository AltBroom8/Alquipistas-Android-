package com.example.alquipistas;

import androidx.core.util.Consumer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DerechaViewModel extends ViewModel {
    private ApiRest apiRest;
    private MutableLiveData<List<Escuela>> inscripcionesLiveData = new MutableLiveData<>();

    public void init(ApiRest apiRest, String username) {
        this.apiRest = apiRest;
        cargarInscripciones(username);
    }

    public void cargarInscripciones(String username) {
        apiRest.getInscripciones(username, new Consumer<List<Escuela>>() {
            @Override
            public void accept(List<Escuela> escuelas) {
                inscripcionesLiveData.setValue(escuelas);
            }
        });
    }

    public LiveData<List<Escuela>> getInscripcionesLiveData() {
        return inscripcionesLiveData;
    }
}
