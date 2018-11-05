package com.example.rocky_geralt.practicaempresarial.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.rocky_geralt.practicaempresarial.app.MainActivity;
import com.example.rocky_geralt.practicaempresarial.R;
import com.example.rocky_geralt.practicaempresarial.connection.RegisterRequest;
import com.example.rocky_geralt.practicaempresarial.connection.SupportRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Soporte extends AppCompatActivity implements View.OnClickListener {

    EditText etTipo, etMarca, etEmpresa, etTelefono, etDescripcion;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soporte);

        etTipo = findViewById(R.id.Tipo);
        etMarca = findViewById(R.id.Marca);
        etEmpresa = findViewById(R.id.Empresa);
        etTelefono = findViewById(R.id.Telefono);
        etDescripcion = findViewById(R.id.Descripcion);
        registrar = findViewById(R.id.btnSoporte);

        registrar.setOnClickListener(this);

        if (!conexion(this)){
            Toast.makeText(getBaseContext(), "Necesita conexion a internet ", Toast.LENGTH_LONG).show();
            this.finish();
        }

    }

    @Override
    public void onClick(View view) {

        final String tipo = etTipo.getText().toString();
        final String marca = etMarca.getText().toString();
        final String empresa = etEmpresa.getText().toString();
        final String telefono = etTelefono.getText().toString();
        final String descripcion = etDescripcion.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (etTipo.getText().toString().isEmpty()){  //SE APLICA UNA CONDICIÓN SI LOS CAMPOS ESTÁN VACÍOS

                    Toast.makeText(Soporte.this, "Debe ingresar tipo de equipo electronico", Toast.LENGTH_SHORT).show();
                    //SE MANDA MENSAJE

                }else if (etMarca.getText().toString().isEmpty()) {

                    Toast.makeText(Soporte.this, "Debe ingresar marca del equipo", Toast.LENGTH_SHORT).show();

                }else if (etEmpresa.getText().toString().isEmpty()) {

                    Toast.makeText(Soporte.this, "Debe ingresar nombre de la empresa o cliente", Toast.LENGTH_SHORT).show();

                }else if (etTelefono.getText().toString().isEmpty()) {

                    Toast.makeText(Soporte.this, "Debe ingresar numero de telefono", Toast.LENGTH_SHORT).show();

                }else if (etDescripcion.getText().toString().isEmpty()) {

                    Toast.makeText(Soporte.this, "Debe ingresar descripcion del problema", Toast.LENGTH_SHORT).show();

                }
                try {
                    JSONObject jsonReponse = new JSONObject(response);
                    boolean success = jsonReponse.getBoolean("success");
                    if (success){
                        Toast.makeText(Soporte.this, "registro de soporte creado exitosamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Soporte.this, MainActivity.class);
                        startActivity(intent);
                        Soporte.this.finish();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Soporte.this);
                        builder.setMessage("Error al hacer registro").setNegativeButton("Volver",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SupportRequest supportRequest = new SupportRequest(tipo, marca, empresa, telefono, descripcion, respoListener );
        RequestQueue queue = Volley.newRequestQueue(Soporte.this);
        queue.add(supportRequest);
    }

    public void onBackPressed(){
        Intent menu = new Intent(Soporte.this, Servicios.class);
        startActivity(menu);
        this.finish();
    }

    public static boolean conexion(Context context){

        boolean conectado = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Recuperar todas las redes (datos o wifi)
        NetworkInfo[] redes = connectivityManager.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++){
            //Si alguna red tiene conexion, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED){
                conectado = true;
            }
        }
        return conectado;
    }
}
