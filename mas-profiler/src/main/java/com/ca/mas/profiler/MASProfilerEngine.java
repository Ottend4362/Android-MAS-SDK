package com.ca.mas.profiler;

import android.content.Context;

public class MASProfilerEngine {
    private static MASProfilerEngine _instance = new MASProfilerEngine();

    private MASProfilerEngine() {

    }

    public static MASProfilerEngine getInstance() {
        return _instance;
    }

    public void initialize(Context context){

    }

    public void startSuite(){

    }
}
