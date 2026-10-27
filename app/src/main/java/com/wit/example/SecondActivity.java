
package com.wit.example;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private TextView selectedFileText;
    private List<SensorData> loadedSensorData = new ArrayList<>();

    public static class SensorData {
        public long time;
        public String deviceName;
        public String accX, accY, accZ;
        public String asX, asY, asZ;
        public String angleX, angleY, angleZ;
        public String hX, hY, hZ;
        public String temperature, batteryPercentage, version;
    }

    public List<SensorData> getLoadedSensorData() {
        return new ArrayList<>(loadedSensorData);
    }

    private final ActivityResultLauncher<String[]> csvPicker =
            registerForActivityResult(
                    new ActivityResultContracts.OpenDocument(),
                    uri -> {
                        if (uri != null) handleSelectedCsv(uri);
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button backButton = findViewById(R.id.backButton);
        Button selectCsvButton = findViewById(R.id.selectCsvButton);
        selectedFileText = findViewById(R.id.selectedFileText);

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
        selectedFileText.setText("Loading CSV...");

        new Thread(() -> {
            try {
                List<SensorData> parsedData = parseCsv(uri);

                runOnUiThread(() -> {
                    loadedSensorData = parsedData;
                    selectedFileText.setText(
                            "CSV loaded successfully\nRecords: "
                                    + loadedSensorData.size()
                                    + "\nFile: " + uri.getLastPathSegment()
                    );

                    Toast.makeText(this,
                            "Loaded " + loadedSensorData.size()
                                    + " records from "
                                    + uri.getLastPathSegment(),
                            Toast.LENGTH_LONG).show();

                    Log.d(TAG, "Parsed records: " + loadedSensorData.size());
                });
            } catch (Exception e) {
                Log.e(TAG, "Failed to parse CSV", e);
                runOnUiThread(() -> {
                    selectedFileText.setText("Failed to load CSV");
                    Toast.makeText(this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }

    private List<SensorData> parseCsv(Uri uri) throws IOException {
        List<SensorData> records = new ArrayList<>();

        try (InputStream input = getContentResolver().openInputStream(uri)) {
            if (input == null) throw new IOException("Unable to open file");

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {

                String header = reader.readLine();
                if (header == null) throw new IOException("CSV is empty");

                if (header.startsWith("\uFEFF")) {
                    header = header.substring(1);
                }

                String[] headers = header.split(",", -1);
                Map<String, Integer> columns = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    columns.put(headers[i].trim().toLowerCase(Locale.ROOT), i);
                }

                if (!columns.containsKey("timestamp_ms")
                        || !columns.containsKey("device_name")) {
                    throw new IOException("Missing required CSV columns");
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    String[] v = line.split(",", -1);
                    SensorData d = new SensorData();

                    d.time = parseLong(get(v, columns, "timestamp_ms"));
                    d.deviceName = get(v, columns, "device_name");

                    d.accX = get(v, columns, "accx");
                    d.accY = get(v, columns, "accy");
                    d.accZ = get(v, columns, "accz");

                    d.asX = get(v, columns, "asx");
                    d.asY = get(v, columns, "asy");
                    d.asZ = get(v, columns, "asz");

                    d.angleX = get(v, columns, "anglex");
                    d.angleY = get(v, columns, "angley");
                    d.angleZ = get(v, columns, "anglez");

                    d.hX = get(v, columns, "hx");
                    d.hY = get(v, columns, "hy");
                    d.hZ = get(v, columns, "hz");

                    d.temperature = get(v, columns, "temperature");
                    d.batteryPercentage = get(v, columns, "batterypercentage");
                    d.version = get(v, columns, "version");

                    records.add(d);
                }
            }
        }

        return records;
    }

    private String get(String[] values, Map<String, Integer> columns,
                       String name) {
        Integer index = columns.get(name);
        return index == null || index >= values.length ? "" : values[index];
    }

    private long parseLong(String value) {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}