package com.example.rocky_geralt.practicaempresarial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre, etpassword, etid, ettelefono, etemail;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        etnombre = findViewById(R.id.Nombre);
        etpassword = findViewById(R.id.Password);
        etid = findViewById(R.id.Nit);
        ettelefono = findViewById(R.id.Telefono);
        etemail = findViewById(R.id.Email);
        registrar = findViewById(R.id.btnRegistro);

        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        final String id = etid.getText().toString();
        final String nombre = etnombre.getText().toString();
        final String email = etemail.getText().toString();
        final String telefono = ettelefono.getText().toString();
        final String password = etpassword.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (etid.getText().toString().isEmpty()){  //SE APLICA UNA CONDICIÓN SI LOS CAMPOS ESTÁN VACÍOS

                    Toast.makeText(Registro.this, "Debe ingresar Nit o Cedula", Toast.LENGTH_SHORT).show();
                    //SE MANDA MENSAJE

                }else if (etnombre.getText().toString().isEmpty()) {

                    Toast.makeText(Registro.this, "Debe ingresar nombre de empresa o personal", Toast.LENGTH_SHORT).show();

                }else if (etemail.getText().toString().isEmpty()) {

                    Toast.makeText(Registro.this, "Debe ingresar email", Toast.LENGTH_SHORT).show();

                }else if (ettelefono.getText().toString().isEmpty()) {

                    Toast.makeText(Registro.this, "Debe ingresar numero de telefono", Toast.LENGTH_SHORT).show();

                }else if (etpassword.getText().toString().isEmpty()) {

                    Toast.makeText(Registro.this, "Debe ingresar contraseña", Toast.LENGTH_SHORT).show();

                }
                try {
                    JSONObject jsonReponse = new JSONObject(response);
                    boolean success = jsonReponse.getBoolean("success");
                    if (success){
                        Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        Registro.this.startActivity(intent);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("El alumno ya está registrado").setNegativeButton("Volver",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(id, nombre, email, telefono, password, respoListener );
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);
    }

}
