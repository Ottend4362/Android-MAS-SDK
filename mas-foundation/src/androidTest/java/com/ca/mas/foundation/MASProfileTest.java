/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 */

package com.ca.mas.foundation;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.ca.mas.MASMockGatewayTestBase;
import com.ca.mas.profiler.management.ScenarioManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;


@RunWith(AndroidJUnit4.class)
public class MASProfileTest extends MASMockGatewayTestBase  {


    private Context context;


    @Before
    public void loadConfigs(){
        context = getContext();

        ScenarioManager.getInstance().loadScenarios(context);
    }

       @Test
        public void startFlow(){


               ScenarioManager.getInstance().startScenarios(context);

           Assert.assertTrue("State of SDK= "+MAS.getState(context), false);


       }
}
