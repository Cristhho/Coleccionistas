package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class perfil extends Activity {
String username , userid;
    public GridView coleccion ;
    PerfilAdapter perfilAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        TextView editText = (TextView) findViewById(R.id.textViewNOmbre);
        coleccion = (GridView)findViewById(R.id.gridPerfil) ;
        username = getIntent().getStringExtra("User");
        userid = getIntent().getStringExtra("userid");
        editText.setText(username);

        perfilAdapter = new PerfilAdapter(this,userid);
        coleccion.setAdapter(perfilAdapter);
        coleccion.setColumnWidth(3);
        coleccion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto p = perfilAdapter.getItem(position);
                Intent desc = new Intent(getApplicationContext(),Item_Description.class);
                desc.putExtra("nombre",p.getNombre());
                desc.putExtra("descripcion",p.getDescripcion());
                desc.putExtra("precio",p.getPrecio());
                desc.putExtra("correo",p.getCorreo());
                desc.putExtra("imagen",p.getImagen());
                desc.putExtra("User",username);
                startActivity(desc);
            }
        });
    }
    public void agregarP (View view){
        Intent i = new Intent(this, AgregarProducto.class);
        i.putExtra("User",username);
        i.putExtra("userid",userid);
        startActivity(i);

    }
}
