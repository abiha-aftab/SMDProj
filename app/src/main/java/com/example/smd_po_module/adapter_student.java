package com.example.smd_po_module;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.information_stud;

import java.util.ArrayList;

public class adapter_student extends RecyclerView.Adapter<adapter_student.MyHolder> {

    Context context;
    ArrayList<information_stud> getdata;
    public adapter_student(Context context, ArrayList<information_stud> getdata) {
        this.context=context;
        this.getdata=getdata;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.student_object,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.sname.setText((getdata.get(position).title[0]));
        holder.batch.setText(getdata.get(position).title[1]);
        holder.cgpa.setText(getdata.get(position).title[2]);



    }

    @Override
    public int getItemCount() {
        return getdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView sname;
        TextView batch;
        TextView cgpa;
        CardView cv;
        AlertDialog.Builder builder;
        public MyHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this::onClick);
            context = v.getContext();
            cv = itemView.findViewById(R.id.card);
            sname= itemView.findViewById(R.id.sname);
            batch= itemView.findViewById(R.id.batch);
            cgpa= itemView.findViewById(R.id.cgpa);
            builder = new AlertDialog.Builder(context);
        }

        public void onClick(View v) {
            if (Global.intent == 'S') {
                confirmThenOpen("Do you want to notify this offer?", select_job_notify.class);
            } else if (Global.intent == 'T') {
                Global.intent1 = 'T';
                open(list_Jobs.class);
            } else if (Global.intent == 'N' && Global.intent1 == 'c') {
                open(chat_base.class);
            }
        }

        private void open(Class<?> screen) {
            context.startActivity(new Intent(context, screen));
        }

        private void confirmThenOpen(String question, Class<?> screen) {
            AlertDialog alert = builder.setMessage(question)
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> open(screen))
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                    .create();
            alert.setTitle("Confirmation");
            alert.show();
        }
    }
}
