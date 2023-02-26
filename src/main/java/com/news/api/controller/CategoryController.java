package com.news.api.controller;

import com.news.api.converter.MapperClass;
import com.news.api.domain.Category;
import com.news.api.dto.category.CategoryCreateDTO;
import com.news.api.dto.category.CategoryDetailDTO;
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
    private final MapperClass mapperClass;

    @PostMapping
    @ResponseBody
    public ResponseEntity<CategoryDetailDTO> createCategory(@RequestBody @Valid Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperClass.toObject(categoryService.save(category), CategoryDetailDTO.class));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CategoryDetailDTO> updateCategory(@PathVariable Integer id, @RequestBody @Valid CategoryCreateDTO category) {
        return ResponseEntity.ok().body(mapperClass.toObject(categoryService.updateCategory(id, category), CategoryDetailDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CategoryDetailDTO>> getCategories() {
        return ResponseEntity.ok().body(MapperClass.converter(categoryService.allCategories(), CategoryDetailDTO.class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDetailDTO> getCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapperClass.toObject(categoryService.findCategory(id), CategoryDetailDTO.class));
    }
}
