package com.example.Food_App.Repository;

import com.example.Food_App.Model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepo extends MongoRepository<Restaurant, Integer> {
    @Query("{'$or': [ " +
            "{'name': { $regex: ?0, $options: 'i' }}, " +
            "{'location': { $regex: ?0, $options: 'i' }}, " +
         "{'cuisines': { $regex: ?0, $options: 'i' }}"+
         "{'items.name': { $regex: ?0, $options: 'i' }}" +
         "{'items.itemType': { $regex: ?0, $options: 'i' }}" +
            "]}")
    List<Restaurant> searchRestaurants(String keyword);

    Optional<Restaurant>findTopByOrderByIdDesc();
}


//
