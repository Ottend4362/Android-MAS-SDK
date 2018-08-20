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

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MASProfileStartTest extends MASMockGatewayTestBase {


    @Override
    public Context getContext() {
        return super.getContext();
    }


    @Test

    public void loginFlow() throws InterruptedException, ExecutionException {

        long sum = 0;
        BigDecimal duration = null;


        List<BigDecimal> bdArray = new ArrayList<>();
        int noOfIterations = 1;


        for (int i = 0; i < noOfIterations; i++) {

            Debug.startMethodTracing();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            long start = System.nanoTime();
            MAS.start(getContext());
            //start();

            MAS.setGrantFlow(MASConstants.MAS_GRANT_FLOW_PASSWORD);

            int finalI = i;
            MASUser.login("admin", "7layer".toCharArray(), new MASCallback<MASUser>() {

                @Override
                public void onSuccess(MASUser result) {
                    //Log.d("LOGIN", "Login success for iteration -"+ finalI);

                    //result.logout(true, null);
                    MASDevice.getCurrentDevice().deregister(new MASCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            //Log.d("LOGIN", "Deregister success for iteration -"+ finalI+" For user "+MASUser.getCurrentUser());
                            countDownLatch.countDown();
                        }

                        @Override
                        public void onError(Throwable e) {
                            //Log.d("LOGIN", "Deregister failed for iteration -"+ finalI);
                            countDownLatch.countDown();
                        }
                    });


                }

                @Override
                public void onError(Throwable e) {
                    //Log.d("LOGIN", "Login failed for iteration -"+ finalI);
                    countDownLatch.countDown();

                }
            });

            long end = System.nanoTime();
            duration = new BigDecimal(end - start).divide(new BigDecimal(1000000000));
            bdArray.add(duration);
            sum = sum + (end - start);

            Log.d("LOGIN", "Duration of login flow for iteration " + i + " = " + duration);

            try {
                //Log.d("LOGIN", "Before countdown await");
                countDownLatch.await();
                //Log.d("LOGIN", "After countdown await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


        BigDecimal avg = average(bdArray, RoundingMode.CEILING);
        Log.d("LOGINSUM", "Averag time = " + avg + " micro secs");
        Log.d("LOGIN", "Average from sum= " + sum / (noOfIterations * 1000) + " Micro seconds");

        //writeToFile("Execution time - loginFlow \t\t\t"+avg);

        assertTrue("avg = " + avg, false);


    }


    ////////////////////////////

    @Test

    public void loginTest() throws InterruptedException, ExecutionException {

        long sum = 0;
        BigDecimal duration = null;


        List<BigDecimal> bdArray = new ArrayList<>();
        int noOfIterations = 2;

        for (int i = 0; i < noOfIterations; i++) {

            long start = System.nanoTime();
            MAS.start(getContext());
            //start();

            MAS.setGrantFlow(MASConstants.MAS_GRANT_FLOW_PASSWORD);

            int finalI = i;
            MASCallbackFuture<MASUser> callback = new MASCallbackFuture<>();
            MASUser.login("admin", "7layer".toCharArray(), callback);
            MASUser obj = callback.get();
            MASCallbackFuture<Void> callbackFuture = new MASCallbackFuture<>();
            MASDevice.getCurrentDevice().deregister(callbackFuture);
            callbackFuture.get();
            long end = System.nanoTime();
            duration = new BigDecimal(end - start).divide(new BigDecimal(1000000000));
            bdArray.add(duration);
            sum = sum + (end - start);

            Log.d("LOGIN", "Duration of login flow for iteration " + i + " = " + duration);




        }


       // MASUser.getCurrentUser().logout(true, null);
        BigDecimal avg = average(bdArray, RoundingMode.CEILING);
        Log.d("LOGINSUM", "Averag time = " + avg + " micro secs");
        Log.d("LOGIN", "Average from sum= " + sum / (noOfIterations * 1000) + " Micro seconds");

        //writeToFile("Execution time - loginFlow \t\t\t"+avg);

        assertTrue("avg = " + avg, false);


    }
    ///////////////////////////







    public BigDecimal average(List<BigDecimal> bigDecimals, RoundingMode roundingMode) {
        BigDecimal sum = bigDecimals.stream()
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(bigDecimals.size()), roundingMode);
    }


    public void writeToFile(String data) {

        try (OutputStream outFile = Files.newOutputStream(Paths.get("result.txt"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
             PrintStream newSysOut = new PrintStream(outFile)) {
            System.setOut(newSysOut);
            System.out.print(data);

        } catch (IOException ex) {
            // your code here
        } finally {
            System.setOut(System.out);
        }
    }



    @Test
    public void startTest(){
        long start = System.nanoTime();
        MAS.start(getContext());
        long end = System.nanoTime();
        Log.d("LOGIN", "Start duration = "+(end - start)/1000000);
    }

}
