package com.example.cebcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AssesByCensus extends AppCompatActivity {

    private List<AssessByCensusItem> censusItems = AssesByCensusItemDataProvider.itemsList ;
    private Context c = this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_by_census);

        Toolbar toolbar = findViewById(R.id.accessByCensusToolbar);
        toolbar.setTitle("Access By Census");
        setSupportActionBar(toolbar);

        AssesByCensusItemListAdapter adapter = new AssesByCensusItemListAdapter(this, R.layout.asses_by_census_list_item, censusItems);
        ListView lv = findViewById(R.id.censusList);

        TextView amount = findViewById(R.id.abc_amount);
        TextView units = findViewById(R.id.abc_units);
        amount.setText("0.0");
        units.setText("0.0");
        adapter.initiateTextViews(amount, units);

        lv.setAdapter(adapter);
    }

    // Parsing XML data
    private void parseXML(){

        XmlPullParserFactory xmlParserFactory;

        try {
            xmlParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlParser = xmlParserFactory.newPullParser();
            InputStream inputStream = getAssets().open("appliance_list.xml");
            xmlParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlParser.setInput(inputStream, null);

            processXMLParsing(xmlParser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processXMLParsing(XmlPullParser xmlParser) {
        ArrayList<AssessByCensusItem> items = new ArrayList<>();
        try {
            int eType = xmlParser.getEventType();
            AssessByCensusItem curItem = null;
            String curName = "" ;
            String tempString ;


            while(eType != XmlPullParser.END_DOCUMENT){
                switch (eType){
                    case XmlPullParser.START_TAG:
                        curName = xmlParser.getName();

                        if("item".equals(curName)){
                            curItem = new AssessByCensusItem();
                            curItem.setQuantity(0);
                            curItem.setHoursADay(0.0f);
                            curItem.setSelected(false);
                        }else if("item-id".equals(curName)){
                            curItem.setId(xmlParser.nextText());
//                            Log.d("CREATION", xmlParser.nextText());
                        }else if("item-name".equals(curName)){
                            curItem.setAppliance(xmlParser.nextText());
//                            Log.d("CREATION", xmlParser.nextText());
                        }else if("item-units-per-hour".equals(curName)){
                            curItem.setUnitsPerHour(Float.parseFloat(xmlParser.nextText()));
//                            Log.d("CREATION", xmlParser.nextText());
                        }
                        break;
                }
//                items.add(curItem);
                eType = xmlParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printItems(items);
    }

    private void printItems(ArrayList<AssessByCensusItem> items) {
        StringBuilder b = new StringBuilder();

        for (AssessByCensusItem i: items){
            b.append(i.getQuantity()).append("\n")
                    .append(i.getHoursADay()).append("\n")
                    .append(i.isSelected()).append("\n")
                    .append(i.getId()).append("\n")
                    .append(i.getAppliance()).append("\n")
                    .append(i.getQuantity()).append("\n")
                    .append(i.getUnitsPerHour()).append("\n")
                    .append("-----------------------------------------------").append("\n");
        }

        Log.d("CREATION", b.toString());
    }

}
