package com.bairontapia.projects.cuidamed.mappings.allergytype;

public enum AllergyType {
    DRUG_ALLERGY("medicamentos"),
    FOOD_ALLERGY("comidas"),
    INSECT_ALLERGY("insectos"),
    LATEX_ALLERGY("latex"),
    MOLD_ALLERGY("moho"),
    PET_ALLERGY("mascotas"),
    POLLEN_ALLERGY("polen");

    private static final AllergyType[] VALUES = values();

    private final String name;

    AllergyType(String name){
        this.name = name;
    }

    public static AllergyType[] getValues() {
        return VALUES;
    }

    public static AllergyType getValueFromIndex(final int index){
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
