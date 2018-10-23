package com.example.rocky_geralt.practicaempresarial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Soporte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soporte);
    }
    public void onBackPressed(){
        Intent menu = new Intent(Soporte.this, MainActivity.class);
        startActivity(menu);
        this.finish();
    }
}
