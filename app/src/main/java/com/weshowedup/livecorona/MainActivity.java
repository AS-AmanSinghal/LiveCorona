package com.weshowedup.livecorona;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION_CODE=1;
    TextInputEditText name,email,mobile;
    TextView location;
    Button button;
    ProgressBar progressBar;
    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.login_name);
        email=findViewById(R.id.login_email);
        mobile=findViewById(R.id.login_mobile);
        location=findViewById(R.id.login_location);
        button=findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.login_progress);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                requestPermission();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
//                Toast.makeText(MainActivity.this,latitude+" "+longitude,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,QuestionActivity.class));
                finish();
            }
        });

        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (String.valueOf(name.getText()).trim().isEmpty() || String.valueOf(email.getText()).trim().isEmpty()
                        || String.valueOf(mobile.getText()).trim().isEmpty()
                        || String.valueOf(mobile.getText()).length() != 10
                        || String.valueOf(location.getText()).equals("Click For Location")
                        || !String.valueOf(email.getText()).contains("@")
                        || !String.valueOf(email.getText()).contains("."))
                {
                    button.setBackgroundResource(R.drawable.disablebuttonbackground);
                    button.setEnabled(false);
                }
                else
                {
                    button.setEnabled(true);
                    button.setBackgroundResource(R.drawable.enablebuttonbackground);
                }
            }
        };
        name.addTextChangedListener(textWatcher);
        email.addTextChangedListener(textWatcher);
        mobile.addTextChangedListener(textWatcher);
        location.addTextChangedListener(textWatcher);
        button.setEnabled(false);

    }

    private void requestPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION_CODE
            );
        }else
        {
            getCurrentLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_LOCATION_PERMISSION_CODE && grantResults.length>0)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }else
            {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation()
    {
        final LocationRequest locationRequest=new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                .requestLocationUpdates(locationRequest,new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult!=null && locationResult.getLocations().size()>0)
                        {
                            int latestLocationindex=locationResult.getLocations().size()-1;
                            latitude=locationResult.getLocations().get(latestLocationindex).getLatitude();
                            longitude=locationResult.getLocations().get(latestLocationindex).getLongitude();
                            location.setText("Longitude "+latitude+" Longitude "+longitude);
                        }
                    }
                }, Looper.getMainLooper());
    }
}