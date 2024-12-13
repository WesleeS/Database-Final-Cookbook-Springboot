package project.wscookbook.entity;

public class Recipe {
    private int id;
    private String name;
    private String cookbookTitle;
    private int cookingTime;
    private String difficulty;

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCookbookTitle(){
        return this.cookbookTitle;
    }

    public void setCookbookTitle(String cookbookTitle){
        this.cookbookTitle = cookbookTitle;
    }

    public int getCookingTime(){
        return this.cookingTime;
    }

    public void setCookingTime(int cookingTime){
        this.cookingTime = cookingTime;
    }


    public String getDifficulty(){
        return this.difficulty;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }



}
