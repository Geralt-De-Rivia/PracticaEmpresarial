package com.example.rocky_geralt.practicaempresarial.connection;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SupportRequest extends StringRequest {

    private static final String SUPPORT_REQUEST_URL = "https://half-pound-roar.000webhostapp.com/proyectopractica/Soporte.php"; //Ruta donde se encuentra nuestra conexion php a base de datos de registro
    private Map<String, String> parametros;

    public SupportRequest(String tipo, String marca, String empresa, String telefono, String descripcion, Response.Listener<String> listener) {
        super(Request.Method.POST, SUPPORT_REQUEST_URL, listener, null);
        parametros = new HashMap<>();
        parametros.put("tipo", tipo);
        parametros.put("marca", marca);
        parametros.put("empresa", empresa);
        parametros.put("telefono", telefono);
        parametros.put("descripcion", descripcion);
    }

    @Override
    public Map<String, String> getParams() {
        return parametros;
    }
}