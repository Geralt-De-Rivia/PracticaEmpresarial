package com.example.rocky_geralt.practicaempresarial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Principal extends AppCompatActivity{

    TextView tvnombre, tvemail, tvtelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        tvnombre = findViewById(R.id.txtNombre);
        tvemail = findViewById(R.id.txtEmail);
        tvtelefono = findViewById(R.id.txtTelefono);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String email = intent.getStringExtra("email");
        String telefono = intent.getStringExtra("telefono");

        tvnombre.setText(nombre);
        tvemail.setText(email);
        tvtelefono.setText(telefono);
    }
}
