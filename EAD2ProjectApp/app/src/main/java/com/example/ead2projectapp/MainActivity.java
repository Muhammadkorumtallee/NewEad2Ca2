package com.example.ead2projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_teams;
    ListView playerlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_teams = findViewById(R.id.teams);
        playerlist = (ListView) findViewById(R.id.playerlist);

        btn_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate the RequestQueue
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ead2projectapi20220421170132.azurewebsites.net/api/Players/";


                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String playerID = "";
                        JSONObject playerInfo = new JSONObject();
                        ArrayList<String> allPlayers = new ArrayList<>();
                        try {
                            for (int i = 0; i < 7; i++){
                                playerInfo = response.getJSONObject(i);
                                playerID = "";
                                playerID = playerInfo.getString("playername") + " " + playerInfo.getString("position");
                                allPlayers.add(playerID);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, allPlayers);
                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error getting json for players", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);
            }

        });
    }
}

/*btn_teams = findViewById(R.id.teams);
        et_input_value_text = findViewById(R.id.input_value_text);

        btn_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate the RequestQueue
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ead2projectapi20220421170132.azurewebsites.net/api/";


                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "Players/", null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String teamID = "";

                        try {
                            JSONObject teaminfo = response.getJSONObject(0);
                            teamID = teaminfo.getString("teamId");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error getting json for players", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);
            }

        });*/

//Request string response from provided url
                /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(MainActivity.this, "Error getting players", Toast.LENGTH_SHORT).show();
                            }
                        });*/
//add request the requestQueue

//Toast.makeText(MainActivity.this, "Clicked me", Toast.LENGTH_SHORT).show();