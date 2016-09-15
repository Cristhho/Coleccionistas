package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AgregarProducto extends Activity {
    Spinner Combobox ;

    private String titulo, descripcion, precio,categoria, userid, name;
    private EditText Etitulo, Edescripcion, Eprecio;

    private RequestQueue request;
    private static  String URL_CONSULTA_SINGIN ="http://10.0.2.2/coleccionistas/agregar_producto.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        userid = getIntent().getStringExtra("userid");
        name = getIntent().getStringExtra("user");
        System.out.println(userid);

        request = Volley.newRequestQueue(this);
       Combobox = (Spinner) findViewById(R.id.spinnerCategoria) ;
        Etitulo = (EditText)findViewById(R.id.editTitulo);
        Edescripcion =  (EditText)findViewById(R.id.editDescricionP);
        Eprecio = (EditText)findViewById(R.id.editPrecioP);

    }

    public void agregarProducto(View view){
        titulo = Etitulo.getText().toString();
        descripcion= Edescripcion.getText().toString();
        precio =""+Eprecio.getText().toString();
       // categoria= "otros";

        categoria= ""+ Combobox.getSelectedItem().toString();


         System.out.println(URL_CONSULTA_SINGIN );

        request.add( new StringRequest(Request.Method.POST, URL_CONSULTA_SINGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject oJson = new JSONObject(response);
                           // Log.d(TAG, oJson.toString());
                            procesarRespuesta(oJson);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      System.out.print( "ERROR VOLLEY: " + error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                parameters.put("titulo",titulo);
                parameters.put("descripcion",descripcion);
                parameters.put("precio",""+precio);
                parameters.put("categoria",categoria);
                parameters.put("userid",userid);
                return parameters;
            }
        });
    }

    private void procesarRespuesta(JSONObject response){
        try{

            String estado = response.getString("estado");
            String mensaje = response.getString("mensaje");
            switch (estado) {
                case "1":
                    Toast.makeText(getApplicationContext(),
                            mensaje,Toast.LENGTH_SHORT).show();
                    Intent intend = new Intent(getApplicationContext(),Notices.class );
                    System.out.println("Iniciar activiadad ");
                    intend.putExtra("User",name);
                    intend.putExtra("userid",userid);
                    startActivity(intend);
                    break;
                case "2":
                    Toast.makeText(getApplicationContext(),
                            mensaje,Toast.LENGTH_SHORT).show();
                    break;

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
