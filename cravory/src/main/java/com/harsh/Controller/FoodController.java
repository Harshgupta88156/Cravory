package com.harsh.Controller;


import com.harsh.Requires.CreateFoodRequest;
import com.harsh.Service.FoodService;
import com.harsh.Service.RestaurantService;
import com.harsh.Service.UserService;
import com.harsh.model.Food;
import com.harsh.model.Restaurant;
import com.harsh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {



    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searcFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }




    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable long restaurantId ,
                                                        @RequestParam boolean vegetarian,
                                                        @RequestParam boolean isNonVegetarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam(required = false) String food_category,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId, vegetarian,isNonVegetarian,seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.Ok);
    }
}
