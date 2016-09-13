package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class Item_Description extends Activity {

    private ImageView itemImg;
    private TextView itemName,itemDescrip,itemPrice,itemCorreo;
    private static final String URL_Image = "http://10.0.2.2/coleccionistas";
    private static final String TAG = Item_Description.class.getSimpleName();
    private RequestQueue request;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__description);
        itemImg = (ImageView)findViewById(R.id.imgItem);
        Intent i = getIntent();
        user = i.getExtras().getString("User");
        String name = i.getExtras().getString("nombre");
        String description = i.getExtras().getString("descripcion");
        String price = i.getExtras().getString("precio");
        String correo = i.getExtras().getString("correo");
        String img = i.getExtras().getString("imagen");
        itemName = (TextView)findViewById(R.id.textName);
        itemDescrip = (TextView)findViewById(R.id.textDescription);
        itemPrice = (TextView)findViewById(R.id.textPrice);
        itemCorreo = (TextView)findViewById(R.id.textCorreo);
        itemName.setText(name);
        itemDescrip.setText(description);
        itemPrice.setText("$" + price);
        itemCorreo.setText(correo);
        request = Volley.newRequestQueue(this);
        request.add(
                new ImageRequest(URL_Image + img,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                itemImg.setImageBitmap(response);
                            }
                        }, 0, 0, null, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                itemImg.setImageResource(R.drawable.error);
                                Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                            }
                        })
        );
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
