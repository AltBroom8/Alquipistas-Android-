package com.example.alquipistas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends Fragment {
    //DECLARACIONES
    ApiRest myApi;
    ImageView ojo;
    EditText passwordInput;
    EditText userInput;
    boolean oculto = false;
    Button boton;

    public Login(ApiRest api) {
        this.myApi = api;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //AQUI INICIALIZO MIS VARIABLES
        ojo = view.findViewById(R.id.ojo);
        passwordInput = view.findViewById(R.id.password);
        userInput = view.findViewById(R.id.userLogin);
        boton = view.findViewById(R.id.btn);
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordInput.setTypeface(Typeface.SANS_SERIF);
        //METODO PARA MOSTRAR U OCULTAR LA  CONTRASEÑA
        ojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!oculto){
                    ojo.setImageResource(R.drawable.hide_password);
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    oculto=true;

                }else{
                    ojo.setImageResource(R.drawable.show_password);
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    oculto=false;
                }
                passwordInput.setSelection(passwordInput.length());
                passwordInput.setTypeface(Typeface.SANS_SERIF);

            }
        });
        //METODO QUE CONTROLA EL INICIO DE SESION
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = String.valueOf(userInput.getText());
                String password = String.valueOf(passwordInput.getText());
                //NO SE PERMITE DEJAR CAMPOS VACIOS
                if(user.isEmpty()){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setMessage("El campo usuario está vacío")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Aquí puedes realizar alguna acción adicional si es necesario
                                }
                            })
                            .show();
                }else if(password.isEmpty()){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setMessage("El campo contraseña está vacío")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Aquí puedes realizar alguna acción adicional si es necesario
                                }
                            })
                            .show();
                }else{
                    //VALIDAMOS SI LOS DATOS SON CORRECTOS
                    myApi.inicioSesion(user,password,loginCorrecto->{
                        String titulo = "";
                        String msg = "";
                        //CAMBIO DE ACTIVITY SI SON CORRECTOS
                        if (loginCorrecto){
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", user);
                            editor.apply();
                            Intent intent = new Intent(getActivity(), Home.class);
                            startActivity(intent);
                            getActivity().finish();

                        }else {
                            titulo = "Error";
                            msg = "Acceso denegado";
                            //EN  CASO CONTRARIO LANZO ALERTDIALOG
                            new AlertDialog.Builder(getActivity())
                                    .setTitle(titulo)
                                    .setMessage(msg)
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();
                        }
                    });




                }
            }
        });

        return view;
    }
}