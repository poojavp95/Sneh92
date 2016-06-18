package com.sneh92.sneh92;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private SearchView searchView;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner) findViewById(R.id.spincat);
        String cat[]={"Name","City","State","Country"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cat);
        spinner.setAdapter(arrayAdapter);
        searchView=(SearchView)findViewById(R.id.edit_search);
        imageButton=(ImageButton)findViewById(R.id.search_btn);
        Fragment fragment = null;
        fragment = new SearchList();
        Bundle bundle = new Bundle();
        bundle.putString("cat","NULL");
        bundle.putString("search", "NULL");
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frag_holder, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void querySearch(View view) {
        String cat=spinner.getSelectedItem().toString();
        String se=searchView.getQuery().toString();
        Fragment fragment = null;
        fragment = new SearchList();
        Bundle bundle = new Bundle();
        bundle.putString("cat",cat);
        bundle.putString("search",se);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frag_holder, fragment).commit();

    }
}
