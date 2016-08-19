package com.ayc.coleccionistas;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActualizacionesAdapter extends BaseAdapter {

    private static final String URL_Image = "http://10.0.2.2/coleccionistas";
    private static final String URL = "http://10.0.2.2/coleccionistas/consulta_escogidos.php";
    private static final String TAG = Notices.class.getSimpleName();

    private Context mContext;
    private List<Producto> items;
    private RequestQueue request;

    public ActualizacionesAdapter(Context c){
        mContext = c;
        request = Volley.newRequestQueue(c);
        request.add(
                new JsonObjectRequest(Request.Method.POST, URL,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                items = parseJson(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "ERROR VOLLEY: " + error.getMessage());
                            }
                        })
        );
    }

    public int getCount(){
        return items != null ? items.size() : 0;
    }

    public Producto getItem(int position){
        return items.get(position);
    }

    public long getItemId(int i){
        return items.get(i).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setImageResource(R.drawable.error);
        } else {
            imageView = (ImageView) convertView;
        }
        Producto producto = items.get(position);
        request.add(
                new ImageRequest(URL_Image + producto.getImagen(),
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                imageView.setImageBitmap(response);
                            }
                        }, 0, 0, null, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                imageView.setImageResource(R.drawable.error);
                                Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                            }
                        })
        );
        return imageView;
    }

    public List<Producto> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Producto> productos = new ArrayList();
        JSONArray jsonArray;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("productos");

            for(int i=jsonArray.length()-3; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Producto producto = new Producto(
                            objeto.getLong("id"),
                            objeto.getString("nombre"),
                            objeto.getString("descripcion"),
                            objeto.getString("precio"),
                            objeto.getString("imagen"));


                    productos.add(producto);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productos;
    }

}
