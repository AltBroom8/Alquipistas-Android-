package com.example.alquipistas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class Medio extends Fragment {
    ApiRest myapi;
    TextView tituloMedio;
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rv;
    private MedioViewModel viewModel;
    private AdapterCentral adapter;
    String username;
    public Medio(ApiRest api,String username){
        this.myapi = api;
        this.username = username;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medio, container, false);
        tituloMedio = view.findViewById(R.id.titulo);
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterCentral(new ArrayList<>());
        // Inicializar el ViewModel
        viewModel = new ViewModelProvider(this).get(MedioViewModel.class);
        // Inicializar y observar LiveData
        viewModel.getEscuelasLiveData().observe(getViewLifecycleOwner(), escuelas -> {
            if (escuelas != null) {
                Log.d("LISTA", "La lista no está vacía");
                for (Escuela e : escuelas) {
                    Log.d("LISTA", e.getNombre());
                }
                adapter = new AdapterCentral(escuelas);
                rv.setAdapter(adapter);
                // Actualizar el adaptador con los nuevos datos
            } else {
                Log.d("ERROR DE LISTA", "La lista es null");
                adapter.updateData(new ArrayList<>());
                rv.setAdapter(adapter);// Actualizar el adaptador con lista vacía si es null
            }
            swipeRefreshLayout.setRefreshing(false);
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

         // Asegúrate de que esto sea correcto
        viewModel.init(myapi, username); // Reemplaza "usuario123" con el username correspondiente

        return view;
    }

    private void refreshData() {
        viewModel.cargarEscuelas(username);
    }

}
