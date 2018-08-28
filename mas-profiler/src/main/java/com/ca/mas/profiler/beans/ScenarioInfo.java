package com.ca.mas.profiler.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ScenarioInfo implements Parcelable {
    private int id = -1;
    private String name = null;
    private String class_name = null;
    private boolean enabled = false;
    private double benchmark = 0.0;

    public ScenarioInfo() {

    }

    protected ScenarioInfo(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScenarioInfo> CREATOR = new Creator<ScenarioInfo>() {
        @Override
        public ScenarioInfo createFromParcel(Parcel in) {
            return new ScenarioInfo(in);
        }

        @Override
        public ScenarioInfo[] newArray(int size) {
            return new ScenarioInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return class_name;
    }

    public void setClazz(String clazz) {
        this.class_name = clazz;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(double benchmark) {
        this.benchmark = benchmark;
    }
}
