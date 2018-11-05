package com.example.rocky_geralt.practicaempresarial.connection;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://half-pound-roar.000webhostapp.com/proyectopractica/Login.php"; //Ruta donde se encuentra nuestra conexion php a base de datos de login

    private Map<String, String> parametros;
    public LoginRequest(String id, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        parametros = new HashMap<>();
        parametros.put("id", id);
        parametros.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return parametros;
    }
}