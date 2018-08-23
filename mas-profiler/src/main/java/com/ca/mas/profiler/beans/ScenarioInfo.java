package com.ca.mas.profiler.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ScenarioInfo implements Parcelable {
    private int _id = -1;
    private String _name = null;
    private String _clazz = null;
    private boolean _enabled = false;
    private double _benchmark = 0.0;

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
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getClazz() {
        return _clazz;
    }

    public void setClazz(String clazz) {
        this._clazz = clazz;
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public void setEnabled(boolean enabled) {
        this._enabled = enabled;
    }

    public double getBenchmark() {
        return _benchmark;
    }

    public void setBenchmark(double benchmark) {
        this._benchmark = benchmark;
    }
}
