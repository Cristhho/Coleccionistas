package com.ayc.coleccionistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Item_Description extends Activity {

    private ImageView itemImg;
    private TextView itemName,itemDescrip,itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__description);
        itemImg = (ImageView)findViewById(R.id.imgItem);
        EscogidosFullAdapter efa = new EscogidosFullAdapter(this);
        ActualizacionesFullAdapter afa = new ActualizacionesFullAdapter(this);
        Intent i = getIntent();
        int id = i.getExtras().getInt("id");
        String name = i.getExtras().getString("name");
        String description = i.getExtras().getString("desc");
        String price = i.getExtras().getString("price");
        String src = i.getExtras().getString("source");
        if(src.equals("rec")){
            itemImg.setImageResource(efa.getItem(id));
        } else if (src.equals("actual")){
            itemImg.setImageResource(afa.getItem(id));
        }
        itemName = (TextView)findViewById(R.id.textName);
        itemDescrip = (TextView)findViewById(R.id.textDescription);
        itemPrice = (TextView)findViewById(R.id.textPrice);
        itemName.setText(name);
        itemDescrip.setText(description);
        itemPrice.setText(price);
    }
}
