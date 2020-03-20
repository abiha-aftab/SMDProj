package com.example.smd_po_module.classes;

import java.util.ArrayList;

public class Job_data {
    public static ArrayList<information_job> getdata(){
        ArrayList<information_job> data = new ArrayList<information_job>();

        String[] firstObject={"Position: Marketing Manager","Company: Netsol Limited","Salary offered: Rs60,000"};
        String[] secondObject={"Position: Marketing Manager","Company: Netsol Limited","Salary offered: Rs60,000"};
        String[] thirdObject={"Position: Marketing Manager","Company: Netsol Limited","Salary offered: Rs60,000"};
        String[] fourthObject={"Position: Marketing Manager","Company: Netsol Limited","Salary offered: Rs60,000"};

        information_job info1=new information_job();
        information_job info2=new information_job();
        information_job info3=new information_job();
        information_job info4=new information_job();
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
