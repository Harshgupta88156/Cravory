package com.harsh.Service;

import com.harsh.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImp implements  CategoryService{
    @Override
    public Category createCategory(String name, Long userrId) {
        return null;
    }

    @Override
    public List<Category> findCategoryByRestaurant(Long id) throws Exception {
        return List.of();
    }

    @Override
    public Category findCategroyById(Long id) throws Exception {
        return null;
    }
}
