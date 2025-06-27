package com.uff.eventsync.presentation.categories;

import com.uff.eventsync.application.categories.dto.CategoryResponseDTO;
import com.uff.eventsync.application.categories.mapper.CategoryMapper;
import com.uff.eventsync.application.categories.service.CategoryService;
import com.uff.eventsync.domain.categories.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<Category> categories = categoryService.findAllSortedByName();
        List<CategoryResponseDTO> responseDTOs = CategoryMapper.toDTOList(categories);
        return ResponseEntity.ok(responseDTOs);
    }
}
