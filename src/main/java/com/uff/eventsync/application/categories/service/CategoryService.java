package com.uff.eventsync.application.categories.service;

import com.uff.eventsync.application.categories.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> findAllSortedByName();
}
