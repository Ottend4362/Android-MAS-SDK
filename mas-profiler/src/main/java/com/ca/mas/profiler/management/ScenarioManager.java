package com.ca.mas.profiler.management;

import android.content.Context;

import com.ca.mas.profiler.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenarioManager {
    private static final ScenarioManager instance = new ScenarioManager();
    private static final List<Scenario> scenarios = new ArrayList<>();
    private ScenarioManager() {

    }

    public static ScenarioManager getInstance() {
        return instance;
    }

    protected void loadScenarios(Context context){
        //TODO: Load Scenarios from ConfigManager.getAllScenarios
        //TODO: Initialize all using Reflection and load into scenarios list
    }

    protected void startScenarios(Context context){
        //TODO: Loop through the list and call execute of each scenario.Best to be done one by one, else multithreaded approch can be looked into also
    }

}
