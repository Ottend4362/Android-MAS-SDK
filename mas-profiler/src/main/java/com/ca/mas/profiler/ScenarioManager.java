package com.ca.mas.profiler;

import android.content.Context;
import android.graphics.Bitmap;

public class ScenarioManager {

private static ScenarioManager _instance;
    private  ScenarioInfo[] scenarioInfo ;
    private ProfileConfigurations profileConfigs;
    private  Context context;


private ScenarioManager(){}



public static ScenarioManager getInstance(){

    if(_instance == null){
        _instance = new ScenarioManager();
    }
    return _instance;
}



public  void initialize(Context mContext){
    context = mContext;
    ConfigurationManager.getInsgtance().loadConfigurations(context);
    scenarioInfo = ConfigurationManager.getInsgtance().getScenarioInfo();
    profileConfigs = ConfigurationManager.getInsgtance().getProfileConfigurations();
}

public void evaluateScenario(){

    for (ScenarioInfo scenario: scenarioInfo){

        for(int i=0; i<= profileConfigs.getIteration(); i++){
            ScenarioFactory.getInstance().getScenario(scenario.getName()).evaluate(context);}

    }
}
public  ScenarioInfo[] getScenarios(){

    return ConfigurationManager.getInsgtance().getScenarioInfo();

}


}
