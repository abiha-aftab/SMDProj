package com.example.smd_po_module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            int viewId = v.getId();
            if(Global.intent == 'S') {
                int pos = getAdapterPosition();
                if (pos == 0) {
                    builder.setMessage("Do You want to notify this offer?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, select_job_notify.class));

                                    //Toast.makeText(getApplicationContext(),"you choose yes ",
                                           // Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                   // Toast.makeText(getApplicationContext(),"you choose no ",
                                        //    Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Confirmation");
                    alert.show();

                } else if (pos == 1) {
                    builder.setMessage("Do You want to notify this offer?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, select_job_notify.class));

                                    //Toast.makeText(getApplicationContext(),"you choose yes ",
                                    // Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    // Toast.makeText(getApplicationContext(),"you choose no ",
                                    //    Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Confirmation");
                    alert.show();
                } else if (pos == 2) {
                    builder.setMessage("Do You want to notify this offer?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, select_job_notify.class));

                                    //Toast.makeText(getApplicationContext(),"you choose yes ",
                                    // Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    // Toast.makeText(getApplicationContext(),"you choose no ",
                                    //    Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Confirmation");
                    alert.show();
                } else if (pos == 3) {
                    builder.setMessage("Do You want to notify this offer?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, select_job_notify.class));

                                    //Toast.makeText(getApplicationContext(),"you choose yes ",
                                    // Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    // Toast.makeText(getApplicationContext(),"you choose no ",
                                    //    Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Confirmation");
                    alert.show();
                }
            }
            if(Global.intent == 'T'){
                Global.intent1='T';
                int pos = getAdapterPosition();
                if (pos == 0) {

                    context.startActivity(new Intent(context, list_Jobs.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, list_Jobs.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, list_Jobs.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, list_Jobs.class));
                }
            }
            if(Global.intent == 'i'){


            }
            if(Global.intent == 'N'&& Global.intent1=='c'){

                int pos = getAdapterPosition();
                if (pos == 0) {

                    context.startActivity(new Intent(context, chat_base.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, chat_base.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, chat_base.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, chat_base.class));
                }
            }


        }
    }
}
