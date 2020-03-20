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

import com.example.smd_po_module.classes.information_job;

import java.util.ArrayList;

public class adapter_job extends RecyclerView.Adapter<adapter_job.MyHolder>{
    Context context;
    ArrayList<information_job> getdata;
    public adapter_job(Context context, ArrayList<information_job> getdata) {
        this.context=context;
        this.getdata=getdata;
    }

    @NonNull
    @Override
    public adapter_job.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.job_object,parent,false);

        return new adapter_job.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_job.MyHolder holder, int position) {
        holder.pos.setText((getdata.get(position).title[0]));
        holder.cname.setText(getdata.get(position).title[1]);
        holder.salary.setText(getdata.get(position).title[2]);



    }

    @Override
    public int getItemCount() {
        return getdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView pos;
        TextView cname;
        TextView salary;
        AlertDialog.Builder builder;
        public MyHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this::onClick);
            context = v.getContext();
            cv = itemView.findViewById(R.id.card);
            pos= itemView.findViewById(R.id.pos);
           cname= itemView.findViewById(R.id.cname);
            salary= itemView.findViewById(R.id.salary);
            builder = new AlertDialog.Builder(context);
        }
        public void onClick(View v) {
            char flow = Global.intent;
            char sub = Global.intent1;
            if (sub == 'T') {
                if (flow == 'J') {
                    confirmThenOpen("Do you want to add this job for the selected student?", Add_selected1.class);
                }
            } else if (flow == 'P') {
                open(Student_view_job.class);
            } else if (flow == 'J' && sub == 'y') {
                Global.intent = 'i';
                open(list_student.class);
            } else if (sub != 'y') {
                if (flow == 'J' || flow == 'N') {
                    open(view_job.class);
                } else if (flow == 'e') {
                    Global.intent = 'i';
                    open(Student_job_view1.class);
                }
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
