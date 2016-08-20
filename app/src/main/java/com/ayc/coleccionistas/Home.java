package com.ayc.coleccionistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Home extends Activity {

    private EditText name, mail, password;
    private AlertDialog.Builder alert;
    private String user,pass;
    private boolean fuser = false;
    static String NAME ="";
    private static final String TAG = Home.class.getSimpleName();
    private static  String URL_CONSULTA_lOGIN ="http://192.168.1.3/coleccionistas/login.php";

    private RequestQueue request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        name = (EditText)findViewById(R.id.editTextName);
        mail = (EditText)findViewById(R.id.editTextMail);
        password = (EditText)findViewById(R.id.editTextPassword);
        request = Volley.newRequestQueue(this);

        alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.icon);
        alert.setTitle("ERROR");
        alert.setCancelable(true);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (fuser){
                    password.setText("");
                } else {
                    name.setText("");
                    mail.setText("");
                    password.setText("");
                }
            }
        });
    }


public void SingIng (View view){

    Intent intend = new Intent(getApplicationContext(),RegistrarUsuario.class );

    startActivity(intend);

}



    public void Longin(View v){
        String usuario = name.getText().toString();
        String contrasena = password.getText().toString();

        URL_CONSULTA_lOGIN  = "http://192.168.1.3/coleccionistas/login.php?usuario="+usuario+"&contrasena="+contrasena;


        request.add(
                new JsonObjectRequest(Request.Method.POST, URL_CONSULTA_lOGIN,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray jsonArray;
                                try {
                                    jsonArray = response.getJSONArray("Usuarios");
                                    for(int i=0; i<jsonArray.length(); i++){
                                        try{
                                            JSONObject objeto= jsonArray.getJSONObject(i);

                                                   String name = objeto.getString("usuario_nombre");
                                                   String lastname = objeto.getString("usuario_apellido");

                                                Log.d(TAG, "Nombre " + objeto.getString("usuario_nombre"));
                                                Log.d(TAG, "Apellido " + objeto.getString("usuario_apellido"));
                                                Log.d(TAG, "User " + objeto.getString("usuario_usuario"));
                                            Intent intend = new Intent(getApplicationContext(),Notices.class );

                                            intend.putExtra("Nombre",name);
                                            intend.putExtra("Apellido",lastname);
                                            startActivity(intend);


                                        }catch (JSONException e) {
                                            Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                        }
                                    }
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),
                                        "Algo va mal",Toast.LENGTH_SHORT).show();
                                //Log.d(TAG, "ERROR VOLLEY: " + error.getMessage());
                            }
                        }
                ));
    }
}
