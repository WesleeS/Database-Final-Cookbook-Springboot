package project.wscookbook.repository;

import project.wscookbook.entity.Cookbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;


public class CookbookRepository extends BaseRepository<Cookbook> {

    public CookbookRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public Cookbook get(String id) throws Exception{
        String sql = "SELECT * FROM WSRecipeDatabase.Cookbook WHERE Title = '" + id + "'";
        List<Cookbook> cookbooks = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Cookbook.class));
        if (cookbooks.size() == 1) {
            return cookbooks.get(0);
        }
        throw new Exception();
    }

    public Integer getNumberOfRecipes(String id){
        String sql = "Select * FROM WSRecipeDatabase.CookbookWithRecipeCount WHERE Title = '" + id + "'";
        return this.getDatabaseConnection().queryForObject(sql, Integer.class);
    }

    @Override
    public List<Cookbook> get() {
        String sql = "SELECT * FROM WSRecipeDatabase.Cookbook;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Cookbook.class));
    }

    @Override
    public void create(Cookbook cookbook) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, Cookbook cookbook) {

    }
    

}
