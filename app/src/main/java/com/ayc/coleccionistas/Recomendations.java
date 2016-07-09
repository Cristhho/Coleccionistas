package com.ayc.coleccionistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Recomendations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendations);
    }

    public void viewItem (View view){
        Intent i = new Intent(this, Item_Description.class);
        startActivity(i);
    }

}
