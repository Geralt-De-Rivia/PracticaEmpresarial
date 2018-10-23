package com.example.rocky_geralt.practicaempresarial;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        if (!conexion(this)){
            Toast.makeText(getBaseContext(), "Necesita conexion a internet ", Toast.LENGTH_LONG).show();
            this.finish();
        }

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id.getText().toString().isEmpty()){  //AQUI DIGO SI EL CAMPO NIT ESTÁ VACÍO

                    Toast.makeText(MainActivity.this, "Debe ingresar Nit o cedula", Toast.LENGTH_SHORT).show(); //MANDE UN MENSAJE QUE DEBE INGRESAR RUT

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
                                String telefono = jsonResponse.getString("telefono");

                                Intent intent = new Intent(MainActivity.this, Servicios.class);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("email", email);
                                intent.putExtra("telefono", telefono);

                                startActivity(intent);

                                MainActivity.this.finish();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error de logueo o Usuario no existe").setNegativeButton("Volver",null).create().show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_about:
                showAbout();
                return true;
            case R.id.m_howto:
                showHowTo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAbout() {
        Intent i = new Intent(this, Acerca.class);
        startActivity(i);
    }
    private void showHowTo() {
        Intent i = new Intent(this, Ayuda.class);
        startActivity(i);
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
