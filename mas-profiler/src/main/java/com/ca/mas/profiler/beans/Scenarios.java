package com.ca.mas.profiler.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Scenarios  {
    ScenarioInfo [] scenarios;

    public ScenarioInfo[] getScenarios() {
        return scenarios;
    }

    public void setScenarios(ScenarioInfo[] scenarios) {
        this.scenarios = scenarios;
    }
}
