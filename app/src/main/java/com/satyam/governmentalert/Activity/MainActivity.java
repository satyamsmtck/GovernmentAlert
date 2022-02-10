package com.satyam.governmentalert.Activity;

import static com.satyam.governmentalert.Helper.ConstantClass.CAT_NAMES;
import static com.satyam.governmentalert.Helper.ConstantClass.DETAILS;
import static com.satyam.governmentalert.Helper.ConstantClass.FUNCTIONAL_AREA;
import static com.satyam.governmentalert.Helper.ConstantClass.ID;
import static com.satyam.governmentalert.Helper.ConstantClass.IMPORTANT;
import static com.satyam.governmentalert.Helper.ConstantClass.INDEXED_TYPE;
import static com.satyam.governmentalert.Helper.ConstantClass.LANGUAGE_ID;
import static com.satyam.governmentalert.Helper.ConstantClass.LOCATION;
import static com.satyam.governmentalert.Helper.ConstantClass.NOTIFICATION;
import static com.satyam.governmentalert.Helper.ConstantClass.ORGANIZATION;
import static com.satyam.governmentalert.Helper.ConstantClass.PROFILE_TYPE;
import static com.satyam.governmentalert.Helper.ConstantClass.PUBLISH_DATE;
import static com.satyam.governmentalert.Helper.ConstantClass.QUALIFICATION;
import static com.satyam.governmentalert.Helper.ConstantClass.SUBMISSION_DATE;
import static com.satyam.governmentalert.Helper.ConstantClass.SUMMARY;
import static com.satyam.governmentalert.Helper.ConstantClass.TAGS;
import static com.satyam.governmentalert.Helper.ConstantClass.TITLE;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.satyam.governmentalert.Fragment.JobCategoryFragment;
import com.satyam.governmentalert.Fragment.NewJobFragment;
import com.satyam.governmentalert.Fragment.SearchJobFragment;
import com.satyam.governmentalert.Fragment.YourJobFragment;
import com.satyam.governmentalert.Helper.MyDBHelper;
import com.satyam.governmentalert.Helper.ViewPageAdapter;
import com.satyam.governmentalert.R;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public MyDBHelper myDBHelper;
    private ProgressBar progress_circular;
    private ImageView img_home_menu;
    public ArrayList<HashMap<String,String>> dataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        progress_circular = findViewById(R.id.progress_circular);
        img_home_menu = findViewById(R.id.img_home_menu);
        progress_circular.setVisibility(View.GONE);
        myDBHelper = new MyDBHelper(MainActivity.this);
        img_home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,JobDetailsActivity.class);
                startActivity(intent);
            }
        });
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }
   /* private void getExcelId() {
        progress_circular.setVisibility(View.VISIBLE);
        String server_url = "https://script.google.com/macros/s/AKfycbzaxHubu0fwzDWQ_mizjqyzyniOVsFVXKo3XlYEfyEOEu_tOvE/exec";
        Log.e("server_url", server_url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();
                    JSONArray data = response.getJSONArray("data");
                    Toast.makeText(MainActivity.this, "" + data.length(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        ContentValues hashMap = new ContentValues();
                        hashMap.put(ID, object.getString("ID"));
                        hashMap.put(SUBMISSION_DATE, object.getString("SUBMISSION_DATE"));
                        hashMap.put(PUBLISH_DATE, object.getString("PUBLISH_DATE"));
                        hashMap.put(LANGUAGE_ID, object.getString("LANGUAGE_ID"));
                        hashMap.put(TITLE, object.getString("TITLE"));
                        hashMap.put(SUMMARY, object.getString("SUMMARY"));
                        hashMap.put(CAT_NAMES, object.getString("CAT_NAMES"));
                        hashMap.put(FUNCTIONAL_AREA, object.getString("FUNCTIONAL_AREA"));
                        hashMap.put(IMPORTANT, object.getString("IMPORTANT"));
                        hashMap.put(INDEXED_TYPE, object.getString("INDEXED_TYPE"));
                        hashMap.put(LOCATION, object.getString("LOCATION"));
                        hashMap.put(NOTIFICATION, object.getString("NOTIFICATION"));
                        hashMap.put(ORGANIZATION, object.getString("ORGANIZATION"));
                        hashMap.put(PROFILE_TYPE, object.getString("PROFILE_TYPE"));
                        hashMap.put(QUALIFICATION, object.getString("QUALIFICATION"));
                        hashMap.put(TAGS, object.getString("TAGS"));
                        hashMap.put(DETAILS, object.getString("DETAILS"));
                        if (i==9){
                            setupViewPager(viewPager);
                            tabLayout.setupWithViewPager(viewPager);
                            progress_circular.setVisibility(View.GONE);
                        }
                        long insetdata = sqLiteDatabase.insert(MyDBHelper.TABLE_NAME, null, hashMap);
                        if (insetdata != -1) {
                            Log.e("datainser",object.getString("ID"));
                        }
                    }

                } catch (JSONException e) {
                    progress_circular.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    Log.e("JSONException", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress_circular.setVisibility(View.GONE);
                Log.e("JSONException", error.toString());
                if (error.toString().contains("ConnectException")) {
                    Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
    }*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), this);
        adapter.addFrag(new NewJobFragment(MainActivity.this), "New Jobs");
        adapter.addFrag(new YourJobFragment(), "Your Jobs");
        adapter.addFrag(new JobCategoryFragment(MainActivity.this), "Categories");
        adapter.addFrag(new SearchJobFragment(), "Notification");
        viewPager.setAdapter(adapter);
    }


}