package com.ca.mas.profiler.management;

import android.content.Context;

import com.ca.mas.profiler.beans.ProfilerConfig;
import com.ca.mas.profiler.beans.ScenarioInfo;
import com.ca.mas.profiler.beans.Scenarios;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationManager {
    private static final ConfigurationManager _instance = new ConfigurationManager();
    private ProfilerConfig profilerConfig = null;
    private Map<Integer, ScenarioInfo> scenarioMap = new HashMap<>();
    private  Context context ;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        return _instance;
    }

    protected void loadConfigurations(Context context) {


        this.context = context;
        InputStream profilerConfigStream = null;
        InputStream scenarioConfigStream = null;
        Reader readerScnario = null;
        Reader readerProfile;
        try {
            profilerConfigStream = context.getAssets().open("mas_profiler_master.json");
            scenarioConfigStream = context.getAssets().open("scenario.json");

            Gson gson = new Gson();
            readerScnario = new InputStreamReader(scenarioConfigStream);
            readerProfile = new InputStreamReader(profilerConfigStream);
            Scenarios scenarios = gson.fromJson(readerScnario, Scenarios.class);
            this.profilerConfig = gson.fromJson(readerProfile, ProfilerConfig.class);


            for (ScenarioInfo scenarioInfo : scenarios.getScenarios()) {
                if (scenarioInfo.isEnabled()) {
                    scenarioMap.put(scenarioInfo.getId(), scenarioInfo);
                }
            }

            profilerConfigStream.close();
            scenarioConfigStream.close();
            readerScnario.close();
            readerProfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }

    public ProfilerConfig getProfilerConfig() {
        return profilerConfig;
    }

    public ScenarioInfo getScenarioInfo(Integer scenarioId) {
        return scenarioMap.get(scenarioId);
    }

    public List<ScenarioInfo> getAllScenarios() {

         List<ScenarioInfo> scenarioInfos = new ArrayList<ScenarioInfo>(scenarioMap.values());
        return scenarioInfos;
    }

    public Context getContext() {
        return context;
    }


}
