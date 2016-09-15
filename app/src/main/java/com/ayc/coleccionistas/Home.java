package com.ayc.coleccionistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends Activity {

    private EditText name, mail, password;
    private AlertDialog.Builder alert;
    private String user,pass;
    private boolean fuser = false;
    static String NAME ="";
    private static final String TAG = Home.class.getSimpleName();
    private static final String URL_CONSULTA_lOGIN ="http://10.0.2.2/coleccionistas/login.php";

    private RequestQueue request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        name = (EditText)findViewById(R.id.editTextName);

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
        final String usuario = name.getText().toString();
        final String contrasena = password.getText().toString();

        request.add(
                new StringRequest(Request.Method.POST, URL_CONSULTA_lOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject oJson = new JSONObject(response);
                                    procesarRespuesta(oJson);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alert.setMessage("Usuario o contraseña incorrectos");
                                alert.show();
                            }
                        }
                ){
                    @Override
                    protected Map<String,String> getParams()throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<>();
                        parameters.put("usuario",usuario);
                        parameters.put("contrasena",contrasena);
                        return parameters;
                    }
                }
        );
    }

    private void procesarRespuesta(JSONObject response){
        JSONArray jsonArray;
        try {
            jsonArray = response.getJSONArray("Usuarios");
            for(int i=0; i<jsonArray.length(); i++){
                try{
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    String name = objeto.getString("usuario_usuario");
                    String id = objeto.getString("usuario_id");

                    Intent intend = new Intent(getApplicationContext(),Notices.class );

                    intend.putExtra("User",name);
                    intend.putExtra("userid",id);
                    startActivity(intend);


                }catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
