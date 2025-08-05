package com.harsh.Service;

import com.harsh.Requires.CreateFoodRequest;
import com.harsh.Requires.CreateRestaurantRequest;
import com.harsh.model.Category;
import com.harsh.model.Food;
import com.harsh.model.Restaurant;

import java.util.List;

public interface FoodService
{

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deletedFood(Long foodId) throws Exception;
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian , boolean isNonVegetarian,boolean isSeasioanl, String foodCategory);

    public List<Food> searcFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;


}
