package com.example.oespartner.Adapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> menu1 = new ArrayList<String>();
        List<String> menu4 = new ArrayList<String>();


        List<String> list2 = new ArrayList<String>();
        list2.add("Visitor Gate Pass");
        list2.add("Work  Gate Pass");
        //list2.add("Submenu 3");
        //list2.add("Submenu 4");


        List<String> list3 = new ArrayList<String>();
        list3.add("Authorized Signatory");
        list3.add("Partner Person");
       // list3.add("Submenu 3");
       // list3.add("Submenu 4");


        List<String> list4 = new ArrayList<String>();
        list4.add("Vehicle No.");
        list4.add("Transport Data");

        expandableListDetail.put("Dashboard", menu1);
        expandableListDetail.put("Gate Pass System", list2);
        expandableListDetail.put("Partner", list3);
        expandableListDetail.put("Material Gatepass System", menu4);
        expandableListDetail.put("Transport", list4);


        return expandableListDetail;
    }
}
