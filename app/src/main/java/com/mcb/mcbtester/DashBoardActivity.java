package com.mcb.mcbtester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DashBoardActivity extends AppCompatActivity {

    ListView listView;
    ImageView Add;

    int[] colors = {R.color.lightSkin, R.color.periwinkle, R.color.palePink, R.color.skinColor};
    int[] images = {R.drawable.testerman, R.drawable.testerman2, R.drawable.testerman3, R.drawable.testerman4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        listView = (ListView)findViewById(R.id.lvdbalist);
        Add = (ImageView)findViewById(R.id.ivdbaadd);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestConfigActivity.class);
                startActivity(intent);
            }
        });

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.mcb_main_layout, null);

            ImageView Icon = (ImageView)view.findViewById(R.id.ivmrlicon);
            TextView MCBName = (TextView) view.findViewById(R.id.tvmmlmcbname);
            TextView MCBDate = (TextView) view.findViewById(R.id.tvmmltestdate);
            TextView MCBTime = (TextView) view.findViewById(R.id.tvmmltesttime);
            RelativeLayout Main = (RelativeLayout)view.findViewById(R.id.rlmrlmain);

            Main.setBackgroundTintList(getResources().getColorStateList(colors[i%4]));
            MCBName.setText("MCB "+(i+1));
            Icon.setImageResource(images[i%4]);

            Main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MCBTestsActivity.class);
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}