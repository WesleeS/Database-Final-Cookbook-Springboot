package project.wscookbook.entity;

public class Step {
    private int recipeID;
    private int step;
    private String directions;

    public int getRecipeID() {
        return this.recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getDirections() {
        return this.directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
