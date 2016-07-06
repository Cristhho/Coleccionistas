package com.ayc.coleccionistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Notices extends AppCompatActivity {

    TextView Recomendados ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_notices);
         Recomendados = (TextView) findViewById(R.id.textEscogido) ;
    }

    public void Recomendations (View view){

        Intent i = new Intent(this, Recomendations.class);
        startActivity(i);
    }
}
