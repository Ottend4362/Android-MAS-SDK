package com.ca.mas.profiler;

import android.content.Context;

import com.ca.mas.foundation.MAS;

public class StartScenario implements Scenario {

    @Override
    public void evaluate(Context context) {
        MAS.start(context);
    }
}
