package com.pau.connecting_to_the_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity<button> extends AppCompatActivity {

    static EditText website, result;
    Button button;

    String websiteURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = findViewById(R.id.website);
        result =findViewById(R.id.result);
        button =findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websiteURL = website.getText().toString();
                DownloadWebsiteInBackground myClass = new DownloadWebsiteInBackground(MainActivity.this);
                myClass.execute(websiteURL);
            }

        });
    }
}