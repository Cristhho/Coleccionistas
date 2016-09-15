package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoriaElegir extends Activity {
    Intent categoria;
    String cat,userid,user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_elegir);
         categoria = new Intent(this, Categorias.class);

         userid = getIntent().getStringExtra("userid");
         user = getIntent().getStringExtra("user");

    }
    public void carro(View view) {
        cat = "Autos";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        startActivity(categoria);
    }
    public void juegos(View view) {
        cat = "Video Juegos";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        startActivity(categoria);

    }
    public void comics(View view) {
        cat = "Comics";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        startActivity(categoria);
    }
    public void otros(View view) {
        cat = "Otros";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        startActivity(categoria);

    }
    public void monedas(View view) {
        cat = "Monedas";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        startActivity(categoria);

    }
    public void pintura(View view) {
        cat = "Pinturas";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        startActivity(categoria);

    }

}
