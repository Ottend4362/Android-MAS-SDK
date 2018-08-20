package com.ca.mas.profiler;

public class ScenarioFactory {

    private static ScenarioFactory _instance;

    private ScenarioFactory(){

    }

    public static ScenarioFactory getInstance(){
        if(_instance == null){
            _instance = new ScenarioFactory();
        }
       return _instance;
    }
    public  Scenario getScenario(String scenarioName){

            if(scenarioName.equalsIgnoreCase("start")){
                return new StartScenario();
            } else {
                return null;
            }
    }
}
