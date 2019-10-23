package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class PatientDoctorLogin extends AppCompatActivity {
    public static final String TAG="PatientLogin";
    private Switch loginOptionSelect;
    private Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor_login);
        loginOptionSelect = (Switch) findViewById(R.id.login_option_selection);
        //For button
        getStarted = (Button) findViewById(R.id.get_started_button);

        //getStartedButton onClick method
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(loginOptionSelect.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), DoctorSignInActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), PatientSignInActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}
