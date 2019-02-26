package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timer = findViewById(R.id.timer);

        TextView remainingTime = findViewById(R.id.textView);

        timer.setMax(600);

        timer.setProgress(30);

        remainingTime.setText(getTime(timer.getProgress()));

        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                SeekBar timer = findViewById(R.id.timer);

                TextView remainingTime = findViewById(R.id.textView);

                remainingTime.setText(getTime(timer.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public String getTime(int time) {

        return (time/60) +":" +String.format("%02d",(time%60));

    }

    public void startCountDown(View view) {

        Button button = findViewById(R.id.button);

        SeekBar timer = findViewById(R.id.timer);

        final MediaPlayer mplayer;
        mplayer = MediaPlayer.create(this,R.raw.airhorn);

        timer.setEnabled(false);

        CountDownTimer countDown = new CountDownTimer(timer.getProgress()*1000,1000){
            public void onTick(long value){
                if(!count) {
                    this.cancel();
                }
                else {
                    SeekBar timer = findViewById(R.id.timer);

                    timer.setProgress((int) value/1000);
                }

            }
            public void onFinish(){
                Button button = findViewById(R.id.button);
                SeekBar timer = findViewById(R.id.timer);
                mplayer.start();
                button.setText("GO!");
                timer.setEnabled(true);
                count = false;
            }
        };

        if(button.getText()=="STOP!") {
            button.setText("GO!");
            timer.setEnabled(true);
            count = false;
            return;
        }
        count = true;
        button.setText("STOP!");
        countDown.start();

    }
}
