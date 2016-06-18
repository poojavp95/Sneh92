package com.sneh92.sneh92;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

import static android.graphics.Color.parseColor;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchList extends Fragment {

public static final String SEARCH_URL ="http://sneh92.esy.es/search.php";
    private ListView listView;

    public SearchList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String cat="NULL",search="NULL";
        View view= inflater.inflate(R.layout.fragment_search_list, container, false);
        listView=(ListView)view.findViewById(R.id.list_search);

        Bundle bundle=getArguments();
        if (bundle != null) {
            cat = bundle.getString("cat", "NULL");
            search=bundle.getString("search","NULL");
        }
        display(cat,search);
        return  view;
    }
    public void display(final String cat,final String search)
    {
        class DispList extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Please Wait",null,true,true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("string", s);
                loading.dismiss();
                final String [] displist;
                displist=s.split(" ");
                Log.d("check1", Integer.toString(displist.length));
                listView.setAdapter(new CustomAdapter(getActivity(), displist));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Fragment fragment = null;
                        fragment = new DisplayDetail();
                        Bundle bundle = new Bundle();
                        bundle.putString("value", displist[position]);
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();

                        fragmentManager.beginTransaction().addToBackStack(null)
                                .replace(R.id.frag_holder, fragment).commit();

                    }
                });
            }
            @Override
            protected String doInBackground(String...params) {
                HashMap<String, String> data = new HashMap<>();
                data.put("cat", cat);
                data.put("search",search);
                ConnectionClass ruc = new ConnectionClass();
                String result = ruc.sendPostRequest(SEARCH_URL,data);
                return result;
            }
        }
        DispList d=new DispList();
        d.execute(cat,search);
    }


}
