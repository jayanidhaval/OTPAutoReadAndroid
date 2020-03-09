package com.dhaval.otpautoread.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dhaval.otpautoread.R;
import com.dhaval.otpautoread.app.App;
import com.dhaval.otpautoread.interfaces.OtpReceivedInterface;
import com.goodiebag.pinview.Pinview;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Created on : March 09, 2020
 * Author     : Dhaval Jayani
 */
public class MainActivity extends AppCompatActivity implements OtpReceivedInterface, View.OnClickListener {

    private Pinview pinview;
    private Button buttonRequestForOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
    }

    private void initialization() {
        pinview = findViewById(R.id.pinview);
        buttonRequestForOTP = findViewById(R.id.buttonRequestForOTP);
        buttonRequestForOTP.setOnClickListener(this);
    }

    @Override
    public void onOtpReceived(String otp) {
        if (pinview != null) {
            pinview.setValue(otp);
        }
    }

    @Override
    public void onOtpTimeout() {
        Toast.makeText(this, "Time out, please resend", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRequestForOTP:
                startSMSListener();
                break;
        }
    }

    public void startSMSListener() {
        SmsRetrieverClient mClient = SmsRetriever.getClient(this);
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Start SMS Listener: " + App.GetAppSignature(), Toast.LENGTH_LONG).show();
            }
        });
        mTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
