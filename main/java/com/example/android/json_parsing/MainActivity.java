package com.example.android.json_parsing;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getJson(View view) {
        new BackgroundTask().execute();
    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;
        String json_string;
        @Override
        protected void onPreExecute() {
            json_url = "https://bested-anchor.000webhostapp.com/get_json.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                StringBuilder json_data = new StringBuilder();
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((json_string = reader.readLine()) != null) {
                    json_data.append(json_string).append(json_string + "\n");
                }
                reader.close();
                in.close();
                httpURLConnection.disconnect();
                return json_data.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView jsonText = (TextView) findViewById(R.id.jsontext);
            jsonText.setText(result);
            JSON_STRING = result;
        }

    }

    public void displayList(View view) {
        if (JSON_STRING == null) {
            Toast.makeText(getApplicationContext(), "Get Json", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, DisplayList.class);
            intent.putExtra("json_data", JSON_STRING);
            startActivity(intent);
        }
    }
}