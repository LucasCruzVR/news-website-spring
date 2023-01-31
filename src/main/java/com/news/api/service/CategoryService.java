package com.news.api.service;

import com.news.api.domain.Category;
import com.news.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category save(Category category) {
        category.setName(StringUtils.capitalize(category.getName().toLowerCase()));
        return categoryRepository.save(category);
    }

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(Integer categoryId) {
        Optional<Category> teste = categoryRepository.findById(categoryId);
        return teste.orElseThrow(() -> new ObjectNotFoundException(categoryId, "Not found"));
    }
}
