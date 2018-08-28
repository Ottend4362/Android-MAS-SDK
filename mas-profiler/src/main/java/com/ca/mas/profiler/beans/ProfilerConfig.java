package com.ca.mas.profiler.beans;

import com.ca.mas.profiler.ProfilerConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfilerConfig {
    private JSONObject _profileConfigJSON = null;
    private OPERATION_TYPE operation_type = null;
    private int iteration = 0;


    public int getIterations() {
        return iteration;
    }

    public void setIterations(int iterations) {
        this.iteration = iterations;
    }

    public boolean isBenchmarkOP() {
        return getOperationType() == OPERATION_TYPE.BENCHMARK;
    }

    public OPERATION_TYPE getOperationType() {
        return operation_type;
    }

    public void setOperationType(OPERATION_TYPE operationType) {
        this.operation_type = operationType;
    }

    public enum OPERATION_TYPE {
        BENCHMARK("benchmark"),
        PROFILE("profile");
        private String name = null;

        OPERATION_TYPE(String name) {
            this.name = name;
        }

        public String getOperationType() {
            return this.name;
        }

        public static boolean isValid(String opType) {
            return BENCHMARK.getOperationType().equalsIgnoreCase(opType) || PROFILE.getOperationType().equalsIgnoreCase(opType);
        }

        private static Map<String, OPERATION_TYPE> lookupMap = new HashMap<>();

        static {
            for (OPERATION_TYPE ops : OPERATION_TYPE.values()) {
                lookupMap.put(ops.name.toLowerCase(), ops);
            }
        }

        public static OPERATION_TYPE lookup(String name) {
            return name == null ? null : lookupMap.get(name.toLowerCase());
        }
    }

    public ProfilerConfig(JSONObject profileConfigJSON) {
        this._profileConfigJSON = profileConfigJSON;
        if (this._profileConfigJSON == null) {
            throw new IllegalArgumentException("Invalid Master Profile JSON");
        }
        try {
            String opType = this._profileConfigJSON.getString(ProfilerConstants.OP_TYPE);
            if (!OPERATION_TYPE.isValid(opType)) {
                throw new IllegalArgumentException("Invalid OPERATION_TYPE");
            }
            this.operation_type = OPERATION_TYPE.lookup(opType);
            this.iteration = this._profileConfigJSON.optInt(ProfilerConstants.ITERATION, 0);
            if (this.operation_type == OPERATION_TYPE.BENCHMARK && this.iteration <= 0) {
                throw new IllegalArgumentException("Invalid iteration number provided for operation BENCHMARK");
            }
        } catch (JSONException jce) {
            throw new IllegalArgumentException("Invalid OPERATION_TYPE");
        }
    }
}
