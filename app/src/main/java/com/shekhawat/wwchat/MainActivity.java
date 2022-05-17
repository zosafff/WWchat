package com.shekhawat.wwchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.shekhawat.wwchat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

    }

    private void init(){

        binding.ccp.detectNetworkCountry(true);
        binding.ccp.detectLocaleCountry(true);

        binding.btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        binding.txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

            //    if (mInterstitialAd != null)
             //       mInterstitialAd.show(DashboardActivity.this);
            //    else {
            //        loadAd();
                    Intent inte = new Intent(MainActivity.this, WebViewActivity.class);
                    inte.putExtra("url", "https://sites.google.com/view/ww-chat/home");
                    startActivity(inte);
                }

         //   }
        });
    }

    private void sendMessage(){

        String phoneNumberWithCountryCode = binding.ccp.getSelectedCountryCodeWithPlus()
                + binding.txtNumber.getText().toString();
        String message = "Hello";
        startActivity(
                new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                        )
                )
        );
    }
}