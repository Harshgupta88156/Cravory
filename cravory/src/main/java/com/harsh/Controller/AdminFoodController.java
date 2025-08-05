package com.harsh.Controller;


import com.harsh.Requires.CreateFoodRequest;
import com.harsh.Response.MessageResponse;
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



@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant  = restaurantService.getRestaurantByUserId(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        foodService.deletedFood(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("food deleted Successfully!!!");

        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);


        Food food = foodService.updateAvailabilityStatus(id);

        foodService.deletedFood(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }






}





