package com.news.api.service;

import com.news.api.domain.Category;
import com.news.api.model.CategoryDTO;
import com.news.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Category save(Category category) {
        category.setName(StringUtils.capitalize(category.getName().toLowerCase()));
        return categoryRepository.save(category);
    }

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElseThrow(() -> new ObjectNotFoundException(categoryId, "Not found"));
    }

    @Transactional
    public Category update(Integer id, CategoryDTO newcategory) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        if (!newcategory.getName().isBlank()) {
            newcategory.setName(StringUtils.capitalize(newcategory.getName().toLowerCase()));
        }
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Not found"));
        modelMapper.map(newcategory, category);
        return categoryRepository.save(category);
    }
}
