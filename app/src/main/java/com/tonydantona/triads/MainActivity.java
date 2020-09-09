package com.tonydantona.triads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTextKey;
    private TextView mTextStringSet;
    private TextView mTextChord;
    private Random mRand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextKey = findViewById(R.id.text_key);
        mTextStringSet = findViewById(R.id.text_stringset);
        mTextChord = findViewById(R.id.text_chord);

        initializeNextButton();

        randomlySetTextViews();
    }

    private void initializeNextButton() {
        Button btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomlySetTextViews();
            }
        });
    }

    public void randomlySetTextViews() {
        mTextStringSet.setText(GetRandomStringSet());
        mTextKey.setText(GetRandomKey());
        mTextChord.setText(GetRandomChord());
    }

    public String GetRandomKey()
    {
        int r = mRand.nextInt(DataManager.getInstance().getKeys().size());
        return DataManager.getInstance().getKey(r).getName();
    }

    public String GetRandomStringSet() {
        int r = mRand.nextInt(DataManager.getInstance().getStringSets().size());
        return DataManager.getInstance().getStringSet(r).getName();
    }

    public String GetRandomChord() {
        int r = mRand.nextInt(DataManager.getInstance().getChords().size());
        return DataManager.getInstance().getChord(r).getName();
    }
}