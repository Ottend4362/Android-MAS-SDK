/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 */

package com.ca.mas.foundation;

import android.content.Context;
import android.os.Debug;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ca.mas.MASCallbackFuture;
import com.ca.mas.MASMockGatewayTestBase;
import com.ca.mas.Profiler;
import com.ca.mas.TestUtils;
import com.ca.mas.profiler.ConfigurationManager;
import com.ca.mas.profiler.ProfileConfigurations;
import com.ca.mas.profiler.Scenario;
import com.ca.mas.profiler.ScenarioFactory;
import com.ca.mas.profiler.ScenarioInfo;
import com.ca.mas.profiler.ScenarioManager;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.ca.mas.foundation.MAS.getState;


@RunWith(AndroidJUnit4.class)
public class MASProfileTest extends MASMockGatewayTestBase  {

    private ScenarioInfo[] scenarioInfo ;
    private ProfileConfigurations profileConfigs;
    private Context context;


    @Before
    public void loadConfigs(){
        context = getContext();

        ScenarioManager.getInstance().initialize(context);

    }

       @Test
        public void startFlow(){


            ScenarioManager.getInstance().evaluateScenario();

           Assert.assertTrue("State of SDK= "+MAS.getState(context), false);


       }
}
