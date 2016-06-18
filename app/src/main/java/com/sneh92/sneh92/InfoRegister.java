package com.sneh92.sneh92;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.HashMap;

public class InfoRegister extends AppCompatActivity {
    private EditText txtfname;
    private EditText txtmname;
    private EditText txtlname;
    private EditText txtpfname;
    private EditText txtpmname;
    private EditText txtplname;
    private EditText dob;
    private Spinner bg;
    private Spinner gender;
    private EditText edu;
    private EditText prof;
    private EditText address;
    private EditText city;
    private EditText state;
    private EditText country;
    private EditText tele;
    private EditText mob1;
    private EditText mob2;
    private EditText whatsapp;
    private EditText email;
    private EditText fb;
    private EditText txtsfname;
    private EditText txtsmname;
    private EditText txtslname;
    private EditText sprof;
    private EditText annv;
    private static final String INFO_URL="http://sneh92.esy.es/info.php";
    private static final String REGISTER_URL="http://sneh92.esy.es/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_register);
        txtfname=(EditText) findViewById(R.id.edit_fname);
        txtmname=(EditText) findViewById(R.id.edit_mname);
        txtlname=(EditText) findViewById(R.id.edit_lname);
        txtpfname=(EditText) findViewById(R.id.edit_pfname);
        txtpmname=(EditText) findViewById(R.id.edit_pmname);
        txtplname=(EditText) findViewById(R.id.edit_plname);
        dob=(EditText) findViewById(R.id.edit_dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(InfoRegister.this,
                        new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }
            class mDateSetListener implements DatePickerDialog.OnDateSetListener {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    // getCalender();
                    int mYear = year;
                    int mMonth = monthOfYear;
                    int mDay = dayOfMonth;
                    dob.setText(new StringBuilder()
                            // Month is 0 based so add 1
                            .append(mMonth + 1).append("/").append(mDay).append("/")
                            .append(mYear).append(" "));


                }
            }

        });
        bg=(Spinner) findViewById(R.id.spin_bg);
        String bloodrgp[]={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bloodrgp);
        bg.setAdapter(arrayAdapter);
        gender=(Spinner)findViewById(R.id.spin_gender);
        String g[]={"Male","Female"};
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,g);
        gender.setAdapter(arrayAdapter1);
        edu=(EditText)findViewById(R.id.edit_edu);
        prof=(EditText)findViewById(R.id.edit_prof);
        address=(EditText)findViewById(R.id.edit_address);
        city=(EditText)findViewById(R.id.edit_city);
        state=(EditText)findViewById(R.id.edit_state);
        country=(EditText)findViewById(R.id.edit_country);
        tele=(EditText)findViewById(R.id.edit_tele);
        mob1=(EditText)findViewById(R.id.edit_mob1);
        mob2=(EditText)findViewById(R.id.edit_mob2);
        whatsapp=(EditText)findViewById(R.id.edit_whatsapp);
        email=(EditText)findViewById(R.id.edit_email);
        fb=(EditText)findViewById(R.id.edit_fb);
        txtsfname=(EditText) findViewById(R.id.edit_sfname);
        txtsmname=(EditText) findViewById(R.id.edit_smname);
        txtslname=(EditText) findViewById(R.id.edit_slname);
        sprof=(EditText) findViewById(R.id.edit_sprof);
        annv=(EditText)findViewById(R.id.edit_anniv_date);
        annv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(InfoRegister.this,
                        new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }
            class mDateSetListener implements DatePickerDialog.OnDateSetListener {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    // getCalender();
                    int mYear = year;
                    int mMonth = monthOfYear;
                    int mDay = dayOfMonth;
                    dob.setText(new StringBuilder()
                            // Month is 0 based so add 1
                            .append(mMonth + 1).append("/").append(mDay).append("/")
                            .append(mYear).append(" "));


                }
            }
        });
       // initialize();

    }

    private void initialize() {
    class UserInfo extends AsyncTask<Void,Void,String>
    {
        ProgressDialog loading;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(InfoRegister.this,"Please Wait",null,true,true);
        }


        @Override
        protected String doInBackground(Void... params) {
            SharedPreferences sharedPreferences=getSharedPreferences("pref", Context.MODE_PRIVATE);
            String value=sharedPreferences.getString("username","null");
            HashMap<String,String> data = new HashMap<>();
            data.put("username",value);

            ConnectionClass cc = new ConnectionClass();

            String result=cc.sendPostRequest(INFO_URL,data);
            return result;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            loading.dismiss();
            Log.d("string", s);
            String values[]=s.split(";;");
            txtfname.setText(values[0]);
            txtmname.setText(values[1]);
            txtlname.setText(values[2]);
            txtpfname.setText(values[3]);
            txtpmname.setText(values[4]);
            txtplname.setText(values[5]);
            dob.setText(values[6]);
            bg.setSelection(1);   //TODO
            gender.setSelection(1);
            edu.setText(values[9]);
            prof.setText(values[10]);
            address.setText(values[11]);
            city.setText(values[12]);
            state.setText(values[13]);
            country.setText(values[14]);
            tele.setText(values[15]);
            mob1.setText(values[16]);
            mob2.setText(values[17]);
            whatsapp.setText(values[18]);
            email.setText(values[19]);
            fb.setText(values[20]);
            txtsfname.setText(values[21]);
            txtsmname.setText(values[22]);
            txtslname.setText(values[23]);
            sprof.setText(values[24]);
            annv.setText(values[25]);
        }
    }
        UserInfo userInfo=new UserInfo();
        userInfo.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_register, menu);
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

    public void register(View view) {
        class RegisterClass extends AsyncTask<Void,Void,String>
        {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InfoRegister.this,"Please Wait",null,true,true);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> data=new HashMap<>();


                ConnectionClass cc=new ConnectionClass();
                String result=cc.sendPostRequest(REGISTER_URL, data);
                return result;
            }
        }
    }
}
