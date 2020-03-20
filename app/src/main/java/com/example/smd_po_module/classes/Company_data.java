package com.example.smd_po_module.classes;

import java.util.ArrayList;

public class Company_data {
    public static ArrayList<information_company> getdata(){
        ArrayList<information_company> data = new ArrayList<information_company>();

        String[] firstObject={"Company Name: Netsol Limited","Email: netsol_email@netsol.com","cmmi: 5 levels"};
        String[] secondObject={"Company Name: Netsol Limited","Email: netsol_email@netsol.com","cmmi: 5 levels"};
        String[] thirdObject={"Company Name: Netsol Limited","Email: netsol_email@netsol.com","cmmi: 5 levels"};
        String[] fourthObject={"Company Name: Netsol Limited","Email: netsol_email@netsol.com","cmmi: 5 levels"};

        information_company info1=new information_company();
        information_company info2=new information_company();
        information_company info3=new information_company();
        information_company info4=new information_company();
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
