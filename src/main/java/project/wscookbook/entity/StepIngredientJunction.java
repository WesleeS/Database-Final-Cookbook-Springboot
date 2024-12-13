package project.wscookbook.entity;

public class StepIngredientJunction {
    private int recipeID;
    private int step;

    private int ingredientID;
    private String name;

    private float quantity;
    private String measurement;

    public int getRecipeID() {
        return recipeID;
    }
    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getStep() {
        return step;
    }
    public void setStep(int step) {
        this.step = step;
    }

    public int getIngredientID() {
        return ingredientID;
    }
    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public float getQuantity() {
        return quantity;
    }
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
