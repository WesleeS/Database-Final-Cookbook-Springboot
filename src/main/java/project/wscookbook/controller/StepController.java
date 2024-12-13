package project.wscookbook.controller;

import project.wscookbook.entity.Step;
import project.wscookbook.entity.StepIngredientJunction;
import project.wscookbook.entity.Ingredient;
import project.wscookbook.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/step/{recipeID}")
public class StepController {

    private final StepRepository stepRepository;

    @Autowired
    public StepController(JdbcTemplate databaseConnection) {
        this.stepRepository = new StepRepository(databaseConnection);
    }

    // Retrieve a specific step by recipeID and stepID
    @GetMapping("/{stepID}")
    public Step getById(@PathVariable("recipeID") String recipeID, @PathVariable("stepID") String stepID) {
        try {
            return this.stepRepository.getStep(recipeID, stepID);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot find step", e);
        }
    }

    // Retrieve all steps for a given recipeID
    @GetMapping("")
    public List<Step> getSteps(@PathVariable("recipeID") String recipeID) {
        try {
            return this.stepRepository.getStepsOfRecipe(recipeID);
        } catch (Exception e) {
            throw new IllegalArgumentException("Recipe not found or invalid number of steps.", e);
        }
    }

    @GetMapping("/{stepID}/ingredient")
    public List<StepIngredientJunction> getIngredients(@PathVariable("recipeID") String recipeID, @PathVariable("stepID") String stepID) {
        try {
            return this.stepRepository.getIngredients(recipeID, stepID);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error querying Ingredients", e);
        }
    }

    // Create a new step
    @PostMapping("")
    public void createStep(@PathVariable("recipeID") int recipeID, @RequestBody() Step step) {
            step.setRecipeID(recipeID);
            this.stepRepository.create(step);
    }

    // Update an existing step
    @PutMapping("/{stepID}")
    public void updateStep(@PathVariable("recipeID") String recipeID, @PathVariable("stepID") String stepID, @RequestBody() Step step) {
            this.stepRepository.update(recipeID, stepID, step);
    }

    // Delete a specific step
    @DeleteMapping("/{stepID}")
    public void deleteStep(@PathVariable("recipeID") String recipeID, @PathVariable("stepID") String stepID) {
        try {
            this.stepRepository.delete(recipeID, stepID);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to delete step", e);
        }
    }
}
