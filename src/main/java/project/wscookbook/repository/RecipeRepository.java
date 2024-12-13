package project.wscookbook.repository;

import project.wscookbook.entity.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;


public class RecipeRepository extends BaseRepository<Recipe> {

    public RecipeRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public Recipe get(String id) throws Exception{
        String sql = "SELECT * FROM WSRecipeDatabase.Recipe WHERE ID = '" + id + "'";
        List<Recipe> recipes = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Recipe.class));
        if (recipes.size() == 1) {
            return recipes.get(0);
        }
        throw new Exception();
    }

    @Override
    public List<Recipe> get() {
        String sql = "SELECT * FROM WSRecipeDatabase.Recipe;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Recipe.class));
    }

    @Override
    public void create(Recipe recipe) {
        if (recipe.getName() == "'POISON'") {
            
        } else {
            String sql = "INSERT INTO WSRecipeDatabase.Recipe (Name, CookbookTitle, CookingTime, VariationOf, Difficulty) VALUES ('" + recipe.getName() + "','" + recipe.getCookbookTitle() + "'," + recipe.getCookingTime() + ", NULL,'" + recipe.getDifficulty() + "');";
        this.getDatabaseConnection().execute(sql);
        }
    }


    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, Recipe recipe) {

    }
    

}
