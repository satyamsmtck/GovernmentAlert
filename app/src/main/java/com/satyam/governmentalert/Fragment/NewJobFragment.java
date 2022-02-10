package com.satyam.governmentalert.Fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.governmentalert.Adapter.NewJobAdapter;
import com.satyam.governmentalert.Helper.MethodClass;
import com.satyam.governmentalert.R;

import java.util.ArrayList;
import java.util.HashMap;

public class NewJobFragment extends Fragment {

    private Activity activity;
    public NewJobFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_job, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recy = view.findViewById(R.id.recy);
        getData(recy);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getData(RecyclerView recyclerView){
        ArrayList<HashMap<String,String>> arrayList= MethodClass.getData(activity,"SELECT * FROM job_table LIMIT 10 OFFSET 1");
        NewJobAdapter newJobAdapter=new NewJobAdapter(activity,arrayList);
        recyclerView.setAdapter(newJobAdapter);
        final LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition()== arrayList.size()){
                    arrayList.addAll(MethodClass.getData(activity,"SELECT * FROM job_table LIMIT 10 OFFSET "+arrayList.size()));
                    newJobAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}
























   /* private void getData(RecyclerView recyclerView){

        SQLiteDatabase sqLiteDatabase=new MyDBHelper(activity).getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM job_table LIMIT 10 OFFSET 2",null);
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
        NewJobAdapter newJobAdapter=new NewJobAdapter(activity,arrayList);
        recyclerView.setAdapter(newJobAdapter);
    }
*/