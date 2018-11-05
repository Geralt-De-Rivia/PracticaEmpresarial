package com.example.rocky_geralt.practicaempresarial.connection;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://half-pound-roar.000webhostapp.com/proyectopractica/Register.php"; //Ruta donde se encuentra nuestra conexion php a base de datos de registro
    private Map<String, String> parametros;

    public RegisterRequest(String id, String nombre, String email, String telefono, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        parametros = new HashMap<>();
        parametros.put("id", id);
        parametros.put("nombre", nombre);
        parametros.put("email", email);
        parametros.put("telefono", telefono);
        parametros.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return parametros;
    }
}
