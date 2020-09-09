package com.tonydantona.triads;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextKey;
    private TextView mTextStringSet;
    private TextView mTextChord;
    private Random mRand = new Random();
    private TextView mTextTimerSecs;
    private CountDownTimer mCountDownTimer;
    private Boolean mTimerRunning = false;
    private long mTimeLeftInMilliseconds = 10000;
    private NumberPicker mNumberPicker;
    private Button mBtnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextKey = findViewById(R.id.text_key);
        mTextStringSet = findViewById(R.id.text_stringset);
        mTextChord = findViewById(R.id.text_chord);

        initializeNextButton();
        initializeNumberPicker();
        initializeTextTimerSecs();

        randomlySetTextViews();

        // keeps the window from dimming or sleeping for this activity only
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeTextTimerSecs() {
        mTextTimerSecs = findViewById(R.id.textView_timersecs);

        mTextTimerSecs.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TimerToggle();
                return false;
            }
        });
    }

    private void initializeNumberPicker() {

        mNumberPicker = findViewById(R.id.number_picker_timer);

        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(30);

        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(mTimerRunning) {
                    stopTimer();
                }
                setTextTimerSecs(newVal);
                mTimeLeftInMilliseconds = (newVal + 1) * 1000;
            }
        });

    }

    private void initializeNextButton() {
        mBtnNext = findViewById(R.id.btn_next);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
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

    private void TimerToggle() {
        if(mTimerRunning) {
            stopTimer();
        }
        else {
            startTimer();
        }
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                stopTimer();
                mBtnNext.performClick();
                mTimeLeftInMilliseconds = (long) (mNumberPicker.getValue() + 1) * 1000;
                startTimer();
            }
        }.start();

        mTimerRunning = true;
    }

    private void stopTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void updateTimer() {
        int secsRemaining = (int) mTimeLeftInMilliseconds / 1000;
        mTextTimerSecs.setText(Integer.toString(secsRemaining));
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

    private void setTextTimerSecs(int secs) {
        mTextTimerSecs.setText(Integer.toString(secs));
    }
}