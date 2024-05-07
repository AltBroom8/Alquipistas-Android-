package com.example.alquipistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


public class Registro extends Fragment {

    ImageView ojo;
    ImageView ojo2;
    EditText password;
    EditText password2;
    boolean oculto = false;
    boolean oculto2 = false;

    public Registro() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        ojo = view.findViewById(R.id.mostrar);
        password = view.findViewById(R.id.password);
        ojo2 = view.findViewById(R.id.mostrar2);
        password2 = view.findViewById(R.id.password2);

        ojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!oculto){
                    ojo.setImageResource(R.drawable.hide_password);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    oculto=true;

                }else{
                    ojo.setImageResource(R.drawable.show_password);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    oculto=false;
                }
                password.setSelection(password.length());

            }
        });

        ojo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!oculto2){
                    ojo2.setImageResource(R.drawable.hide_password);
                    password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    oculto2=true;

                }else{
                    ojo2.setImageResource(R.drawable.show_password);
                    password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    oculto2=false;
                }
                password2.setSelection(password2.length());

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}