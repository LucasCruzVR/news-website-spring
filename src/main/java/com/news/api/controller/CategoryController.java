package com.news.api.controller;

import com.news.api.domain.Category;
import com.news.api.service.CategoryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(categoryService.allCategories());
    }

    @GetMapping(value = "/{category_id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer category_id) {
        return ResponseEntity.ok().body(categoryService.findCategory(category_id));
    }
}
