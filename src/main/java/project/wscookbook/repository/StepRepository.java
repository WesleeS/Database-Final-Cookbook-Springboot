package project.wscookbook.repository;

import project.wscookbook.entity.Step;
import project.wscookbook.entity.StepIngredientJunction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class StepRepository{
    
    private final JdbcTemplate databaseConnection;
    public StepRepository(JdbcTemplate databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public List<Step> getStepsOfRecipe(String recipeID){

        String sql = "SELECT * FROM WSRecipeDatabase.Step WHERE RecipeID = '" + recipeID + "'";
        List<Step> steps = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Step.class));
        try {
            return steps;
        } catch (Exception e) {
            throw new IllegalArgumentException("No Steps Found", e);
        }

    }

    public Step getStep(String recipeID, String step) throws Exception{
        String sql = "SELECT * FROM WSRecipeDatabase.Step WHERE RecipeID = '" + recipeID + "' AND Step = '" + step + "'";
        List<Step> stepReal = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Step.class));
        if (stepReal.size() == 1) {
            return stepReal.get(0);
        }
        throw new Exception();
    }

    public List<StepIngredientJunction> getIngredients(String recipeID, String step){

        String sql = "SELECT i.Name AS name, " + 
             "si.Quantity, " + 
             "s.RecipeID, " +
             "s.Step, " +  
             "i.ID AS IngredientID, " + 
             "si.Measurement " + 
             "FROM WSRecipeDatabase.Step s " + 
             "JOIN WSRecipeDatabase.Step_to_Ingredient si ON s.RecipeID = si.RecipeID AND s.Step = si.Step " + 
             "JOIN WSRecipeDatabase.Ingredient i ON si.IngredientID = i.ID " + 
             "WHERE s.RecipeID = " + recipeID + " AND s.Step = " + step;
        List<StepIngredientJunction> ingredients = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(StepIngredientJunction.class));
        try {
            return ingredients;
        } catch (Exception e) {
            throw new IllegalArgumentException("No Ingredients Found", e);
        }

    }

    public void create(Step step) {
        String sql = "INSERT INTO WSRecipeDatabase.Step VALUES (" + step.getRecipeID() + "," + step.getStep() + ",'" + step.getDirections() + "');";
        this.getDatabaseConnection().execute(sql);
    }

    public void delete(String recipeID, String stepID){
        String sql = "DELETE FROM WSRecipeDatabase.Step WHERE RecipeID = " + recipeID + " AND Step = " + stepID;
        this.getDatabaseConnection().execute(sql);
    }   

    public void update(String recipeID, String stepID, Step step) {
        String sql = "UPDATE WSRecipeDatabase.Step SET Directions = '" + step.getDirections() + "', Step = '" + step.getStep() + "' WHERE RecipeID = " + recipeID + " AND Step = " + stepID + ";";
        this.getDatabaseConnection().execute(sql);
    }
    

    public JdbcTemplate getDatabaseConnection(){
        return this.databaseConnection;
    }

}
