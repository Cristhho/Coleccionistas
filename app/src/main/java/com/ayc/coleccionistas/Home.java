package com.ayc.coleccionistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Home extends Activity {

    private EditText name, mail, password;
    private AlertDialog.Builder alert;
    private String user,pass;
    private boolean fuser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        name = (EditText)findViewById(R.id.editTextName);
        mail = (EditText)findViewById(R.id.editTextMail);
        password = (EditText)findViewById(R.id.editTextPassword);
        user = "cristhian";
        pass = "12345";
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
        if(name.getText().toString().equals("")){
            alert.setMessage("Ingrese un nombre de usuario");
            alert.create();
            alert.show();
        } else if (password.getText().toString().equals(null) || password.getText().toString().equals("")){
            fuser = true;
            alert.setMessage("Ingrese su contraseña");
            alert.create();
            alert.show();
        } else if (!name.getText().toString().equals(user)){
            alert.setMessage("Usuario incorrecto");
            alert.create();
            alert.show();
        } else if(!password.getText().toString().equals(pass)){
            fuser = true;
            alert.setMessage("Contraseña incorrecta");
            alert.create();
            alert.show();
        } else {
            Intent i = new Intent(this,Notices.class );
            startActivity(i);
        }
    }
}
