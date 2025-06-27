package com.uff.eventsync.application.categories.service;

import com.uff.eventsync.domain.categories.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllSortedByName();
}
