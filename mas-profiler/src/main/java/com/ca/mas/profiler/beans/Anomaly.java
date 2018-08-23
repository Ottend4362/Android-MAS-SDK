package com.ca.mas.profiler.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Anomaly implements Parcelable {
    private double _benchmark = 0.0;
    private double _recording = 0.0;
    private double _delta = 0.0;

    public Anomaly() {
    }

    protected Anomaly(Parcel in) {
    }

    public static final Creator<Anomaly> CREATOR = new Creator<Anomaly>() {
        @Override
        public Anomaly createFromParcel(Parcel in) {
            return new Anomaly(in);
        }

        @Override
        public Anomaly[] newArray(int size) {
            return new Anomaly[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public double getBenchmark() {
        return _benchmark;
    }

    public void setBenchmark(double benchmark) {
        this._benchmark = benchmark;
    }

    public double getRecording() {
        return _recording;
    }

    public void setRecording(double recording) {
        this._recording = recording;
    }

    public double getDelta() {
        return _delta;
    }

    public void setDelta(double delta) {
        this._delta = delta;
    }
}
