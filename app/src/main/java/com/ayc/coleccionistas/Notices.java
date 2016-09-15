package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class Notices extends Activity {

    private GridView gridRecomendados, gridActualizaciones;
    private String[] tagTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    EscogidosAdapter ea;
    ActualizacionesAdapter aa;
    private String user, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        tagTitles = getResources().getStringArray(R.array.tags);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        mTitle = mDrawerTitle = getTitle();

        user = getIntent().getStringExtra("User");
        userid = getIntent().getStringExtra("userid");

        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        items.add(new DrawerItem(user,R.drawable.icon));
        items.add(new DrawerItem(tagTitles[0],R.drawable.monedasicon));
        items.add(new DrawerItem(tagTitles[1],R.drawable.roboticon));
        items.add(new DrawerItem(tagTitles[2],R.drawable.comicicon));
        items.add(new DrawerItem(tagTitles[3],R.drawable.gameicon));
        items.add(new DrawerItem(tagTitles[4],R.drawable.pinturaicon));
        items.add(new DrawerItem(tagTitles[5],R.drawable.carroicon));
        items.add(new DrawerItem(tagTitles[6],R.drawable.otrosicon));
        drawerList.setAdapter(new DrawerListAdapter(this, items));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //setTitle(mDrawerTitle);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        gridRecomendados = (GridView)findViewById(R.id.gridEscogidos);
        ea = new EscogidosAdapter(this);
        gridRecomendados.setAdapter(ea);
        gridRecomendados.setNumColumns(3);
        gridRecomendados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto p = ea.getItem(position);
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
        gridActualizaciones = (GridView)findViewById(R.id.gridNews);
        aa = new ActualizacionesAdapter(this);
        gridActualizaciones.setAdapter(aa);
        gridActualizaciones.setNumColumns(3);
        gridActualizaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto p = aa.getItem(position);
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
    }
    public void OnclickCategorias (View view){
        Intent i = new Intent(this, CategoriaElegir.class);
        i.putExtra("User",user);
        i.putExtra("userid",userid);
        startActivity(i);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Recommendations (View view){
        Intent i = new Intent(this, Recomendations.class);
        i.putExtra("User",user);
        startActivity(i);
    }

    public void Updates (View view){
        Intent u = new Intent(this,Actualizaciones.class);
        u.putExtra("User",user);
        startActivity(u);
    }

    private void selectItem(int position) {
        if (position > 0){
            Intent categoria = new Intent(this, Categorias.class);
            String cat = tagTitles[position - 1];
            categoria.putExtra("categoria", cat);
            categoria.putExtra("User",user);
            startActivity(categoria);
        } else {
            Intent Agregar = new Intent(this, perfil.class);

            Agregar.putExtra("userid",userid );
            Agregar.putExtra("User",user);
            startActivity(Agregar);
        }
        drawerLayout.closeDrawer(drawerList);
    }

}
