package com.ca.mas.profiler;

import android.content.Context;

import com.ca.mas.profiler.beans.Anomaly;
import com.ca.mas.profiler.beans.ScenarioInfo;

public interface Scenario {

    void evaluate(Context context);

    ScenarioInfo getScenarioInfo(Context context);

    Anomaly getAnomaly(Context context);
}

