package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class Actualizaciones extends Activity {


    private ListView listaAct;
   // ActualizacionesAdapter adapter ;
    ActualizacionesFullAdapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizaciones);
        listaAct = (ListView)findViewById(R.id.listActualizacion);
        adapter = new ActualizacionesFullAdapter(Actualizaciones.this);
        listaAct.setAdapter(adapter);
        listaAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto obj = adapter.getItem(i);

                Intent intentt = new Intent(getApplicationContext(),Item_Description.class);
                //i.putExtra("id",position);
                intentt.putExtra("name",obj.getNombre());
                intentt.putExtra("desc",obj.getDescripcion());
                intentt.putExtra("price",obj.getPrecio());
                intentt.putExtra("source",obj.getImagen());
                startActivity(intentt);

                Log.d("Producto >>>","Titulo: " + obj.getNombre()
                        + "\nDescripcion: " + obj.getDescripcion()
                        + "\nImagen: " +"" );
            }
        });


    }
}
