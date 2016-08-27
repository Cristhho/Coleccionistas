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

    private GridView gridNews;
    private ListView listaAct;
   // ActualizacionesAdapter adapter ;
    ActualizacionesFullAdapter adapter ;
 /*   private String datos[][] = {
            {"Consola antigua año 90",
                    "Donec id elit non mi porta gravida at eget metus. " +
                            "Fusce dapibus, tellus ac cursus commodo, " +
                            "tortor mauris condimentum nibh, " +
                            "ut fermentum massa justo sit amet risus",
                    "$500,00"},
            {"Figura de action de superman 70\'s",
                    "Donec id elit non mi porta gravida at eget metus. " +
                            "Fusce dapibus, tellus ac cursus commodo, " +
                            "tortor mauris condimentum nibh, " +
                            "ut fermentum massa justo sit amet risus",
                    "$800,00"},
            {"Llave antigua 200 años",
                    "Donec id elit non mi porta gravida at eget metus. " +
                            "Fusce dapibus, tellus ac cursus commodo, " +
                            "tortor mauris condimentum nibh, " +
                            "ut fermentum massa justo sit amet risus",
                    "$100,00"},
            {"Botella Coca-Cola 60\'s",
                    "Donec id elit non mi porta gravida at eget metus. " +
                            "Fusce dapibus, tellus ac cursus commodo, " +
                            "tortor mauris condimentum nibh, " +
                            "ut fermentum massa justo sit amet risus",
                    "$60,00"},
            {"Moneda celta 500 años",
                    "Donec id elit non mi porta gravida at eget metus. " +
                            "Fusce dapibus, tellus ac cursus commodo, " +
                            "tortor mauris condimentum nibh, " +
                            "ut fermentum massa justo sit amet risus",
                    "$1000,00"},
            {"Detecctive Comic #27",
                    "Donec id elit non mi porta gravida at eget metus. " +
                            "Fusce dapibus, tellus ac cursus commodo, " +
                            "tortor mauris condimentum nibh, " +
                            "ut fermentum massa justo sit amet risus",
                    "$5000,00"}
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizaciones);
        listaAct = (ListView)findViewById(R.id.listActualizacion);
       //adapter = new ActualizacionesAdapter(this);
        adapter = new ActualizacionesFullAdapter(Actualizaciones.this);
        listaAct.setAdapter(adapter);
        listaAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto obj = adapter.getItem(i);

                Intent intentt = new Intent(getApplicationContext(),Item_Description.class);
                //i.putExtra("id",position);
                /*intentt.putExtra("name",obj.getNombre());
                intentt.putExtra("desc",obj.getDescripcion());
                intentt.putExtra("price",obj.getPrecio());
                intentt.putExtra("source",obj.getImagen());
                startActivity(intentt);*/

                Log.d("Producto >>>","Titulo: " + obj.getNombre()
                        + "\nDescripcion: " + obj.getDescripcion()
                        + "\nImagen: " +"" );
            }
        });

      /*  gridNews = (GridView)findViewById(R.id.gridNewsFull);
        ActualizacionesFullAdapter afa = new ActualizacionesFullAdapter(this);
        gridNews.setAdapter(afa);
        gridNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Item_Description.class);
                i.putExtra("id",position);
                i.putExtra("name",datos[position][0]);
                i.putExtra("desc",datos[position][1]);
                i.putExtra("price",datos[position][2]);
                i.putExtra("source","actual");
                startActivity(i);
            }
        });*/
    }
}
