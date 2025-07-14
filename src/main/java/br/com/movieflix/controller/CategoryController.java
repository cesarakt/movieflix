package br.com.movieflix.controller;

import br.com.movieflix.entity.Category;
import br.com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor //Utilizado para injeção de dependências
public class CategoryController {

    private final CategoryService categoryService; //Injeção de dependência via @RequiredArgsConstructor

    @GetMapping()
    public List<Category> getAllCategories () {
        return categoryService.findAll();
    }

    @PostMapping()
    public Category saveCategory (@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category getByCategoryId (@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        return optionalCategory.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId (@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
