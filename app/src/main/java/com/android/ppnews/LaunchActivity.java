package com.android.ppnews;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;

public class LaunchActivity extends PPActivity {
    private CountDownTimer countDownTimer;
    @Override
    public int getLayout() {
        return R.layout.activity_launch;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.content;
    }

    @Override
    protected void init() {
        super.init();
         countDownTimer = new CountDownTimer(3000,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
                overridePendingTransition(android.R.anim.fade_in,0);

                finish();
                overridePendingTransition(0,android.R.anim.accelerate_interpolator);
            }
        };
        countDownTimer.start();
    }

    public void skip(View view){
        if(countDownTimer!=null) {
            countDownTimer.cancel();
        }
        startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
        overridePendingTransition(android.R.anim.fade_in,0);

        finish();
        overridePendingTransition(0,android.R.anim.accelerate_interpolator);
    }
}
