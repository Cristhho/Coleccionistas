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
         user = getIntent().getStringExtra("User");

    }
    public void carro(View view) {
        cat = "autos";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        categoria.putExtra("userid",userid );
        startActivity(categoria);
    }
    public void juegos(View view) {
        cat = "videojuegos";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        categoria.putExtra("userid",userid );
        startActivity(categoria);

    }
    public void comics(View view) {
        cat = "comics";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        categoria.putExtra("userid",userid );
        startActivity(categoria);
    }
    public void otros(View view) {
        cat = "otros";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        categoria.putExtra("userid",userid );
        startActivity(categoria);

    }
    public void monedas(View view) {
        cat = "monedas";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        categoria.putExtra("userid",userid );
        startActivity(categoria);

    }
    public void pintura(View view) {
        cat = "pinturas";
        categoria.putExtra("categoria", cat);
        categoria.putExtra("User",user);
        categoria.putExtra("userid",userid );
        startActivity(categoria);

    }

}
