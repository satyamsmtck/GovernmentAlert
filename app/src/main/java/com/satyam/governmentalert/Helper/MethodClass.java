package com.satyam.governmentalert.Helper;

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

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MethodClass {

    public static String getFormatDate(String date_str){
        String final_date="";
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date=simpleDateFormat.parse(date_str);
            final_date= (String) android.text.format.DateFormat.format("dd, MMM yyyy",date);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("DateForma Exception",e.toString());
        }
        return final_date;
    }

    public static String getFormatJson(String str){
        try {
            ArrayList<String> listdata = new ArrayList<String>();
            JSONArray jArray = new JSONArray(str);
            if (jArray != null) {
                for (int i=0;i<jArray.length();i++){
                    listdata.add(jArray.getString(i));
                }
            }
            return TextUtils.join(", ",listdata);
        }catch (Exception e){
            Log.e("ExceptiongetFormatJson",e.toString());
            return "";
        }
    }

    public static ArrayList<HashMap<String,String>> getData(Context context,String sql){

        SQLiteDatabase sqLiteDatabase=new MyDBHelper(context).getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        while(cursor.moveToNext()) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put(ID,cursor.getString(cursor.getColumnIndex("ID")));
            hashMap.put(SUBMISSION_DATE,cursor.getString(cursor.getColumnIndex("SUBMISSION_DATE")));
            hashMap.put(PUBLISH_DATE,cursor.getString(cursor.getColumnIndex("PUBLISH_DATE")));
            hashMap.put(LANGUAGE_ID,cursor.getString(cursor.getColumnIndex("LANGUAGE_ID")));
            hashMap.put(TITLE,cursor.getString(cursor.getColumnIndex("TITLE")));
            hashMap.put(SUMMARY,cursor.getString(cursor.getColumnIndex("SUMMARY")));
            hashMap.put(CAT_NAMES,cursor.getString(cursor.getColumnIndex("CAT_NAMES")));
            hashMap.put(FUNCTIONAL_AREA,cursor.getString(cursor.getColumnIndex("FUNCTIONAL_AREA")));
            hashMap.put(IMPORTANT,cursor.getString(cursor.getColumnIndex("IMPORTANT")));
            hashMap.put(INDEXED_TYPE,cursor.getString(cursor.getColumnIndex("INDEXED_TYPE")));
            hashMap.put(LOCATION,cursor.getString(cursor.getColumnIndex("LOCATION")));
            hashMap.put(NOTIFICATION,cursor.getString(cursor.getColumnIndex("NOTIFICATION")));
            hashMap.put(ORGANIZATION,cursor.getString(cursor.getColumnIndex("ORGANIZATION")));
            hashMap.put(PROFILE_TYPE,cursor.getString(cursor.getColumnIndex("PROFILE_TYPE")));
            hashMap.put(QUALIFICATION,cursor.getString(cursor.getColumnIndex("QUALIFICATION")));
            hashMap.put(TAGS,cursor.getString(cursor.getColumnIndex("TAGS")));
            hashMap.put(DETAILS,cursor.getString(cursor.getColumnIndex("DETAILS")));
            arrayList.add(hashMap);
        }
        cursor.close();
        return arrayList;
    }

    public static ArrayList<HashMap<String,String >> getCategory(){
        String [] categotyStr={"Airforce","Army","Bank Jobs","Clerical","Defence","faculty Jobs","Judicial","Lecturer/Teacher","Navy","Nursing","Other Jobs","Paramilitary",
        "Police","PSUs","Railway","SSC","State PSC","UPSC"};

        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        for (int i = 0; i < categotyStr.length; i++) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put(TITLE,categotyStr[i]);
            arrayList.add(hashMap);
        }
        return arrayList;
    }

}












/*
    private void getExcelId(RecyclerView recyclerView) {
        String server_url = "https://script.google.com/macros/s/AKfycbzaxHubu0fwzDWQ_mizjqyzyniOVsFVXKo3XlYEfyEOEu_tOvE/exec";
        Log.e("server_url", server_url);
        HashMap<String, String> params = new HashMap<String, String>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("respUser", response.toString());
                try {
                    NewJobAdapter adapter = null;
                    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
                    JSONArray data=response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object=data.getJSONObject(i);
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put(ID,object.getString("ID"));
                        hashMap.put(SUBMISSION_DATE,object.getString("SUBMISSION_DATE"));
                        hashMap.put(PUBLISH_DATE,object.getString("PUBLISH_DATE"));
                        hashMap.put(LANGUAGE_ID,object.getString("LANGUAGE_ID"));
                        hashMap.put(TITLE,object.getString("TITLE"));
                        hashMap.put(SUMMARY,object.getString("SUMMARY"));
                        hashMap.put(CAT_NAMES,object.getString("CAT_NAMES"));
                        hashMap.put(FUNCTIONAL_AREA,object.getString("FUNCTIONAL_AREA"));
                        hashMap.put(IMPORTANT,object.getString("IMPORTANT"));
                        hashMap.put(INDEXED_TYPE,object.getString("INDEXED_TYPE"));
                        hashMap.put(LOCATION,object.getString("LOCATION"));
                        hashMap.put(NOTIFICATION,object.getString("NOTIFICATION"));
                        hashMap.put(ORGANIZATION,object.getString("ORGANIZATION"));
                        hashMap.put(PROFILE_TYPE,object.getString("PROFILE_TYPE"));
                        hashMap.put(QUALIFICATION,object.getString("QUALIFICATION"));
                        hashMap.put(TAGS,object.getString("TAGS"));
                        hashMap.put(DETAILS,object.getString("DETAILS"));
                        arrayList.add(hashMap);
                        */
/*if (i==10){
                            adapter = new NewJobAdapter(activity, arrayList);
                            recyclerView.setAdapter(adapter);
                        }
                        if (i>10){
                            if (i%10==0){
                                adapter.notifyItemInserted(i);
                            }
                        }*//*

                    }
                    adapter = new NewJobAdapter(activity, arrayList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    Log.e("JSONException", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.toString().contains("ConnectException")) {
                    Toast.makeText(activity, "Connection Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Network error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        MySingleton.getInstance(activity).addToRequestQueue(jsonObjectRequest);
    }
*/