package com.javapapers.android.sqlitestorageoption;

import java.util.ArrayList;
import java.util.HashMap;

import com.javapapers.android.sqlitestorageoption.R;
import com.javapapers.android.sqlitestorageoption.DBController;
import com.javapapers.android.sqlitestorageoption.NewKasus;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MainActivity extends ListActivity {
	Intent intent;
	TextView kasusId;
	DBController controller = new DBController(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<HashMap<String, String>> kasusList =  controller.getAllKasus();
		if(kasusList.size()!=0) {
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				  @Override 
				  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					  kasusId = (TextView) view.findViewById(R.id.kasusId);
					  String valKasusId = kasusId.getText().toString();
					  Intent  objIndent = new Intent(getApplicationContext(),EditKasus.class);
					  objIndent.putExtra("kasusId", valKasusId);
					  startActivity(objIndent); 
				  }
			}); 
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,kasusList, R.layout.view_kasus_entry, new String[] { "kasusId","kasusName"}, new int[] {R.id.kasusId, R.id.kasusName});
			setListAdapter(adapter);
		}
	}
	public void showAddForm(View view) {
		Intent objIntent = new Intent(getApplicationContext(), NewKasus.class);
		startActivity(objIntent);
	}
}
