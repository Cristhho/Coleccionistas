package com.ayc.coleccionistas;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarUsuario extends Activity {

    private EditText nameE,userE,lastnameE, mailE, passwordE,password2E;
    private AlertDialog.Builder alert;
    private String user,pass,pass2,lastname,mail,name;
    private boolean fuser = false;
    static String NAME ="";
    private static final String TAG = Home.class.getSimpleName();
    private RequestQueue request;

    private static  String URL_CONSULTA_SINGIN ="http://108.170.38.42/coleccionistas/singin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        request = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        nameE = (EditText)findViewById(R.id.editTextnameE);
        userE = (EditText)findViewById(R.id.editTextUserE);
        lastnameE = (EditText)findViewById(R.id.editTextLastnameE);
        mailE = (EditText)findViewById(R.id.editTextmailE);
        passwordE = (EditText)findViewById(R.id.editTextpassE);
        password2E = (EditText)findViewById(R.id.editTextpass2E);

        //mail = (EditText)findViewById(R.id.editTextMail);
     //   password = (EditText)findViewById(R.id.editTextPassword);
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
                    intend.putExtra("Nombre","");
                    //intend.putExtra("Apellido",lastname);
                    startActivity(intend);
                    break;
                case "2":
                    Toast.makeText(getApplicationContext(),
                            mensaje,Toast.LENGTH_SHORT).show();
                    break;
                case "3":
                    Toast.makeText(getApplicationContext(),
                            mensaje,Toast.LENGTH_SHORT).show();
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void Singin(View v){
       // user,pass,lastname,mail,name;






        user = userE.getText().toString() ;
        lastname  =  lastnameE.getText().toString() ;
        mail = mailE.getText().toString() ;
        name = nameE.getText().toString() ;
        pass =  passwordE.getText().toString() ;
        pass2 =  password2E.getText().toString() ;
       // http://192.168.1.3/coleccionistas/singin.php?nombre=Alberto&apellido=Ordonez+Urgiles&usuarioR=Alberto&contrasena=1234&contrasena2=1234&correo=alberto%40gmail.com

        URL_CONSULTA_SINGIN  = "http://108.170.38.42/coleccionistas/singin.php?nombre="+name+"&apellido="+lastname+"&usuarioR="+user+"&contrasena="+pass+"&contrasena2="+pass2+"&correo="+mail;
        System.out.println(URL_CONSULTA_SINGIN );

        request.add(
                new StringRequest(Request.Method.POST, URL_CONSULTA_SINGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject oJson = new JSONObject(response);
                                    Log.d(TAG, oJson.toString());
                                    procesarRespuesta(oJson);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "ERROR VOLLEY: " + error.getMessage());
                            }
                        }
                ));
    }
}
