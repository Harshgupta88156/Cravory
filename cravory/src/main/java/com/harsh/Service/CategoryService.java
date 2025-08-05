package com.harsh.Service;

import com.harsh.model.Category;

import java.util.List;

public interface CategoryService()

{
    public Category createCategory(String name, Long userrId);

    public List<Category> findCategoryByRestaurant(Long id) throws Exception;

    public Category findCategroyById(Long id) throws Exception;




}
