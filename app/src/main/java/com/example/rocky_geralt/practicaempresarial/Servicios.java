package com.example.rocky_geralt.practicaempresarial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Servicios extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicios);

        Button soporte = findViewById(R.id.soporte);
        Button noticias = findViewById(R.id.noticias);

        soporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Servicios.this, Soporte.class);
                startActivity(intent);
                Servicios.this.finish();
            }
        });

        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Servicios.this, Noticias.class);
                startActivity(intent);
                Servicios.this.finish();
            }
        });

    }

    public void onBackPressed(){
        Intent menu = new Intent(Servicios.this, MainActivity.class);
        startActivity(menu);
        this.finish();
    }
}
