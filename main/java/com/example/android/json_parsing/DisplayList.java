package com.example.android.json_parsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayList extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DetailAdapter detailAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list);
        detailAdapter = new DetailAdapter(this,R.layout.list_item);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(detailAdapter);

        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count=0;
            String name,email,mobile;
            while (count<jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                email = jo.getString("email");
                mobile = jo.getString("mobile");

                Details details = new Details(name,email,mobile);
                detailAdapter.add(details);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
