package project.wscookbook.controller;

import project.wscookbook.entity.Ingredient;
import project.wscookbook.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(JdbcTemplate databaseConnection) {
        this.ingredientRepository = new IngredientRepository(databaseConnection);
    }

    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable("id") String id) throws Exception {
        try {
            return this.ingredientRepository.get(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ingredient not found, or returned multiple", e);
        }
    }

    @GetMapping("")
    public List<Ingredient> getCookbooks(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        List<Ingredient> ingredients = this.ingredientRepository.get();

        return ingredients;
    }
}
