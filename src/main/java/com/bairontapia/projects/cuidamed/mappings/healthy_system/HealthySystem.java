package com.bairontapia.projects.cuidamed.mappings.healthy_system;

public enum HealthySystem {
    PUBLIC("Fonasa"),
    PRIVATE("Isapre");

    private static final HealthySystem[] VALUES = values();
    private final String system;

    HealthySystem(final String system){
        this.system = system;
    }

    public static HealthySystem[] getValues(){
        return VALUES;
    }

    public static HealthySystem getValueFromIndex(final int index){
        if(index < 1 || index > VALUES.length){
            throw new IllegalArgumentException();
        }
        return VALUES[index-1];
    }

    public String toString(){
        return system;
    }

    public String getStatus(){
        return system;
    }
}
