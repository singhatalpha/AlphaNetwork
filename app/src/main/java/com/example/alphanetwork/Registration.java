package com.example.alphanetwork;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Registration extends Activity implements OnClickListener, OnItemSelectedListener


{
    // Variable Declaration should be in onCreate()
    private Button mSubmit;
    private Button mCancel;

    private EditText mFname;
    private EditText mLname;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    private Spinner mGender;
    private String Gen;

    protected DBHelper DB = new DBHelper(Registration.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Assignment of UI fields to the variables
        mSubmit = findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);

        mCancel = findViewById(R.id.cancel);
        mCancel.setOnClickListener(this);

        mFname = findViewById(R.id.efname);
        mLname = findViewById(R.id.elname);

        mUsername = findViewById(R.id.reuname);
        mPassword = findViewById(R.id.repass);
        mEmail = findViewById(R.id.eemail);


        mGender = findViewById(R.id.spinner1);

        // Spinner method to read the on selected value
        ArrayAdapter<State> spinnerArrayAdapter = new ArrayAdapter<State>(this,
                android.R.layout.simple_spinner_item, new State[] {
                new State("Male"),
                new State("Female")});
        mGender.setAdapter(spinnerArrayAdapter);
        mGender.setOnItemSelectedListener(this);
    }



    public void onClick(View v)
    {

        switch(v.getId()){

            case R.id.cancel:
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
                break;

            case R.id.submit:


                String fname = mFname.getText().toString();
                String lname = mLname.getText().toString();

                String uname = mUsername.getText().toString();
                String pass = mPassword.getText().toString();
                String email = mEmail.getText().toString();


                boolean invalid = false;

                if(fname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your Firstname", Toast.LENGTH_SHORT).show();
                }
                else

                if(lname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Lastname", Toast.LENGTH_SHORT).show();
                }
                else

                if(uname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Username", Toast.LENGTH_SHORT).show();
                }
                else


                if(pass.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();

                }
                else
                if(email.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
                }
                else
                if(invalid == false)
                {
                    addEntry(fname, lname, Gen, uname, pass, email);
                    Intent i_register = new Intent(Registration.this, LoginActivity.class);
                    startActivity(i_register);
                }

                break;
        }
    }





    public void onDestroy()
    {
        super.onDestroy();
        DB.close();
    }



    private void addEntry(String fname, String lname, String Gen, String uname, String pass, String email)
    {

        SQLiteDatabase db = DB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstname", fname);
        values.put("lastname", lname);
        values.put("gender", Gen);
        values.put("username", uname);
        values.put("password", pass);
        values.put("email", email);

        try
        {
            db.insert(DBHelper.DATABASE_TABLE_NAME, null, values);

            Toast.makeText(getApplicationContext(), "your details submitted Successfully...", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // Get the currently selected State object from the spinner
        State st = (State)mGender.getSelectedItem();

        // Show it via a toast
        toastState( "onItemSelected", st );
    }


    public void toastState(String name, State st)
    {
        if ( st != null )
        {
            Gen = st.name;
            //Toast.makeText(getBaseContext(), Gen, Toast.LENGTH_SHORT).show();

        }

    }


    public void onNothingSelected(AdapterView<?> arg0) {

        //  Auto-generated method stub

    }

}

