package com.example.whip.listviewsuite2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whip.listviewsuite2.toutv.Lineups;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    MyAdapter adapter;
    ListView list;
    Lineups films;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview1);

        Intent intent = getIntent();
        type = intent.getStringExtra("TYPEEMISSION");


        RunAPI run = new RunAPI();
        run.execute(); //Va aller dans le do in background automatiquement

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(MainActivity.this,films.LineupItems.get(i).GenreTitle,Toast.LENGTH_SHORT).show();
        String titre = films.LineupItems.get(i).GenreTitle;
        String url = films.LineupItems.get(i).ImagePlayerNormalC;
        String desc = films.LineupItems.get(i).Details.Description;

        Intent intent = new Intent(this,DetailsActivity.class);

        intent.putExtra("TITLE",titre);
        intent.putExtra("URL",url);
        intent.putExtra("DESC",desc);

        startActivity(intent);
    }

    public class RunAPI extends AsyncTask <String, Object, Lineups>{

        @Override
        protected Lineups doInBackground(String... strings) {
            WebAPI web = new WebAPI(type);
            try{
                films = web.run();
            }catch (IOException e){
                e.printStackTrace();
            }
            return films;
        }

        @Override
        protected void onPostExecute(Lineups lineups) {
            super.onPostExecute(lineups);
            adapter = new MyAdapter();
            list.setAdapter(adapter);

            list.setOnItemClickListener(MainActivity.this);
        }
    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public MyAdapter(){
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        //Changer ça pour le crash array list out of bounds
        @Override
        public int getCount() {
            return films.LineupItems.size();
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

            if (view==null){
                view = inflater.inflate(R.layout.rangee,viewGroup,false);
            }

            if (i%2==0){
                view.setBackgroundColor(Color.LTGRAY);
            }else{
                view.setBackgroundColor(Color.WHITE);
            }

            TextView tv = (TextView) view.findViewById(R.id.textview1);
            ImageView im = (ImageView) view.findViewById(R.id.imgview);

            String title = films.LineupItems.get(i).GenreTitle;
            String url = films.LineupItems.get(i).ImagePlayerNormalC;
            tv.setText(title);

            //Picasso permet placer image dans un view (librairie) à partir URL
            Picasso.with(getApplicationContext()).load(url).into(im);

            return view;
        }
    }
}
