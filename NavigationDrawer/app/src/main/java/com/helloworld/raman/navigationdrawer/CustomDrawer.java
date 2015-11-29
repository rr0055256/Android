package com.helloworld.raman.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raman on 4/11/15.
 */

public class CustomDrawer extends ArrayAdapter<String> {
    private String[] cricketer_names;
    private Integer[] cricketerImages;
    private Activity context;

    public CustomDrawer(Activity context,String[] cricketer_names,Integer[] cricketerImages) {
        super(context, 0);
        this.cricketer_names = cricketer_names;
        this.cricketerImages = cricketerImages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cricketer_names.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();

            convertView = inflater.inflate(R.layout.drawer_list_item, null, true);
        }
        ImageView cricketerImage = (ImageView) convertView.findViewById(R.id.cricketerImage);

        TextView cricTextView = (TextView) convertView.findViewById(R.id.cricketerNames);

        cricTextView.setText(cricketer_names[position]);

        cricketerImage.setImageResource(cricketerImages[position]);

        return convertView;
    }
}
