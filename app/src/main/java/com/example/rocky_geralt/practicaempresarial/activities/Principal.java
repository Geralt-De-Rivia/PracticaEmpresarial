package com.example.rocky_geralt.practicaempresarial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rocky_geralt.practicaempresarial.R;
import com.example.rocky_geralt.practicaempresarial.app.MainActivity;

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
    public void onBackPressed(){
        Intent menu = new Intent(Principal.this, MainActivity.class);
        startActivity(menu);
        this.finish();
    }


}
