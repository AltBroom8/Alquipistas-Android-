package com.example.alquipistas;

import androidx.core.util.Consumer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MedioViewModel extends ViewModel {
    private ApiRest apiRest;
    private MutableLiveData<List<Escuela>> escuelasLiveData = new MutableLiveData<>();

    public void init(ApiRest apiRest, String username) {
        this.apiRest = apiRest;
        cargarEscuelas(username);
    }

    public void cargarEscuelas(String username) {
        apiRest.getEscuelas(username, new Consumer<List<Escuela>>() {
            @Override
            public void accept(List<Escuela> escuelas) {
                escuelasLiveData.setValue(escuelas);
            }
        });
    }

    public LiveData<List<Escuela>> getEscuelasLiveData() {
        return escuelasLiveData;
    }

}
