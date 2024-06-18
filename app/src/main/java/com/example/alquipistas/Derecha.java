package com.example.alquipistas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class Derecha extends Fragment {
    ApiRest myapi;
    RecyclerView rv;
    String username;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterDerecha adapter;
    private DerechaViewModel viewModel;
    public Derecha(ApiRest api,String username){
        this.myapi = api;
        this.username = username;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_der, container, false);
        rv = view.findViewById(R.id.rv2);
        swipeRefreshLayout = view.findViewById(R.id.refresh2);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(DerechaViewModel.class);
        Log.d(" ENTRA","ENTRA EN LA DERECHA");
        adapter = new AdapterDerecha(username);
        viewModel.getInscripcionesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Escuela>>() {
            @Override
            public void onChanged(List<Escuela> escuelas) {
                // Actualizar la UI con los datos obtenidos
                if (escuelas != null) {
                    Log.d(" LISTA","LA LISTA no ESTA VACIA");
                    for (Escuela e:escuelas) {
                        Log.d(" LISTA",e.getNombre());
                    }
                    if (adapter == null) {
                        adapter = new AdapterDerecha(username);
                        adapter.updateData(escuelas);
                        rv.setAdapter(adapter);

                    } else {
                        adapter.updateData(escuelas);
                        rv.setAdapter(adapter);
                    }
                } else {
                    // Manejar caso de datos nulos o error
                    Log.d("ERROR DE LISTA","LA LISTA ESTA VACIA");
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Acciones cuando se activa el gesto de deslizamiento para refrescar
                refreshData();
            }
        });


        viewModel.init(myapi, username);
        return view;
    }
    private void refreshData() {
        viewModel.cargarInscripciones(username);
    }
}
