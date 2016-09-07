package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class Categorias extends Activity {

    private TextView categoria;
    private ListView listCat;
    CategoriasAdapter adapter;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        user = getIntent().getStringExtra("User");
        categoria = (TextView)findViewById(R.id.txtCategoria);
        String cat = getIntent().getStringExtra("categoria");
        categoria.setText(cat);
        listCat = (ListView)findViewById(R.id.listCategoria);
        adapter = new CategoriasAdapter(this,cat);
        listCat.setAdapter(adapter);
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
