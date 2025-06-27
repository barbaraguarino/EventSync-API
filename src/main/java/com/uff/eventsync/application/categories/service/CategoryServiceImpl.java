package com.uff.eventsync.application.categories.service;

import com.uff.eventsync.domain.categories.entity.Category;
import com.uff.eventsync.domain.categories.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllSortedByName() {
        return categoryRepository.findAllByOrderByNameAsc();
    }
}
