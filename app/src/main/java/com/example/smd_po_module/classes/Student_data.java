package com.example.smd_po_module.classes;

import com.example.smd_po_module.R;

import java.util.ArrayList;

public class Student_data {
    public static ArrayList<information_stud> getdata(){
        ArrayList<information_stud> data = new ArrayList<information_stud>();

        String[] firstObject={"Mashal Bashir","Batch: 16","CGPA: 3.5"};
        String[] secondObject={"Famia Waheed","Batch: 15","CGPA: 4"};
        String[] thirdObject={"Suqaina","Batch: 17","CGPA: 2.8"};
        String[] fourthObject={"Qasim ","Batch: 18","CGPA: 2.9"};

        information_stud info1=new information_stud();
        information_stud info2=new information_stud();
        information_stud info3=new information_stud();
        information_stud info4=new information_stud();
        for(int i=0;i<3;i++){

            info1.title[i]=firstObject[i];
            info2.title[i]=secondObject[i];
            info3.title[i]=thirdObject[i];
            info4.title[i]=fourthObject[i];
        }


        data.add(info1);
        data.add(info2);
        data.add(info3);
        data.add(info4);

        return data;
    }
}
