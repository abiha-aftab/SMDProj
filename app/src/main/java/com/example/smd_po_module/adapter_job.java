package com.example.smd_po_module;

import android.content.Context;
import android.content.DialogInterface;
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
            int viewId = v.getId();
            System.out.println("global"+Global.intent);
            if (Global.intent == 'J'&& Global.intent1!='T' && Global.intent1!='y') {
                int pos = getAdapterPosition();
                if (pos == 0) {
                    context.startActivity(new Intent(context, view_job.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, view_job.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, view_job.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, view_job.class));
                }

            }
            else if (Global.intent == 'N'&& Global.intent1!='T'&& Global.intent1!='y') {
                int pos = getAdapterPosition();
                if (pos == 0) {
                    context.startActivity(new Intent(context, view_job.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, view_job.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, view_job.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, view_job.class));
                }

            }
            else if (Global.intent == 'P'&& Global.intent1!='T') {
                int pos = getAdapterPosition();
                if (pos == 0) {
                    context.startActivity(new Intent(context, Student_view_job.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, Student_view_job.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, Student_view_job.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, Student_view_job.class));
                }

            }
            else if (Global.intent == 'J'&& Global.intent1!='T' && Global.intent1=='y') {
                int pos = getAdapterPosition();
                Global.intent = 'i';
            if (pos == 0) {
                    context.startActivity(new Intent(context, list_student.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, list_student.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, list_student.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, list_student.class));
                }

            }
            else if (Global.intent == 'e'&& Global.intent1!='T' && Global.intent1!='y') {
                int pos = getAdapterPosition();
                Global.intent = 'i';
                if (pos == 0) {
                    context.startActivity(new Intent(context, Student_job_view1.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, Student_job_view1.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, Student_job_view1.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, Student_job_view1.class));
                }

            }
            else if(Global.intent == 'J' && Global.intent1=='T') {
                int pos = getAdapterPosition();
                if (pos == 0) {
                    builder.setMessage("Do You want to add this job for selected Student?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, Add_selected1.class));

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
                    builder.setMessage("Do You want to add this job for selected Student?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, Add_selected1.class));

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
                    builder.setMessage("Do You want to add this job for selected Student?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, Add_selected1.class));

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
                    builder.setMessage("Do You want to add this job for selected Student?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    context.startActivity(new Intent(context, Add_selected1.class));

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
        }
    }

}
