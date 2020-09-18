package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    HorizontalStepView setpview5=null;
    private Timer myTimer;
    List<StepBean> stepsBeanList;
    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setpview5 = (HorizontalStepView) this.findViewById(R.id.step_view);
        stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("cooking",-1);
        StepBean stepBean1 = new StepBean("picked",-1);
        StepBean stepBean2 = new StepBean("on way",-1);
        StepBean stepBean3 = new StepBean("delivered",-1);
        StepBean stepBean4 = new StepBean("done",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);


        setpview5
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(8)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.black))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.completed_icon))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 5000, 5000);


    }

    void TimerMethod()
    {

        this.runOnUiThread(Timer_Tick);

    }
    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            if(progress<5)
                stepsBeanList.get(progress++).setState(1);
            setpview5
                    .setStepViewTexts(stepsBeanList)//总步骤
                    .setTextSize(12)//set textSize
                    .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_dark))//设置StepsViewIndicator完成线的颜色
                    .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray))//设置StepsViewIndicator未完成线的颜色
                    .setStepViewComplectedTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.black))//设置StepsView text完成线的颜色
                    .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray))//设置StepsView text未完成线的颜色
                    .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.completed_icon))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.attention));
            setpview5.invalidate();

        }
    };
    public void openMaps(View view)
    {
        String geoUri = "http://maps.google.com/maps?q=loc:" + 28.6739022 + "," + 77.134768 + " (" + "My own label"+ ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        this.startActivity(intent);
    }

}
