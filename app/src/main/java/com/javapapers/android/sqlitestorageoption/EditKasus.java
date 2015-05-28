package com.javapapers.android.sqlitestorageoption;

import java.util.Calendar;

import java.util.HashMap;

import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;


import com.javapapers.android.sqlitestorageoption.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class EditKasus extends Activity{
    EditText kasusName;
    DBController controller = new DBController(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kasus);
        kasusName = (EditText) findViewById(R.id.kasusName);
        Intent objIntent = getIntent();
        String kasusId = objIntent.getStringExtra("kasusId");
        Log.d("Reading: ", "Reading all contacts..");
        HashMap<String, String> kasusList = controller.getKasusInfo(kasusId);
        Log.d("kasusName",kasusList.get("kasusName"));
        if(kasusList.size()!=0) {
            kasusName.setText(kasusList.get("kasusName"));
        }
    }
    public void editKasus(View view) {
        HashMap<String, String> queryValues =  new  HashMap<String, String>();
        kasusName = (EditText) findViewById(R.id.kasusName);
        Intent objIntent = getIntent();
        String kasusId = objIntent.getStringExtra("kasusId");
        queryValues.put("kasusId", kasusId);
        queryValues.put("kasusName", kasusName.getText().toString());

        controller.updateKasus(queryValues);
        this.callHomeActivity(view);

    }
    public void removeKasus(View view) {
        Intent objIntent = getIntent();
        String kasusId = objIntent.getStringExtra("kasusId");
        controller.deleteKasus(kasusId);
        this.callHomeActivity(view);

    }
    public void callHomeActivity(View view) {
        Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(objIntent);
    }
}

