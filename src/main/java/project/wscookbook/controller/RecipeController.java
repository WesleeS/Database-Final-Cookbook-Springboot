package project.wscookbook.controller;

import project.wscookbook.entity.Recipe;
import project.wscookbook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(JdbcTemplate databaseConnection) {
        this.recipeRepository = new RecipeRepository(databaseConnection);
    }

    @GetMapping("/")
    public List<Recipe> getAll() {
        try {
            return recipeRepository.get();
        } catch (Exception e) {
            throw new IllegalArgumentException("No Recipes Found", e);
        }
        
    }


// This is the table/attribute url
    @GetMapping("/{id}")
    public Recipe getById(@PathVariable("id") String id) {
        try {
            return this.recipeRepository.get(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Recipe not found or invalid number of recipes.", e);
        }
    }
// This is the ?attribute=thing url
    @GetMapping("")
    public List<Recipe> getRecipes(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        List<Recipe> recipes = this.recipeRepository.get();

        return recipes;
    }

}
