package com.harsh.Service;

import com.harsh.Requires.CreateRestaurantRequest;
import com.harsh.dto.RestaurantDto;
import com.harsh.model.Address;
import com.harsh.model.Restaurant;
import com.harsh.model.User;
import com.harsh.repository.AddressRepository;
import com.harsh.repository.RestaurantRepository;
import com.harsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImp implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private UserRepository userRepository;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();



        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setAddress(address);
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setName(req.getName());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return  restaurantRepository.save(restaurant);




    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant = findrestaurantById(restaurantId);
        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }

        if(restaurant.getName() != null){
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

//        Restaurant restaurant = findrestaurantById(restaurantId);
        restaurantRepository.deleteById(restaurantId);


    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findrestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            throw new Exception("restaurant not found");
        }
        return restaurant.get();

    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant == null){
            throw new Exception("No such Restaurant find with id - " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourite(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findrestaurantById(restaurantId);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurantId);


//
//        if(user.getFavourites().contains(restaurantDto)){
//            user.getFavourites().remove(restaurantDto);
//        }
//        else user.getFavourites().add(restaurantDto);

        boolean isFavourited = false;
        List<RestaurantDto> favourites = user.getFavourites();
        for(RestaurantDto favourite: favourites){
            if(favourite.getId().equals(restaurantId)){
                isFavourited = true;
                break;
            }
        }


        if(isFavourited){
            favourites.removeIf(favourite -> favourite.getId().equals(restaurantId));
        }
        else{
            favourites.add(restaurantDto);
        }
        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findrestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
