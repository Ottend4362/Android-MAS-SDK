package com.ca.mas.profiler.management;

import android.content.Context;

import com.ca.mas.profiler.Scenario;

public class AnalyticsManager {
    private static final AnalyticsManager instance = new AnalyticsManager();
    private AnalyticsManager(){
        // TODO: Ready the Configuration data from ConfigurationManager
    }

    public static AnalyticsManager getInstance(){
        return instance;
    }

    protected void saveScenario(Context context, Scenario scenario){

    }
}
