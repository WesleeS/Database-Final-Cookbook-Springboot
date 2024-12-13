package project.wscookbook.entity;

public class Ingredient {
    private int id;
    private String name;
    private int calories;
    private int shelfLife;

    private String subclass;
    private String dairyAnimal;
    private String grainType;
    private String vegetableType;
    private String fruitType;
    private String meatAnimal;

    private byte isVegan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getDairyAnimal() {
        return dairyAnimal;
    }

    public void setDairyAnimal(String dairyAnimal) {
        this.dairyAnimal = dairyAnimal;
    }

    public String getGrainType() {
        return grainType;
    }

    public void setGrainType(String grainType) {
        this.grainType = grainType;
    }

    public String getVegetableType() {
        return vegetableType;
    }

    public void setVegetableType(String vegetableType) {
        this.vegetableType = vegetableType;
    }

    public String getFruitType() {
        return fruitType;
    }

    public void setFruitType(String fruitType) {
        this.fruitType = fruitType;
    }

    public String getMeatAnimal() {
        return meatAnimal;
    }

    public void setMeatAnimal(String meatAnimal) {
        this.meatAnimal = meatAnimal;
    }

    public byte getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(byte isVegan) {
        this.isVegan = isVegan;
    }
}
