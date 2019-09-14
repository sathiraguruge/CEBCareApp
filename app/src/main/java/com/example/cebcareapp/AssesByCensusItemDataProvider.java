package com.example.cebcareapp;

import android.content.res.AssetManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AssesByCensusItemDataProvider {

    public static List<AssessByCensusItem> itemsList = new ArrayList<>() ;
    public static Map<String, AssessByCensusItem> itemsMap = new HashMap<>() ;

    static{
        addItem(0, (float)0.0, false, "app-1", "Incandescent", (float)0.05);
        addItem(0, (float)0.0, false, "app-2", "CFL/LED", (float)0.01);
        addItem(0, (float)0.0, false, "app-3", "Ceiling Fan", (float)0.6);
        addItem(0, (float)0.0, false, "app-4", "Pedestal Fan", (float)0.3);
        addItem(0, (float)0.0, false, "app-5", "Computer (Desktop)", (float)0.8);
        addItem(0, (float)0.0, false, "app-6", "Computer (Laptop)", (float)0.5);
        addItem(0, (float)0.0, false, "app-7", "TV (LCD/LED)", (float)0.33);
        addItem(0, (float)0.0, false, "app-8", "TV (Cathode Ray Tube)", (float)1.2);
        addItem(0, (float)0.0, false, "app-9", "Deep Freezer", (float)0.98);
        addItem(0, (float)0.0, false, "app-10", "Refrigerator", (float)0.75);
        addItem(0, (float)0.0, false, "app-11", "Washing Machine", (float)1.5);
        addItem(0, (float)0.0, false, "app-12", "Iron", (float)1.3);
        addItem(0, (float)0.0, false, "app-13", "Vacuum Cleaner", (float)0.5);
        addItem(0, (float)0.0, false, "app-14", "Audio Setup", (float)0.72);
        addItem(0, (float)0.0, false, "app-15", "Rice Cooker", (float)0.8);
        addItem(0, (float)0.0, false, "app-16", "Electric Cooker", (float)1.83);
        addItem(0, (float)0.0, false, "app-17", "Electric Kettle", (float)1.11);
        addItem(0, (float)0.0, false, "app-18", "Water Heater", (float)2.1);
        addItem(0, (float)0.0, false, "app-19", "Electric Oven", (float)1.2);
        addItem(0, (float)0.0, false, "app-20", "Blender", (float)0.23);
        addItem(0, (float)0.0, false, "app-21", "Air Condition", (float)1.15);
        addItem(0, (float)0.0, false, "app-22", "Floor Polisher", (float)0.89);
        addItem(0, (float)0.0, false, "app-23", "Geyser (Bathroom)", (float)1.4);
        addItem(0, (float)0.0, false, "app-24", "Space Heater", (float)2.68);
    }

    private static void addItem(int quantity, float hoursADay, boolean selected, String id, String appliance, float unitsPerHour) {
        AssessByCensusItem item =  new AssessByCensusItem(quantity, hoursADay, selected, id, appliance, unitsPerHour);
        itemsList.add(item);
        itemsMap.put(id, item);
    }


}
