package com.bairontapia.projects.cuidamed.mappings.typeallergy;

public enum TypeAllergy {
    DRUG_ALLERGY("medicamentos"),
    FOOD_ALLERGY("comidas"),
    INSECT_ALLERGY("insectos"),
    LATEX_ALLERGY("latex"),
    MOLD_ALLERGY("moho"),
    PET_ALLERGY("mascotas"),
    POLLEN_ALLERGY("polen");

    private static final TypeAllergy[] VALUES = values();

    private final String name;

    TypeAllergy(String name){
        this.name = name;
    }

    public static TypeAllergy[] getValues() {
        return VALUES;
    }

    public static TypeAllergy getValueFromIndex(final int index){
        if(index < 1 || index > VALUES.length){
            throw new IllegalArgumentException();
        }
        return VALUES[index - 1];
    }

    @Override
    public String toString() {
        return name;
    }

    public String getStatus(){
        return name;
    }
}
