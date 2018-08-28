package com.ca.mas.profiler.management;

import android.content.Context;

import com.ca.mas.profiler.Scenario;
import com.ca.mas.profiler.beans.ProfilerConfig;
import com.ca.mas.profiler.beans.ScenarioInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenarioManager {
    private static final ScenarioManager instance = new ScenarioManager();
    private List<ScenarioInfo> scenarios;

    private ScenarioManager() {

    }

    public static ScenarioManager getInstance() {
        return instance;
    }

    public void loadScenarios(Context context){


        ConfigurationManager.getInstance().loadConfigurations(context);
        scenarios = ConfigurationManager.getInstance().getAllScenarios();
    }

    public void startScenarios(Context context) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ProfilerConfig profileConfig = ConfigurationManager.getInstance().getProfilerConfig();
        int iterations=0;
          if(profileConfig.getOperationType() == ProfilerConfig.OPERATION_TYPE.BENCHMARK){
              iterations = profileConfig.getIterations();
          }

          for(int i=0; i<= iterations ; i++){

              Class c = Class.forName(scenarios.get(i).getClazz());
              Method method = c.getMethod("evaluate", Context.class);
              //start time
              method.invoke(c.newInstance(), context);
              //end time
          }

    }

}
