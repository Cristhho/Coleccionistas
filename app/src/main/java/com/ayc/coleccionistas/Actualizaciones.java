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

public class Actualizaciones extends Activity {


    private ListView listaAct;
    ActualizacionesFullAdapter adapter ;
    private String user, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizaciones);
        listaAct = (ListView)findViewById(R.id.listActualizacion);
        user = getIntent().getStringExtra("User");
        userid = getIntent().getStringExtra("userid");
        adapter = new ActualizacionesFullAdapter(Actualizaciones.this);
        listaAct.setAdapter(adapter);
        listaAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                desc.putExtra("userid",userid);
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
                upIntent.putExtra("userid",userid);
                NavUtils.navigateUpTo(this,upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
