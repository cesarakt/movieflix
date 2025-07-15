package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
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
    public List<CategoryResponse> getAllCategories () {
        List<Category> categoryList = categoryService.findAll();
        return categoryList.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @PostMapping()
    public CategoryResponse saveCategory (@RequestBody CategoryRequest categoryRequest) {
        Category newCategory = CategoryMapper.toCategory(categoryRequest);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponse getByCategoryId (@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            return CategoryMapper.toCategoryResponse(optionalCategory.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId (@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
