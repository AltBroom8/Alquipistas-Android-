package com.example.alquipistas;

import android.annotation.SuppressLint;
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


public class AdapterCentral extends RecyclerView.Adapter<AdapterCentral.ViewHolder>{

    List<Escuela> listado;
    ApiRest myapi;
    // Constructor
    public AdapterCentral(List<Escuela> listado) {
        this.myapi = new ApiRest();
        this.listado = listado;
    }

    // Método que infla el layout de cada elemento y retorna su ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    // Método que asigna los datos a cada elemento
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Escuela escuela = listado.get(position);
        holder.titulo.setText(escuela.getNombre());
        holder.edadMin.setText(String.valueOf(escuela.getEdad_min()));
        holder.edadMax.setText(String.valueOf(escuela.getEdad_max()));
        holder.fecha.setText(formatDate(escuela.getFecha_inicio()));
        holder.boton.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();// Asegúrate de obtener la escuela correcta

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Confirmación")
                    .setMessage("¿Estás seguro de que deseas eliminar esta escuela? Esta acción no se puede deshacer.")
                    .setPositiveButton("Eliminar", (dialog, which) -> {
                        listado.remove(pos);
                        notifyItemRemoved(pos);
                        myapi.quitarEscuela(escuela.getEscuela_id(), escuela.getUsuario_id());
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });

    }
    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatter.format(date);
    }
    public void updateData(List<Escuela> nuevasEscuelas) {
        if (nuevasEscuelas != null){
            this.listado = nuevasEscuelas;
        }else{
            this.listado = new ArrayList<Escuela>();
        }


        notifyDataSetChanged();

        Log.d("AdapterCentral", "Datos actualizados, tamaño del listado: " + this.listado.size());
    }

    // Retorna la cantidad de elementos en el dataset
    @Override
    public int getItemCount() {
        return listado.size();
    }

    // ViewHolder que representa cada elemento en la RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, edadMin,edadMax,fecha;
        ImageButton boton;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo2);
            edadMin = itemView.findViewById(R.id.edadMinima);
            edadMax = itemView.findViewById(R.id.edadMaxima);
            fecha = itemView.findViewById(R.id.date1);
            boton = itemView.findViewById(R.id.imageButton2);
        }
    }
}
