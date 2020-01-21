package com.example.weatherapplicaton;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

//    TextView cityName;
    Button searchButton;
    TextView result;

    public void search(View view) {

        searchButton = findViewById(R.id.searchButton);
        result = findViewById(R.id.result);

//        String cName = cityName.getText().toString();

//        String content;
        String book;
        Weather weather = new Weather();
        try {
//            content = weather.execute("https://openweathermap.org/data/2.5/weather?q="+cName+"&appid=b6907d289e10d714a6e88b30761fae22").get();
//            //First we will check data is retrieve successfully or not
//            Log.i("contentData",content);
//            JSONObject jsonObject = new JSONObject(content);
//            String weatherData = jsonObject.getString("weather");
//            Log.i("weatherData", weatherData);
//
//            JSONArray array = new JSONArray(weatherData);
//
//            String main = "";
//            String description = "";
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject weatherPart = array.getJSONObject(i);
//                main = weatherPart.getString("main");
//                description = weatherPart.getString("description");
//            }
//
//            Log.i("main", main);
//            Log.i("descrition", description);
//
//            String resultText = "Main : " + main + "\nDescription : " + description;
//
//            result.setText(resultText);
            String author = "";
            String content = "";
            book = weather.execute("http://nguyenluan2603.pythonanywhere.com/quotes/random").get();
            JSONArray array = new JSONArray(book);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                author = obj.getString("author");
                content = obj.getString("content");

            }

            String resultText = "Author : " + author + "\nContent : " + content;
            result.setText(resultText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Weather extends AsyncTask<String, Void, String>{  //First String means URL is in String, Void mean nothing, Third String means Return type will be String

        @Override
        protected String doInBackground(String... address) {
            //String... means multiple address can be send. It acts as array
            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Establish connection with address
                connection.connect();

                //retrieve data from url
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                //Retrieve data and return it as String
                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1){
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                Log.i("Content",content);
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("HEllo World","whats'up");


    }
}

