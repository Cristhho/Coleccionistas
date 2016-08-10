package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Notices extends Activity {

    private GridView gridRecomendados, gridActualizaciones;
    private String datos[][] = {
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
                    "$5000,00"},
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
                    "$100,00"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        gridRecomendados = (GridView)findViewById(R.id.gridEscogidos);
        EscogidosAdapter ea = new EscogidosAdapter(this);
        gridRecomendados.setAdapter(ea);
        gridRecomendados.setNumColumns(ea.getCount());
        gridRecomendados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Item_Description.class);
                i.putExtra("id",position);
                i.putExtra("name",datos[position][0]);
                i.putExtra("desc",datos[position][1]);
                i.putExtra("price",datos[position][2]);
                i.putExtra("source","rec");
                startActivity(i);
            }
        });
        gridActualizaciones = (GridView)findViewById(R.id.gridNews);
        ActualizacionesAdapter aa = new ActualizacionesAdapter(this);
        gridActualizaciones.setAdapter(aa);
        gridActualizaciones.setNumColumns(aa.getCount());
        gridActualizaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Item_Description.class);
                i.putExtra("id",position + 3);
                i.putExtra("name",datos[position + 3][0]);
                i.putExtra("desc",datos[position + 3][1]);
                i.putExtra("price",datos[position + 3][2]);
                i.putExtra("source","rec");
                startActivity(i);
            }
        });
    }

    public void Recommendations (View view){
        Intent i = new Intent(this, Recomendations.class);
        startActivity(i);
    }

    public void Updates (View view){
        Intent u = new Intent(this,Actualizaciones.class);
        startActivity(u);
    }

}
