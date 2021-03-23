package edu.skku.map.pa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnClickInAdapter {


    int now = 3;
    int blank_3x3 = 8;
    int blank_4x4 = 15;
    Bitmap img_3x3[] = new Bitmap[9];
    Bitmap img_4x4[] = new Bitmap[16];
    Bitmap sf_img_3x3[] = new Bitmap[9];
    Bitmap sf_img_4x4[] = new Bitmap[16];
    LinearLayout container;

    @Override
    public void OnclickInAdapter(int position, int now) {
        if (now == 3 && (
                (blank_3x3 == 0 && (position == 1 || position == 3)) ||
                (blank_3x3 == 1 && (position == 0 || position == 2 || position == 4)) ||
                (blank_3x3 == 2 && (position == 1 || position == 5)) ||
                (blank_3x3 == 3 && (position == 0 || position == 4 || position == 6)) ||
                (blank_3x3 == 4 && (position == 1 || position == 3 || position == 5 || position == 7)) ||
                (blank_3x3 == 5 && (position == 2 || position == 4 || position == 8)) ||
                (blank_3x3 == 6 && (position == 3 || position == 7)) ||
                (blank_3x3 == 7 && (position == 4 || position == 6 || position == 8)) ||
                (blank_3x3 == 8 && (position == 5 || position == 7))))
        {
            ImageView origin_imgView = (ImageView) findViewById(position);
            ImageView blank_imgView = (ImageView) findViewById(blank_3x3);
            origin_imgView.setImageBitmap(sf_img_3x3[blank_3x3]);
            blank_imgView.setImageBitmap(sf_img_3x3[position]);
            Bitmap temp = sf_img_3x3[position];
            sf_img_3x3[position] = sf_img_3x3[blank_3x3];
            sf_img_3x3[blank_3x3] = temp;
            blank_3x3 = position;
            for (int i=0;i<9;i++) {
                if (img_3x3[i]!=sf_img_3x3[i]) break;
                if (i==8) {
                    Toast.makeText(getApplicationContext(), "FINISHED", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if (now == 4 && ((blank_4x4 == 0 && (position == 1 || position == 4)) ||
                (blank_4x4 == 1 && (position == 0 || position == 2 || position == 5)) ||
                (blank_4x4 == 2 && (position == 1 || position == 3 || position == 6)) ||
                (blank_4x4 == 3 && (position == 2 || position == 7)) ||
                (blank_4x4 == 4 && (position == 0 || position == 5 || position == 8)) ||
                (blank_4x4 == 5 && (position == 1 || position == 4 || position == 6 || position == 9)) ||
                (blank_4x4 == 6 && (position == 2 || position == 5 || position == 7 || position == 10)) ||
                (blank_4x4 == 7 && (position == 3 || position == 6 || position == 11)) ||
                (blank_4x4 == 8 && (position == 4 || position == 9 || position == 12)) ||
                (blank_4x4 == 9 && (position == 5 || position == 8 || position == 10 || position == 13)) ||
                (blank_4x4 == 10 && (position == 6 || position == 9 || position == 11 || position == 14)) ||
                (blank_4x4 == 11 && (position == 7 || position == 10 || position == 15)) ||
                (blank_4x4 == 12 && (position == 8 || position == 13)) ||
                (blank_4x4 == 13 && (position == 9 || position == 12 || position == 14)) ||
                (blank_4x4 == 14 && (position == 10 || position == 13 || position == 15) ||
                (blank_4x4 == 15 && (position == 11 || position == 14))
                        )))
        {
            ImageView origin_imgView = (ImageView) findViewById(position);
            ImageView blank_imgView = (ImageView) findViewById(blank_4x4);
            origin_imgView.setImageBitmap(sf_img_4x4[blank_4x4]);
            blank_imgView.setImageBitmap(sf_img_4x4[position]);
            Bitmap temp = sf_img_4x4[position];
            sf_img_4x4[position] = sf_img_4x4[blank_4x4];
            sf_img_4x4[blank_4x4] = temp;
            blank_4x4 = position;
            for (int i=0;i<16;i++) {
                if (img_4x4[i]!=sf_img_4x4[i]) break;
                if (i==15) {
                    Toast.makeText(getApplicationContext(), "FINISHED", Toast.LENGTH_LONG).show();
                }
            }
        }
//        System.out.println("position = " + position);
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.linearLayout);

        Bitmap img_origin = BitmapFactory.decodeResource(getResources(), R.drawable.puzzle_image);
        Bitmap blank = BitmapFactory.decodeResource(getResources(), R.drawable.white);

        int width = img_origin.getWidth();
        int height = img_origin.getHeight();
        width = 750;
        height = 750;
        img_origin = Bitmap.createScaledBitmap(img_origin, width, height, true);

        ArrayList<Integer> sf_list_3x3 = new ArrayList();
        ArrayList<Integer> sf_list_4x4 = new ArrayList();
        for (int i = 0; i < 9; i++) {
            sf_list_3x3.add(i);
        }
        for (int i = 0; i < 16; i++) {
            sf_list_4x4.add(i);
        }

        for (int n = 0; n < 9; n++) {
            int i = n / 3;
            int j = n % 3;
            img_3x3[n] = Bitmap.createBitmap(img_origin, j * width / 3, i * height / 3, width / 3, height / 3);
        }
        img_3x3[8] = Bitmap.createBitmap(blank, 0, 0, width / 3, height / 3);

        for (int n = 0; n < 16; n++) {
            int i = n / 4;
            int j = n % 4;
            img_4x4[n] = Bitmap.createBitmap(img_origin, j * width / 4, i * height / 4, width / 4, height / 4);
        }
        img_4x4[15] = Bitmap.createBitmap(blank, 0, 0, width / 4, height / 4);

        container.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_3x3, container, true);
        sf_list_3x3.clear();
        for (int i = 0; i < 9; i++) {
            sf_list_3x3.add(i);
            sf_img_3x3[i] = img_3x3[i];
        }
        ImageAdapter adapter_3x3 = new ImageAdapter(MainActivity.this, sf_img_3x3, now);
        GridView gridView_3x3 = (GridView) findViewById(R.id.gridView_3x3);
        gridView_3x3.setAdapter(adapter_3x3);

        blank_3x3 = 8;

        Button button_3x3 = (Button) findViewById(R.id.button_3x3);

        button_3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now = 3;
                container.removeAllViews();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.layout_3x3, container, true);
                sf_list_3x3.clear();
                for (int i = 0; i < 9; i++) {
                    sf_list_3x3.add(i);
                    sf_img_3x3[i] = img_3x3[i];
                }
                ImageAdapter adapter_3x3 = new ImageAdapter(MainActivity.this, sf_img_3x3, now);
                GridView gridView_3x3 = (GridView) findViewById(R.id.gridView_3x3);
                gridView_3x3.setAdapter(adapter_3x3);

                blank_3x3 = 8;
            }
        });

        Button button_4x4 = (Button) findViewById(R.id.button_4x4);

        button_4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now = 4;
                container.removeAllViews();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.layout_4x4, container, true);
                sf_list_4x4.clear();
                for (int i = 0; i < 16; i++) {
                    sf_list_4x4.add(i);
                    sf_img_4x4[i] = img_4x4[i];
                }
                ImageAdapter adapter_4x4 = new ImageAdapter(MainActivity.this, sf_img_4x4, now);
                GridView gridView_4x4 = (GridView) findViewById(R.id.gridView_4x4);
                gridView_4x4.setAdapter(adapter_4x4);

                blank_4x4 = 15;
            }
        });

        Button shuffle = (Button) findViewById(R.id.shuffle);

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (now == 3) {
                    Collections.shuffle(sf_list_3x3, new Random(System.currentTimeMillis()));
                    for (int i = 0; i < sf_img_3x3.length; i++) {
                        sf_img_3x3[i] = img_3x3[sf_list_3x3.get(i)];
                        if (sf_list_3x3.get(i) == 8) blank_3x3 = i;
                    }
                    inflater.inflate(R.layout.layout_3x3, container, true);
                    ImageAdapter adapter_3x3 = new ImageAdapter(MainActivity.this, sf_img_3x3, now);
                    GridView gridView_3x3 = (GridView) findViewById(R.id.gridView_3x3);
                    gridView_3x3.setAdapter(adapter_3x3);
                } else {
                    Collections.shuffle(sf_list_4x4, new Random(System.currentTimeMillis()));
                    for (int i = 0; i < sf_img_4x4.length; i++) {
                        sf_img_4x4[i] = img_4x4[sf_list_4x4.get(i)];
                        if (sf_list_4x4.get(i) == 15) blank_4x4 = i;
                    }
                    inflater.inflate(R.layout.layout_4x4, container, true);
                    ImageAdapter adapter_4x4 = new ImageAdapter(MainActivity.this, sf_img_4x4, now);
                    GridView gridView_4x4 = (GridView) findViewById(R.id.gridView_4x4);
                    gridView_4x4.setAdapter(adapter_4x4);
                }

            }
        });
    }
}