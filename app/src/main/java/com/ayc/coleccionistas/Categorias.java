package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Categorias extends Activity {

    private TextView categoria;
    private ListView listCat;
    CategoriasAdapter adapter;
    private String user, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        user = getIntent().getStringExtra("User");
        userid = getIntent().getStringExtra("userid");
        categoria = (TextView)findViewById(R.id.txtCategoria);
        String cat = getIntent().getStringExtra("categoria");
        categoria.setText(cat);
        listCat = (ListView)findViewById(R.id.listCategoria);
        adapter = new CategoriasAdapter(this,cat);
        listCat.setAdapter(adapter);
        listCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto p = adapter.getItem(position);
                Intent desc = new Intent(getApplicationContext(),Item_Description.class);
                desc.putExtra("nombre",p.getNombre());
                desc.putExtra("descripcion",p.getDescripcion());
                desc.putExtra("precio",p.getPrecio());
                desc.putExtra("correo",p.getCorreo());
                desc.putExtra("imagen",p.getImagen());
                desc.putExtra("User",user);
                desc.putExtra("userid",userid );
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
                upIntent.putExtra("userid",userid );
                NavUtils.navigateUpTo(this,upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
