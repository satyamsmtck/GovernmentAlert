package com.satyam.governmentalert.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.satyam.governmentalert.Helper.ConstantClass;
import com.satyam.governmentalert.Helper.MethodClass;
import com.satyam.governmentalert.Helper.MyDBHelper;
import com.satyam.governmentalert.R;

public class JobDetailsActivity extends AppCompatActivity {
    private TextView title_tv,postDate_tv,lastDate_tv,eligibility_tv,location_tv,funtional_tv,description_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        title_tv=findViewById(R.id.title_tv);
        postDate_tv=findViewById(R.id.postDate_tv);
        lastDate_tv=findViewById(R.id.lastDate_tv);
        eligibility_tv=findViewById(R.id.eligibility_tv);
        location_tv=findViewById(R.id.location_tv);
        funtional_tv=findViewById(R.id.funtional_tv);
        description_tv=findViewById(R.id.description_tv);
        //getData();
    }

    private void getData(){
        String id= getIntent().getStringExtra("id");
        SQLiteDatabase sqLiteDatabase=new MyDBHelper(this).getReadableDatabase();
        String fetchQuery = "SELECT * FROM job_table ORDER BY(ID) ASC  LIMIT 1 OFFSET 1";
        Cursor cursor=sqLiteDatabase.rawQuery(fetchQuery,null);
        Cursor cursor2=sqLiteDatabase.query("job_table",null, ConstantClass.ID, new String[]{id},null,null,null);
        while (cursor.moveToNext()) {
            title_tv.setText(cursor.getString(cursor.getColumnIndex("TITLE")));
            postDate_tv.setText(MethodClass.getFormatDate(cursor.getString(cursor.getColumnIndex("PUBLISH_DATE"))));
            lastDate_tv.setText(MethodClass.getFormatDate(cursor.getString(cursor.getColumnIndex("SUBMISSION_DATE"))));
            eligibility_tv.setText(MethodClass.getFormatJson(cursor.getString(cursor.getColumnIndex("QUALIFICATION"))));
            location_tv.setText(MethodClass.getFormatJson(cursor.getString(cursor.getColumnIndex("LOCATION"))));
            funtional_tv.setText(MethodClass.getFormatJson(cursor.getString(cursor.getColumnIndex("FUNCTIONAL_AREA"))));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                description_tv.setText(Html.fromHtml(cursor.getString(cursor.getColumnIndex("DETAILS")), Html.FROM_HTML_MODE_COMPACT));
            } else {
                description_tv.setText(Html.fromHtml(cursor.getString(cursor.getColumnIndex("DETAILS"))));
            }
        }
        cursor.close();
    }


}
