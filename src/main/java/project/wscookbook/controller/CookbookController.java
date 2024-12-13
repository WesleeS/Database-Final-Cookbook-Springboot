package project.wscookbook.controller;

import project.wscookbook.entity.Cookbook;
import project.wscookbook.repository.CookbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cookbook")
public class CookbookController {

    private final CookbookRepository cookbookRepository;

    @Autowired
    public CookbookController(JdbcTemplate databaseConnection) {
        this.cookbookRepository = new CookbookRepository(databaseConnection);
    }

    @GetMapping("/{id}")
    public Cookbook getById(@PathVariable("id") String id) throws Exception {
        try {
            return this.cookbookRepository.get(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cookbook not found or invalid number of recipes.", e);
        }
    }

    @GetMapping("")
    public List<Cookbook> getCookbooks(@RequestParam(value = "title", required = false, defaultValue = "") String title) {
        List<Cookbook> cookbooks = this.cookbookRepository.get();

        return cookbooks;
    }
}
