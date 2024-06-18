package com.example.alquipistas;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registro extends Fragment {
    //DECLARACIONES
    ApiRest myApi;
    EditText nombre;
    EditText username;
    EditText email;
    Button btn;
    boolean oculto = false;
    //CONSTRUCTOR QUE RECIBE LA API
    public Registro(ApiRest api) {
        this.myApi = api;
    }
    //ON CREATE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //INICIALIZAMOS VARIABLES Y CONFIGURACION INICIAL DE CONTRASEÑA
        View view = inflater.inflate(R.layout.fragment_registro, container, false);


        nombre = view.findViewById(R.id.nombre);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        btn = view.findViewById(R.id.btn);


        //METODO QUE MANEJA EL BOTON DE REGISTRAR
        btn.setOnClickListener(v->{
            //LISTA DE REGEX
            String[] regex = {
                    "^([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]{0,1}[A-Za-zÑñÁáÉéÍíÓóÚú]+)(\\s+([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]{0,1}[A-Za-zÑñÁáÉéÍíÓóÚú]+))*$",
                    "^[a-zA-Z0-9]{5,}$",
                    "^[a-zA-Z0-9._%+-]+@(gmail|outlook|hotmail|yahoo|aol|icloud|live|msn|mail|yandex|protonmail|inbox)\\.(com|es|net|org|info|gov|edu)$"
            };
            //LISTA DE EDITTEXT
            EditText[] inputs = {
                    nombre,username,email
            };
            //CAMPOS EXISTENTES
            String[] campos = {"nombre", "usuario", "email"};
            //MANEJA QUE NINGUN CAMPO ESTÉ VACIO
            for (int i = 0; i < campos.length; i++) {
                if (String.valueOf(inputs[i].getText()).isEmpty()) {
                    mostrarErrorVacio(campos[i]);
                    Log.d("vacio","vuelve en vacio");
                    return;
                }
            }
            //MANEJA QUE SE CUMPLAN LOS PATRONES REGEX
            for(int i=0;i<regex.length;i++){
                Pattern pattern = Pattern.compile(regex[i]);
                Matcher matcher = pattern.matcher(String.valueOf(inputs[i].getText()));

                if(!matcher.matches()){
                    mostrarErrorRegex(i,campos[i]);
                    return;
                }
            }
            //HILO SECUNDARIO PARA NO BLOQUEAR LA APP AL HACER AWAIT
            new Thread(() -> {
                // Variables para almacenar los resultados de las llamadas
                boolean[] userRegistrado = {true};
                // Iniciar las llamadas asincrónicas a CompruebaUser
                CountDownLatch latch = new CountDownLatch(1);
                myApi.CompruebaUser(String.valueOf(inputs[1].getText()), resultado -> {
                    userRegistrado[0] = resultado;
                    latch.countDown();
                });


                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //OPERACIONES EN EL HILO PRINCIPAL DE LA APP
                getActivity().runOnUiThread(() -> {
                    if (userRegistrado[0]) {
                        mostrarErrorYaRegistrado(campos[1]);
                        Log.d("user","vuelve en user ya registrado");
                        return;
                    }
                    Log.d("datos", inputs[0].getText() +" "+ inputs[1].getText() +" "+
                            inputs[2].getText());
                    myApi.registro(String.valueOf(inputs[0].getText()),String.valueOf(inputs[1].getText()),
                            String.valueOf(inputs[2].getText()), resultado ->{
                                Log.d("REGISTRANDO","ENTRA, resultado es "+ resultado);

                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", String.valueOf(inputs[1].getText()));
                                    editor.apply();
                                    Intent intent = new Intent(getActivity(), Home.class);
                                    startActivity(intent);
                                    getActivity().finish();

                            });



                });
            }).start();
        });
        //DEVOLVEMOS LA VISTA AL MAIN ACTIVITY
        return view;
    }

    //Metodo que muestra un error en Pantalla relacionado con un campo vacío
    private void mostrarErrorVacio(String campo) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Error")
                .setMessage("El campo " + campo + " está vacío")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
    //Metodo que muestra un error en Pantalla relacionado con los requisitos de las regex
    private void mostrarErrorRegex(int id, String campo) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Error")
                .setMessage("El campo " + campo + " no es válido")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
    //Metodo que muestra un error en Pantalla si un email o username ya está en la base de datos
    private void mostrarErrorYaRegistrado(String campo) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Error")
                .setMessage("El " + campo + " ya está registrado.")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}