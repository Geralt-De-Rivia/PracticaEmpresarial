package com.example.rocky_geralt.practicaempresarial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Noticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias);
    }

    public void onBackPressed(){
        Intent menu = new Intent(Noticias.this, MainActivity.class);
        startActivity(menu);
        this.finish();
    }
}
