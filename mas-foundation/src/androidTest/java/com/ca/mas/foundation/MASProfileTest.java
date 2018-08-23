/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 */

package com.ca.mas.foundation;

import android.support.test.runner.AndroidJUnit4;

import com.ca.mas.MASMockGatewayTestBase;

import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MASProfileTest extends MASMockGatewayTestBase  {

    /*private ScenarioInfo[] scenarioInfo ;
    private ProfileConfigurations profileConfigs;
    private Context context;


    @Before
    public void loadConfigs(){
        context = getContext();
        ConfigurationManager.getInsgtance().loadConfigurations(getContext());
        scenarioInfo = ConfigurationManager.getInsgtance().getScenarioInfo();
        profileConfigs = ConfigurationManager.getInsgtance().getProfileConfigurations();
    }

       @Test
        public void startFlow(){


            for (ScenarioInfo scenario: scenarioInfo){

                    for(int i=0; i<= profileConfigs.getIteration(); i++){
                       MASProfilerEngine.getInstance().getScenario(scenario.getName()).evaluate(context);}

           }

          Assert.assertTrue("State of SDK= "+MAS.getState(context), false);


       }*/
}
