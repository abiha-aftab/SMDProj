package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class chat_base extends AppCompatActivity {
    ArrayList<String> chats;
    EditText editText;
    private View context;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_base);
        ListView chatList=(ListView)findViewById(R.id.messages_view);

        chats = new ArrayList<String>();
        getchats();

        // Create The Adapter with passing ArrayList as 3rd parameter
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, chats);

        // Set The Adapter
        chatList.setAdapter(arrayAdapter);
        editText=findViewById(R.id.editText);
        ImageButton b1=findViewById(R.id.send);
        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                sendMessage(context);

            }
        });


    }
    void getchats()
    {
        chats.add("PO-1:  Welcome To Our Application");
        chats.add("ME:  Thanks !!");
        chats.add("ME:  I need help with my Account");
        chats.add("PO-1:  What issues are You facing?");
        chats.add("Me:  I am not getting notifications of new jobs.");
        chats.add("PO-1:  You need to Enter Your batch again");
    }
    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
        //    scaledrone.publish("observable-room", message);
            runOnUiThread(new Runnable() {
                public void run() {
                    arrayAdapter.notifyDataSetChanged();
                }
            });
            chats.add("ME:  "+editText.getText().toString());
            editText.getText().clear();
            chats.add("PO-1: I need to implement it further ");

        }
    }


}
