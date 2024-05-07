package com.example.alquipistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends Fragment {

    ImageView ojo;
    EditText password;
    boolean oculto = false;

    public Login() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ojo = view.findViewById(R.id.mostrar);
        password = view.findViewById(R.id.password);
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
        // Inflate the layout for this fragment
        return view;
    }
}