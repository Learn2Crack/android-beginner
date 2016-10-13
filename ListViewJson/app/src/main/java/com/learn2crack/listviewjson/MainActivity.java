package com.learn2crack.listviewjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.learn2crack.listviewjson.model.AndroidVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;

    public static final String URL = "https://api.learn2crack.com/android/jsonandroid/";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_VER = "ver";
    private static final String KEY_NAME = "name";
    private static final String KEY_API = "api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<AndroidVersion> androidList) {

        for (AndroidVersion android : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_VER, android.getVer());
            map.put(KEY_NAME, android.getName());
            map.put(KEY_API, android.getApi());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_NAME),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_VER, KEY_NAME, KEY_API },
                new int[] { R.id.version,R.id.name, R.id.api });

        mListView.setAdapter(adapter);

    }
}
