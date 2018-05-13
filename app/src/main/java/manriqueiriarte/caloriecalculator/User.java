package manriqueiriarte.caloriecalculator;

public class User {
    private String name;
    private int dailyCaloricIntake;
    private double bmi;
    private int weight;

    public User(String name, int weight, int dailyCaloricIntake, double bmi){
        this.name = name;
        this.weight = weight;
        this.dailyCaloricIntake = dailyCaloricIntake;
        this.bmi = bmi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDailyCaloricIntake() {
        return dailyCaloricIntake;
    }

    public void setDailyCaloricIntake(int dailyCaloricIntake) {
        this.dailyCaloricIntake = dailyCaloricIntake;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    //We have to override toString method to be able to get DailyCaloricIntake for graphing in the CaloriesGraph class
    @Override
    public String toString() {
       return dailyCaloricIntake + "";
    }
}
