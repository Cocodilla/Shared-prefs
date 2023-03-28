package com.example.basememory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText et;
    Button btnSafe, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "saved Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.etText);
        btnSafe = (Button) findViewById(R.id.btnSafe);
        btnLoad = (Button) findViewById(R.id.btnLoad);

//btnSafe.setOnClickListener(this);
//btnLoad.setOnClickListener(this);
loadText();

et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent Event) {
        saveText();
        Log.d(SAVED_TEXT,et.getText().toString());
        return true;
    }
});

    }

//    @Override
//    public void onClick(View view) {
//        switch(view.getId()){
//            case R.id.btnSafe:
//                saveText();
//                break;
//            case R.id.btnLoad:
//                loadText();
//                break;
//            default:
//                break;
//        }
//    }

    private void loadText() {

        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVED_TEXT,"Nothing was saved");
        et.setText(savedText);
        Toast.makeText(this, "The text was loaded", Toast.LENGTH_LONG).show();

    }

    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT, et.getText().toString());
        ed.commit();
        Toast.makeText(this, "The text was saved", Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}