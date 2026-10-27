package com.wit.example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView selectedFileText;

    private final ActivityResultLauncher<String[]> csvPicker =
            registerForActivityResult(
                    new ActivityResultContracts.OpenDocument(),
                    uri -> {
                        if (uri != null) {
                            handleSelectedCsv(uri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button backButton = findViewById(R.id.backButton);
        Button selectCsvButton = findViewById(R.id.selectCsvButton);
        selectedFileText = findViewById(
                R.id.selectedFileText
        );

        backButton.setOnClickListener(v -> finish());

        // user presses "Select CSV File"
        // then they select "Internal Storage" folder
        // then they select "Download" folder
        // then they select "WIT_MOTION_RECORDINGS" folder
        // then they select the CSV file they want
        // all other file types are not selectable
        selectCsvButton.setOnClickListener(v ->
                csvPicker.launch(new String[]{"text/comma-separated-values"})
        );
    }

    private void handleSelectedCsv(Uri uri) {
        selectedFileText.setText(
                "Selected file:\n" + uri.getLastPathSegment()
        );

        Toast.makeText(
                this,
                "CSV file selected",
                Toast.LENGTH_SHORT
        ).show();
    }
}
