package edu.skku.map.pa1;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    Bitmap bit_img[];
    int now;

    public ImageAdapter(Context c, Bitmap img[], int now) {
        context = c;
        bit_img = img;
        this.now = now;
    }

    @Override
    public int getCount() {
        return bit_img.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        }

        else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(bit_img[position]);
        imageView.setId(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnClickInAdapter)context).OnclickInAdapter(position, now);
            }
        });
        return imageView;
    }

    public interface OnClickInAdapter {
        void OnclickInAdapter(int position, int now);
    }
}
