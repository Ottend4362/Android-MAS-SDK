    package com.ca.mas.profiler.management;

    import android.content.Context;
    import android.util.Log;

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

        public void startScenarios(Context context)  {
            ProfilerConfig profileConfig = ConfigurationManager.getInstance().getProfilerConfig();
            int iterations=0;
            double sum = 0.0;
              if(profileConfig.getOperationType() == ProfilerConfig.OPERATION_TYPE.BENCHMARK){
                  iterations = profileConfig.getIterations();
              }

              for(int i=0; i<= iterations ; i++){

                  try {
                      Class c = Class.forName(scenarios.get(i).getClazz());
                      Method method = c.getMethod("evaluate", Context.class);
                      long start = System.nanoTime();
                      method.invoke(c.newInstance(), context);
                      long end = System.nanoTime();
                      long duration = end - start;
                      sum = sum+duration;
                  }catch (Exception e){

                  }
              }
            double avg;

            if(iterations == 0) {
                avg = sum;
            } else {
                avg = sum / iterations;
            }
            //TODO  Save result in file using analytics manager
            Log.d("PROFILE", "Execution time for the scenario = "+ avg/1000000000.0 +" Seconds");
        }

    }
