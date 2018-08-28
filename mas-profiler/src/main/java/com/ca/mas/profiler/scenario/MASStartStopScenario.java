package com.ca.mas.profiler.scenario;

import android.content.Context;

import com.ca.mas.foundation.MAS;
import com.ca.mas.profiler.Scenario;
import com.ca.mas.profiler.beans.Anomaly;
import com.ca.mas.profiler.beans.ScenarioInfo;

public class MASStartStopScenario implements Scenario {

    @Override
    public void evaluate(Context context) {

        MAS.start(context);
        MAS.stop();

    }

    @Override
    public ScenarioInfo getScenarioInfo(Context context) {
        return null;
    }

    @Override
    public Anomaly getAnomaly(Context context) {
        return null;
    }
}
