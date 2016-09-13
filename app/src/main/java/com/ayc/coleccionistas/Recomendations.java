package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class Recomendations extends Activity {

    private ListView listaE;
    EscogidosFullAdapter adapter ;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendations);
        listaE = (ListView)findViewById(R.id.ListEscogidos);

        user = getIntent().getStringExtra("User");

        adapter = new EscogidosFullAdapter(Recomendations.this);
        listaE.setAdapter(adapter);


        listaE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto p = adapter.getItem(i);
                Intent desc = new Intent(getApplicationContext(),Item_Description.class);
                desc.putExtra("nombre",p.getNombre());
                desc.putExtra("descripcion",p.getDescripcion());
                desc.putExtra("precio",p.getPrecio());
                desc.putExtra("correo",p.getCorreo());
                desc.putExtra("imagen",p.getImagen());
                desc.putExtra("User",user);
                startActivity(desc);
            }
        });

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.putExtra("User",user);
                NavUtils.navigateUpTo(this,upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
