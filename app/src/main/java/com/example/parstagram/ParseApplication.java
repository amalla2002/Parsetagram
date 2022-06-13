package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TxvZF3fRL0bhy5mmdEiGTC2ppmYrpk451SIPah2d")
                .clientKey("mvKsBVONgxMDPqyiVRzpCJmCpThqfd6rg8eTxHsF")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
