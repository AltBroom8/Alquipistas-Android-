package com.example.alquipistas;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterDerecha extends RecyclerView.Adapter<AdapterDerecha.ViewHolder>{

    List<Escuela> listado;
    ApiRest myapi;
    String username;
    // Constructor

    public AdapterDerecha(String username) {
        this.myapi = new ApiRest();
        this.username = username;
    }

    // Método que infla el layout de cada elemento y retorna su ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2, parent, false);
        return new ViewHolder(view);
    }

    // Método que asigna los datos a cada elemento
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Escuela escuela = listado.get(position);
        holder.titulo.setText(escuela.getNombre());
        holder.categoria.setText(escuela.getNombre_categoria());
        holder.fecha.setText(formatDate(escuela.getInicio_inscripcion()));
        holder.fecha2.setText(formatDate(escuela.getFin_inscripcion()));
        holder.boton.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            listado.remove(pos);
            notifyItemRemoved(pos);
            myapi.inscribeUser(escuela.getId(), username);
        });

    }
    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatter.format(date);
    }
    public void updateData(List<Escuela> nuevasEscuelas) {
        if (nuevasEscuelas != null) {
            this.listado = nuevasEscuelas;
            Log.d("LLENAAAA","LA LISTA NO ES NULL ");
        } else {
            this.listado = new ArrayList<Escuela>(); // Si es nulo, asigna una lista vacía
            Log.d("VACIAAAA","LA LISTA ES NULL ");
        }
    }

    // Retorna la cantidad de elementos en el dataset
    @Override
    public int getItemCount() {
        return listado.size();
    }

    // ViewHolder que representa cada elemento en la RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, categoria,fecha,fecha2;
        ImageButton boton;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo2);
            categoria = itemView.findViewById(R.id.categoria);
            fecha = itemView.findViewById(R.id.date1);
            fecha2 = itemView.findViewById(R.id.date2);
            boton = itemView.findViewById(R.id.imageButton2);
        }
    }
}