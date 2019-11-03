package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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

//        String url = "http://192.168.0.102:5000/?symptoms="+symptom;
        String url = "http://192.168.42.153:5000/?symptoms="+symptom;
        Log.e("Response url", url);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.e("Responce data", response);
                        diseases = response.toString();
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
                String symptoms = "";
                if(cold.isChecked()){
                    symptoms = symptoms + "cold,";
                }
                if(Cough.isChecked()){
                    symptoms = symptoms + "cough,";
                }
                if(Fever.isChecked()){
                    symptoms = symptoms + "fever,";
                }
                if(HeadAche.isChecked()){
                    symptoms = symptoms + "headache,";
                }
                if(Goldyness.isChecked()){
                    symptoms = symptoms + "giddyness,";
                }
                if(StomachAche.isChecked()){
                    symptoms = symptoms + "stomachache,";
                }
                
                if(symptoms != ""){
                    symptoms = symptoms.substring(0, symptoms.length()-1);
                    text = restApi(symptoms);
                }else{
                    Toast.makeText(PatientHome.this, "Please select the symptoms.", Toast.LENGTH_SHORT).show();
                }
//                if(Cough.isChecked() && Fever.isChecked() && Goldyness.isChecked() ){
//                    text = restApi("cough,fever,giddyness");                }
//                if(StomachAche.isChecked() && Fever.isChecked() && Goldyness.isChecked() ){
//                    text = restApi("stomachache,fever,giddyness");
//                }
//                if(Fever.isChecked() && HeadAche.isChecked() && StomachAche.isChecked() ){
//                    text = restApi("headache,fever,stomachache");
//                }
//                if(cold.isChecked() && Fever.isChecked() && Goldyness.isChecked()  && HeadAche.isChecked()){
//                    text = restApi("cold,fever,giddyness,headache");
//                }
//                if(cold.isChecked() && Cough.isChecked() ){
//                    text = restApi("cold,fever");
////                    edtt.setText("Hello");
////                    edtt.setText(text);
//                }
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
