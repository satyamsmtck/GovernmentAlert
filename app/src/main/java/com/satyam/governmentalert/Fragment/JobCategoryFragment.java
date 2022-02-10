package com.satyam.governmentalert.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.governmentalert.Adapter.MostPopularCategoryAdapter;
import com.satyam.governmentalert.Helper.MethodClass;
import com.satyam.governmentalert.R;


public class JobCategoryFragment extends Fragment {
    private Activity activity;


    public JobCategoryFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_job_categoty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton most_pop_rbtn=view.findViewById(R.id.most_pop_rbtn);
        RadioButton all_rbtn=view.findViewById(R.id.all_rbtn);
        most_pop_rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    most_pop_rbtn.setTextColor(activity.getResources().getColor(R.color.white));
                    all_rbtn.setTextColor(activity.getResources().getColor(R.color.black));
                }else {
                    most_pop_rbtn.setTextColor(activity.getResources().getColor(R.color.black));
                    all_rbtn.setTextColor(activity.getResources().getColor(R.color.white));
                }
            }
        });
        most_pop_rbtn.setChecked(true);

        RecyclerView most_pop_category_recy = view.findViewById(R.id.most_pop_category_recy);
        getData(most_pop_category_recy);
    }

    private void getData(RecyclerView recyclerView) {
        MostPopularCategoryAdapter newJobAdapter = new MostPopularCategoryAdapter(activity, MethodClass.getCategory());
        recyclerView.setAdapter(newJobAdapter);
    }

}