package com.example.studentasu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentasu.DB.Course;
import com.example.studentasu.DB.DB;

import java.util.List;

public class DiplayAdabter extends RecyclerView.Adapter<DiplayAdabter.ViewHolder> {


    private List<Course> data;
    private LayoutInflater mInflater;
    DB db;

    public DiplayAdabter(Context context, List<Course> data)
    {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        db = new DB(context);
    }

    @NonNull
    @Override
    public DiplayAdabter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.card_display, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiplayAdabter.ViewHolder holder, int position)
    {
        Course item = data.get(position);
        holder.myTextView1.setText(item.getCode());
        holder.myTextView2.setText(item.getCredithour() + " Hour");
        holder.myTextView3.setText(item.getName());
        holder.myTextView4.setText(item.getDrname());
        holder.myTextView5.setText("Level " + item.getLevel());
        holder.btn.setOnClickListener(v -> {
            db.deleteCourseforStudent(item.getName());
            holder.linearLayout.setVisibility(View.GONE);
            holder.linearLayout.getLayoutParams().height = 0;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView1;
        TextView myTextView2;
        TextView myTextView3;
        TextView myTextView4;
        TextView myTextView5;
        Button btn;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearhidden);
             myTextView1 = itemView.findViewById(R.id.text_codecourse);
             myTextView2 = itemView.findViewById(R.id.text_Credit_hour);
             myTextView3 = itemView.findViewById(R.id.text_Coursename);
             myTextView4 = itemView.findViewById(R.id.text_CourseDrname);
             myTextView5 = itemView.findViewById(R.id.text_level);
             btn = itemView.findViewById(R.id.remove_box);
        }
    }
}