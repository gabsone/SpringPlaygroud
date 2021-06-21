package it.hyaholding.demo.entity;

public enum Sex {


    MALE("MALE"),
    FEMALE("FEMALE");

    public final String value;

    private Sex(String value) {
        this.value = value;
    }

    public boolean isMale(){
        return this.equals(MALE);
    }



}
