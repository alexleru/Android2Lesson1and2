package ru.alexey.weather;

public class Singleton {
    private static Singleton instance;
    private boolean[] addData = new boolean[3];
    private Singleton(){
    }

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public boolean[] getAddData() {
        return addData;
    }

    public void setAddData(int index, boolean value) {
        this.addData[index] = value;
    }
}
