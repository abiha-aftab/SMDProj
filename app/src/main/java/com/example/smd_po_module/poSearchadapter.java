package com.example.smd_po_module;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.smd_po_module.data.model.PO_DATA;

import java.util.ArrayList;

public class poSearchadapter extends RecyclerView.Adapter<poSearchadapter.MyViewHolder> {

   Context context;
   ArrayList<PlacementOfficer> data;

    public poSearchadapter(Context context, ArrayList<PlacementOfficer> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position){
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.recyclepo,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position){
        PlacementOfficer p= data.get(position);
        myViewHolder.textview1.setText(p.getUsername());
        myViewHolder.textview2.setText(p.getPid());
    }
    @Override
    public int getItemCount(){
       // System.out.print(getdata.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textview1;
        TextView textview2;

        public MyViewHolder(View itemview)
        {
            super(itemview);
            itemview.setOnClickListener(this);
            context = itemview.getContext();
            textview1= (TextView) itemview.findViewById(R.id.tv2);
            textview2= (TextView) itemview.findViewById(R.id.id);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos == 0) {
                Intent i= new Intent(context, view_po.class);
                i.putExtra("id",textview2.getText().toString());
                context.startActivity(i);
            } else if (pos == 1) {
                Intent i= new Intent(context, view_po.class);
                i.putExtra("id",textview2.getText().toString());
                context.startActivity(i);
            }
            else {
                Intent i = new Intent(context, view_po.class);
                i.putExtra("id", textview2.getText().toString());
                context.startActivity(i);
            }
        }
    }
}
