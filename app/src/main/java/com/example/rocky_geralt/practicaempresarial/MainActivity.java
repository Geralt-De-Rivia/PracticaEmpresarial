package com.example.rocky_geralt.practicaempresarial;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView registrar;
    Button sesion;
    EditText password;
    EditText id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.Usuario);
        password = findViewById(R.id.Password);
        sesion = findViewById(R.id.btnSesion);
        registrar = findViewById(R.id.txtRegistro);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intent);
            }
        });

        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().isEmpty()){  //AQUI DIGO SI EL CAMPO RUT ESTÁ VACÍO

                    Toast.makeText(MainActivity.this, "Debe ingresar rut", Toast.LENGTH_SHORT).show(); //MANDE UN MENSAJE QUE DEBE INGRESAR RUT

                }else if (password.getText().toString().isEmpty()) {
                    //AQUI DIGO SI EL CAMPO PASSWORD ESTÁ VACÍO, TAMBIÉN MANDE UN MENSAJE DE QUE ESTÁ VACÍO

                    Toast.makeText(MainActivity.this, "Debe ingresar contraseña", Toast.LENGTH_SHORT).show();

                }
                final String nit = id.getText().toString();
                final String pass = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String nombre = jsonResponse.getString("nombre");
                                String email = jsonResponse.getString("email");
                                String telefono = jsonResponse.getString("nombre");

                                Intent intent = new Intent(MainActivity.this, Principal.class);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("email", email);
                                intent.putExtra("telefono", telefono);

                                MainActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("error de Login").setNegativeButton("Volver",null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(nit, pass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);

            }
        });
    }
}
