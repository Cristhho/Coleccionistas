package com.ayc.coleccionistas;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriasAdapter extends BaseAdapter {
    private static final String URL_Image = "http://10.0.2.2/coleccionistas";
    private static final String URL = "http://10.0.2.2/coleccionistas/consulta_categoria.php";
    private static final String TAG = Categorias.class.getSimpleName();
    private List<Producto> items;
    private RequestQueue request;
    private Context mContext;

    public CategoriasAdapter (Context c, final String categoria) {
        mContext = c;
        request = Volley.newRequestQueue(mContext);
        request.add(
                new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject oJson = new JSONObject(response);
                                    items = parseJson(oJson);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "ERROR VOLLEY: " + error.getMessage());
                            }
                        }
                ){
                    @Override
                    protected Map<String,String> getParams()throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<>();
                        parameters.put("categoria",categoria);
                        return parameters;
                    }
                }
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItemView;
        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.producto_e,
                parent,
                false) : convertView;
        Producto producto = items.get(position);
        TextView titulo = (TextView)listItemView.findViewById(R.id.textoTituloP);
        titulo.setText(producto.getNombre());
        TextView description = (TextView)listItemView.findViewById(R.id.textoDescripcionP);
        description.setText(producto.getDescripcion());
        final String img = producto.getImagen();
        final ImageView imagenProducto = (ImageView)listItemView.findViewById(R.id.imagenProductoE);
        request.add(
                new ImageRequest(URL_Image + producto.getImagen(),
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                Log.d(TAG,URL_Image + img);
                                imagenProducto.setImageBitmap(response);
                            }
                        }, 0, 0, null, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                imagenProducto.setImageResource(R.drawable.error);
                                Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                            }
                        })
        );
        return listItemView;
    }

    public List<Producto> parseJson(JSONObject jsonObject){
        List<Producto> productos = new ArrayList();
        JSONArray jsonArray;
        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("productos");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);
                    String img = objeto.getString("imagen");

                    Producto producto = new Producto(
                            objeto.getLong("id"),
                            objeto.getString("nombre"),
                            objeto.getString("descripcion"),
                            objeto.getString("precio"),
                            img,
                            objeto.getString("categoria"),
                            objeto.getString("correo"));

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
