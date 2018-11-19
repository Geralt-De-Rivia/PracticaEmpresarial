package com.example.rocky_geralt.practicaempresarial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rocky_geralt.practicaempresarial.R;
import com.example.rocky_geralt.practicaempresarial.app.MainActivity;

public class Noticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias);

        Button servicios = findViewById(R.id.btnServicios);
        Button mision = findViewById(R.id.btnMision);
        Button vision = findViewById(R.id.btnVision);

        servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Noticias.this, TiposServicios.class);
                startActivity(intent);
                Noticias.this.finish();
            }
        });

        mision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Noticias.this, TiposServicios.class);
                startActivity(intent);
                Noticias.this.finish();
            }
        });

        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Noticias.this, TiposServicios.class);
                startActivity(intent);
                Noticias.this.finish();
            }
        });
    }

    public void onBackPressed(){
        Intent menu = new Intent(Noticias.this, Servicios.class);
        startActivity(menu);
        this.finish();
    }
}
