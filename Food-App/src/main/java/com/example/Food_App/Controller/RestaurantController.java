package com.example.Food_App.Controller;
import com.example.Food_App.Model.Restaurant;
import com.example.Food_App.Service.ResturantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class RestaurantController {
    @Autowired
    private ResturantService service;
    @GetMapping("/Restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        return service.getRestaurants();
    }

    @GetMapping("/Restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id){
        return service.getRestaurantById(id);
    }

    @GetMapping("/Restaurants/Search/{searchKeyword}")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@PathVariable String searchKeyword){
        return service.searchRestaurants(searchKeyword);
    }


    @PostMapping("/Restaurants")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant){
        return service.addRestaurant(restaurant);
    }
    @PostMapping("/RestaurantsList")
    public ResponseEntity<List<Restaurant>> addRestaurantList(@RequestBody List<Restaurant>restaurants){
        return service.addRestaurantList(restaurants);
    }

    @PutMapping("/Restaurants/{id}")
    public ResponseEntity<Restaurant> patchRestaurant(@RequestBody Restaurant restaurant,@PathVariable int id){
        return service.patchRestaurant(restaurant,id);
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable int id){
        return service.deleteRestaurant(id);
    }
}
