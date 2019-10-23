package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class PatientHome extends AppCompatActivity {
    CheckBox cold,Fever,Cough,HeadAche,Goldyness,StomachAche;
    TextView edtt;
    Button Submit,btnn,mapbut;


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
                    edtt.setText("Flu\nA common viral infection that can be deadly, especially in high-risk groups.");
                }
                if(cold.isChecked() && Fever.isChecked() && Goldyness.isChecked() ){
                    edtt.setText("Common Cold or Sinus\nCommon Viral infection in nose and throat");
                }
                if(StomachAche.isChecked() && Fever.isChecked() && Fever.isChecked() ){
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
