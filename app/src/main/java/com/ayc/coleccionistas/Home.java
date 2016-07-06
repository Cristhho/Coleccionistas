package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

    Button Enviar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
     Enviar = (Button) findViewById(R.id.buttonSing);
    }
    public  void SingIng (View view){

        Intent i = new Intent(this,Notices.class );
        startActivity(i);


    }
}
