package com.uff.eventsync.application.categories.mapper;

import com.uff.eventsync.application.categories.dto.CategoryResponseDTO;
import com.uff.eventsync.domain.categories.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryResponseDTO(category.getId(), category.getName());
    }

    public static List<CategoryResponseDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
