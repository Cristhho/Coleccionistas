package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        tagTitles = getResources().getStringArray(R.array.tags);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        mTitle = mDrawerTitle = getTitle();
        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        items.add(new DrawerItem(tagTitles[0],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[1],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[2],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[3],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[4],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[5],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[6],R.drawable.icon));
        items.add(new DrawerItem(tagTitles[7],R.drawable.icon));
        drawerList.setAdapter(new DrawerListAdapter(this, items));
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
                /*
                Intent i = new Intent(getApplicationContext(),Item_Description.class);
                i.putExtra("id",position);
                i.putExtra("name",datos[position][0]);
                i.putExtra("desc",datos[position][1]);
                i.putExtra("price",datos[position][2]);
                i.putExtra("source","rec");
                startActivity(i);*/
                Producto p = ea.getItem(position);
                Log.d("Producto>>>", "Id: " + p.getId()
                    + "\nNombre: " + p.getNombre() + "\nPrecio: " + p.getPrecio()
                    + "\nImagen: " + p.getImagen());
            }
        });
        gridActualizaciones = (GridView)findViewById(R.id.gridNews);
        aa = new ActualizacionesAdapter(this);
        gridActualizaciones.setAdapter(aa);
        gridActualizaciones.setNumColumns(3);
        gridActualizaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                Intent i = new Intent(getApplicationContext(),Item_Description.class);
                i.putExtra("id",position + 3);
                i.putExtra("name",datos[position + 3][0]);
                i.putExtra("desc",datos[position + 3][1]);
                i.putExtra("price",datos[position + 3][2]);
                i.putExtra("source","rec");
                startActivity(i);*/
                Producto p = aa.getItem(position);
                Log.d("Producto>>>", "Id: " + p.getId()
                        + "\nNombre: " + p.getNombre() + "\nPrecio: " + p.getPrecio()
                        + "\nImagen: " + p.getImagen());
            }
        });
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
        startActivity(i);
    }

    public void Updates (View view){
        Intent u = new Intent(this,Actualizaciones.class);
        startActivity(u);
    }

}
