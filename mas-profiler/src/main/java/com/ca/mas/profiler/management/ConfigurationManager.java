package com.ca.mas.profiler.management;

import android.content.Context;

import com.ca.mas.profiler.beans.ProfilerConfig;
import com.ca.mas.profiler.beans.ScenarioInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationManager {
    private static final ConfigurationManager _instance = new ConfigurationManager();
    private ProfilerConfig profilerConfig = null;
    private Map<Integer, ScenarioInfo> scenarioMap = new HashMap<>();

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        return _instance;
    }

    protected void loadConfigurations(Context context) {
        /*
        InputStream scenarioConfigStream = context.getResources().openRawResource(R.raw.scenario_config);
        InputStream profilerConfigStream = context.getResources().openRawResource(R.raw.profiler_config);
        Gson gson = new Gson();
        Reader readerScnario = new InputStreamReader(scenarioConfigStream);
        Reader readerProfile = new InputStreamReader(profilerConfigStream);
        this.scenarioInfo = gson.fromJson(readerScnario, ScenarioInfo[].class);
        this.profileConfigurations = gson.fromJson(readerProfile, ProfileConfigurations.class);
        */
        // TODO: Files have to be read from assets of the consumer test suite
        //TODO: Code to load ProfilerConfig from mas_profiler_master.json
        //.......

        // TODO: Code to load ScenarioInfo from scenario.json. Make sure to load only those scenario with enabled = true
        // .......
    }

    public ProfilerConfig getProfilerConfig() {
        return profilerConfig;
    }

    public ScenarioInfo getScenarioInfo(Integer scenarioId) {
        return scenarioMap.get(scenarioId);
    }

    public List<ScenarioInfo> getAllScenarios() {
        return (List<ScenarioInfo>) scenarioMap.values(); // TODO: Check if this cast ever fails
    }
}
