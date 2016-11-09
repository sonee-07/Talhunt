package com.example.lenovo.talenthunt;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.TabActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;


public class NewTabActivity extends AppCompatActivity {

    public TabHost mytabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tab);
        // Recuperation du TabHost
        mytabhost =(TabHost) findViewById(R.id.TabHost01);
// Before adding tabs, it is imperative to call the method setup()
        mytabhost.setup();

// Adding tabs
        // tab1 settings
        TabHost.TabSpec spec = mytabhost.newTabSpec("tab_creation");
        // text and image of tab
        spec.setIndicator("Create adresse", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme()));
        // specify layout of tab
        spec.setContent(R.id.onglet1);
        // adding tab in TabHost
        mytabhost.addTab(spec);

// otherwise :
        mytabhost.addTab(mytabhost.newTabSpec("tab_inser").setIndicator("Delete", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme())));

        mytabhost.addTab(mytabhost.newTabSpec("tab_affiche").setIndicator("Show All", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme())));

        ListView lv=(ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.activity_login);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
