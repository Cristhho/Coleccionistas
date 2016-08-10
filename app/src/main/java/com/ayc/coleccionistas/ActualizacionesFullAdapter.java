package com.ayc.coleccionistas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by HOME on 10/08/2016.
 */
public class ActualizacionesFullAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] images = {
            R.drawable.act1,
            R.drawable.act2,
            R.drawable.act3,
            R.drawable.esc1,
            R.drawable.esc2,
            R.drawable.esc3
    };

    public ActualizacionesFullAdapter(Context c){
        mContext = c;
    }

    public int getCount(){
        return images.length;
    }

    public Integer getItem(int position){
        return images[position];
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(images[position]);
        return imageView;

    }
}
