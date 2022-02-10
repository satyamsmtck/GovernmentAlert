package com.satyam.governmentalert.Adapter;

import static com.satyam.governmentalert.Helper.ConstantClass.ID;
import static com.satyam.governmentalert.Helper.ConstantClass.LOCATION;
import static com.satyam.governmentalert.Helper.ConstantClass.ORGANIZATION;
import static com.satyam.governmentalert.Helper.ConstantClass.PUBLISH_DATE;
import static com.satyam.governmentalert.Helper.ConstantClass.QUALIFICATION;
import static com.satyam.governmentalert.Helper.ConstantClass.SUBMISSION_DATE;
import static com.satyam.governmentalert.Helper.ConstantClass.TITLE;
import static com.satyam.governmentalert.Helper.MethodClass.getFormatJson;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.governmentalert.Activity.JobDetailsActivity;
import com.satyam.governmentalert.Helper.MethodClass;
import com.satyam.governmentalert.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashMap;

public class NewJobAdapter extends RecyclerView.Adapter<NewJobAdapter.ViewHolder> {
    Activity activity;
    public ArrayList<HashMap<String, String>> map_list;

    public NewJobAdapter(Activity activity, ArrayList<HashMap<String, String>> map_list) {
        this.activity = activity;
        this.map_list = map_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.new_job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final HashMap<String, String> map = map_list.get(position);
        holder.title.setText(map.get(TITLE));
        holder.postDate_tv.setText(MethodClass.getFormatDate(map.get(PUBLISH_DATE)));
        holder.lastDate_tv.setText(MethodClass.getFormatDate(map.get(SUBMISSION_DATE)));
        holder.organization_tv.setText(getFormatJson(map.get(ORGANIZATION)));
        holder.education_tv.setText(getFormatJson(map.get(QUALIFICATION)));
        holder.location_tv.setText(getFormatJson(map.get(LOCATION)));
        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, JobDetailsActivity.class);
                intent.putExtra("id",map.get(ID));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return map_list == null ? 0 : map_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, postDate_tv, lastDate_tv, organization_tv, education_tv, location_tv;
        private ImageView download_img, share_img;
        private MaterialCardView list_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            postDate_tv = itemView.findViewById(R.id.postDate_tv);
            lastDate_tv = itemView.findViewById(R.id.lastDate_tv);
            organization_tv = itemView.findViewById(R.id.organization_tv);
            education_tv = itemView.findViewById(R.id.education_tv);
            location_tv = itemView.findViewById(R.id.location_tv);
            download_img = itemView.findViewById(R.id.download_img);
            share_img = itemView.findViewById(R.id.share_img);
            list_item = itemView.findViewById(R.id.list_item);
        }
    }
}