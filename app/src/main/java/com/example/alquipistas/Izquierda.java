package com.example.alquipistas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.function.Consumer;

public class Izquierda extends Fragment {
    ApiRest myApi;
    int id;
    String username;
    TextView user;
    EditText nombre,apellido,cp,email,date;
    Button cerrarSesion,guardar;
    public Izquierda(ApiRest api, String username) {
        this.myApi = api;
        this.username = username;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_izq, container, false);
        user = view.findViewById(R.id.usernameLeft);
        guardar = view.findViewById(R.id.guardarBtn);
        cerrarSesion = view.findViewById(R.id.cerrarSesion);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        nombre = view.findViewById(R.id.nombreLeft);
        apellido = view.findViewById(R.id.apellidoLeft);
        cp = view.findViewById(R.id.postalLeft);
        email = view.findViewById(R.id.emailLeft);
        date = view.findViewById(R.id.dateLeft);
        user.setText(username);
        date.setOnClickListener(v -> showDatePickerDialog());


        myApi.getSocio(username, user->{
            if (user != null) {
                nombre.setText(user.getNombre() != null ? user.getNombre() : "");
                apellido.setText(user.getApellidos() != null ? user.getApellidos() : "");
                cp.setText(user.getCodigoPostal() != null ? user.getCodigoPostal() : "");
                email.setText(user.getEmail() != null ? user.getEmail() : "");
                date.setText(user.getFNac() != null ? formatDate(user.getFNac()) : "");
                id = user.getId();

            } else {
                Log.d("Izquierda", "Error al obtener el socio.");
            }

        });
        guardar.setOnClickListener(v->{
            //TODO nombre,apellido,cp,email y date no pueden estar vacios
            //TODO si estan vacios mostrar AlertDialog
            //TODO cp debe tener 5 digitos, sino mostrar AlertDialog
            //TODO email debe comprobar la regex para ser valido.Si no muestra alertDialog
            String nombreText = nombre.getText().toString().trim();
            String apellidoText = apellido.getText().toString().trim();
            String cpText = cp.getText().toString().trim();
            String emailText = email.getText().toString().trim();
            String dateText = date.getText().toString().trim();

            // Validación de campos vacíos
            if (nombreText.isEmpty() || apellidoText.isEmpty() || cpText.isEmpty() || emailText.isEmpty() || dateText.isEmpty()) {
                showAlertDialog("Error", "Todos los campos deben estar llenos.");
                return;
            }

            // Validación de código postal
            if (!cpText.matches("\\d{5}")) {
                showAlertDialog("Error", "El código postal debe tener 5 dígitos.");
                return;
            }

            // Validación de email
            if (!isValidEmail(emailText)) {
                showAlertDialog("Error", "El email no es válido.");
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date fechaSeleccionada = null;
            try {
                fechaSeleccionada = dateFormat.parse(dateText);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (fechaSeleccionada == null || !fechaSeleccionada.before(new Date())) {
                showAlertDialog("Error", "La fecha seleccionada debe ser anterior al día de hoy.");
                return;
            }
            myApi.updateUser(nombreText,apellidoText,Integer.parseInt(cpText),emailText,fechaSeleccionada,id);
            showAlertDialog("Confirmación", "Datos actualizados con éxito.");

        });

        cerrarSesion.setOnClickListener(v -> {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username");
            editor.apply();

            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }
    private String formatDate(String date) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date parsedDate = inputFormatter.parse(date);
            return outputFormatter.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    selectedMonth = selectedMonth + 1;
                    String selectedDate = selectedDay + "/" + selectedMonth + "/" + selectedYear;
                    date.setText(selectedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
