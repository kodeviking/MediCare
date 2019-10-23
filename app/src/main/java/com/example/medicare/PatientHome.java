package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class PatientHome extends AppCompatActivity {
    CheckBox cold,Fever,Cough,HeadAche,Goldyness,StomachAche;
    TextView edtt;
    Button Submit,btnn,mapbut;
    String diseases, text;

    String restApi(String symptom){

        String url = "http://10.0.7.150:5000/?symptoms="+symptom;
        Log.e("Response url", url);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        diseases = response.substring(0,500);
                        edtt.setText(diseases);
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    diseases = error.toString();
                    Log.e("Error connection", error.toString());
                    edtt.setText(diseases);

                }
        });


        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );



        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return diseases;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        mapbut= (Button)findViewById(R.id.mapbut);
        cold=(CheckBox)findViewById(R.id.cold);
        Fever=(CheckBox)findViewById(R.id.fever);
        Cough=(CheckBox)findViewById(R.id.cough);
        HeadAche=(CheckBox)findViewById(R.id.headache);
        Goldyness=(CheckBox)findViewById(R.id.giddyness);
        StomachAche=(CheckBox)findViewById(R.id.stomachache);
        edtt=(TextView) findViewById(R.id.edt);
        Submit=(Button)findViewById(R.id.submitSymptoms);
        btnn=(Button)findViewById(R.id.btn);



        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PatientHome.this,ImageUpload.class);
                startActivity(in);
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cold.isChecked() && Fever.isChecked() ){
                    text = restApi("cold,fever");
//                    edtt.setText("Hello");
//                    edtt.setText(text);
                }
                if(cold.isChecked() && Fever.isChecked() && Goldyness.isChecked() ){
                    edtt.setText("Common Cold or Sinus\nCommon Viral infection in nose and throat");
                }
                if(StomachAche.isChecked() && Fever.isChecked() && Goldyness.isChecked() ){
                    edtt.setText("Stomach Flu\nIntestinal infrection marked by diarrhoea");
                }
                if(HeadAche.isChecked() && Fever.isChecked() && StomachAche.isChecked() ){
                    edtt.setText("Dengue Fever\nMosquito bourne viral disease");
                }
                if(cold.isChecked() && Fever.isChecked() && Goldyness.isChecked()  && HeadAche.isChecked()){
                    edtt.setText("Vertigo\nLabyrinthitis is usually caused by a viral infection, such as the common cold or flu, which spreads to the labyrinth");
                }
            }
        });

        mapbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHome.this,MapAcitvity.class);
                startActivity(intent);
            }
        });







    }
}
