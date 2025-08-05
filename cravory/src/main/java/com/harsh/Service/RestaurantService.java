package com.harsh.Service;

import com.harsh.Requires.CreateRestaurantRequest;
import com.harsh.dto.RestaurantDto;
import com.harsh.model.Restaurant;
import com.harsh.model.User;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;


    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findrestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavourite(Long restaurantId,User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
