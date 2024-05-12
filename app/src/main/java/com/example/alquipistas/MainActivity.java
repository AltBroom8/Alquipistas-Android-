package com.example.alquipistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ApiRest myApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("Comienza","Aqui empieza el activity main");
        //AL INICIAR LA APP BUSCO SI LA SESION YA ESTABA INICIADA
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String nombreUsuario = sharedPreferences.getString("username", "");
        //SI TENIA ALGO EN SHARED PREFERENCES CAMBIO DE ACTIVITY
        if (!nombreUsuario.isEmpty()) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            this.finish();
        }
        myApi = new ApiRest();
        //CONFIGURACION CON VIEWPAGER Y TABLAYOUT
        ViewPager2 viewPager = findViewById(R.id.paginador);
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this,myApi);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> {
            switch(position){
                case 0:
                    tab.setText(R.string.loginbutton_text);
                    break;
                case 1:
                    tab.setText(R.string.registro_header);
                    break;
            }
        }).attach();

    }
    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        //CLASE PARA MANEJAR QUE FRAGMENT SE VE EN PANTALLA
        private ApiRest api;

        public ScreenSlidePagerAdapter(FragmentActivity fa, ApiRest api) {
            super(fa);
            this.api = api;
        }
        //EN FUNCION DE LA POSIICION, SE VE UN FRAGMENT U OTRO
        @NonNull
        @Override
        public Fragment createFragment(int position) {

            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new Login(this.api);
                    break;
                case 1:
                    fragment = new Registro(this.api);
                    break;
                default:
                    fragment = null;
                    break;
            }
            return fragment;
        }
        //DEVUELVE CUANTOS FRAGMENTS TENGO
        @Override
        public int getItemCount() {
            return 2;
        }
    }
}