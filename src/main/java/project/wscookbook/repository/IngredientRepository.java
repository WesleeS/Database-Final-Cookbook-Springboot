package project.wscookbook.repository;

import project.wscookbook.entity.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class IngredientRepository extends BaseRepository<Ingredient>  {

    public IngredientRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public Ingredient get(String id) throws Exception{
        String sql = "SELECT * FROM WSRecipeDatabase.Ingredient WHERE ID = '" + id + "'";
        List<Ingredient> ingredients = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Ingredient.class));
        if (ingredients.size() == 1) {
            return ingredients.get(0);
        }
        throw new Exception();
    }

    @Override
    public List<Ingredient> get() {
        String sql = "SELECT * FROM WSRecipeDatabase.Ingredient;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Ingredient.class));
    }

    @Override
    public void create(Ingredient ingredient) {
    }


    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, Ingredient ingredient) {

    }
    

}

