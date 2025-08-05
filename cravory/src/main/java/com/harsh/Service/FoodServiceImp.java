package com.harsh.Service;

import com.harsh.Requires.CreateFoodRequest;
import com.harsh.model.Category;
import com.harsh.model.Food;
import com.harsh.model.Restaurant;
import com.harsh.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;


    }

    @Override
    public void deletedFood(Long foodId) throws Exception {


        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonVegetarian, boolean isSeasioanl, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if(isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if(isNonVegetarian){
            foods = filterByNonVegetarian(foods, isNonVegetarian);
            
        }
        if(isSeasioanl){
            foods = filterByisSeasioanl(foods, isSeasioanl);
        }


        if(foodCategory != null && !foodCategory.equals("")){
            foods =  filterByCategory(foods, foodCategory);
        }
        return List.of();
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {

        return foods.stream().filter(
                food -> {
                    if (food.getFoodCategory() != null){
                        return food.getFoodCategory().getName().equals(foodCategory);

                    }
                    return false;

                }


        ).collect(Collectors.toList());

    }

    private List<Food> filterByisSeasioanl(List<Food> foods, boolean isSeasioanl) {
        return foods.stream().filter(food -> food.isVegetarian() == isSeasioanl).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());

    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searcFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {

        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isEmpty()){
            throw new Exception("food not exist....");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);

    }
}
