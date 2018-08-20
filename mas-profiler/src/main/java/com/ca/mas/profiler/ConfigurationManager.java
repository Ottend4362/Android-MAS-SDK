package com.ca.mas.profiler;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ConfigurationManager {

    private static ConfigurationManager _instance;
    private ScenarioInfo[] scenarioInfo ;
    private ProfileConfigurations profileConfigurations;


    private ConfigurationManager(){

    }

    public static ConfigurationManager getInsgtance(){
        if(_instance == null){
            _instance = new ConfigurationManager();
        }
        return _instance;
    }

    public void loadConfigurations(Context context){

        InputStream scenarioConfigStream = context.getResources().openRawResource(R.raw.scenario_config);
        InputStream profilerConfigStream = context.getResources().openRawResource(R.raw.profiler_config);
        Gson gson = new Gson();
        Reader readerScnario = new InputStreamReader(scenarioConfigStream);
        Reader readerProfile = new InputStreamReader(profilerConfigStream);
        this.scenarioInfo = gson.fromJson(readerScnario, ScenarioInfo[].class);
        this.profileConfigurations = gson.fromJson(readerProfile, ProfileConfigurations.class);


    }

    public ScenarioInfo[] getScenarioInfo() {
        return scenarioInfo;
    }

    public void setScenarioInfo(ScenarioInfo[] scenarioInfo) {
        this.scenarioInfo = scenarioInfo;
    }

    public ProfileConfigurations getProfileConfigurations() {
        return profileConfigurations;
    }

    public void setProfileConfigurations(ProfileConfigurations profileConfigurations) {
        this.profileConfigurations = profileConfigurations;
    }
}
