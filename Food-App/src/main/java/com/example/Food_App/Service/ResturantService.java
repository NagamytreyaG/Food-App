package com.example.Food_App.Service;
import com.example.Food_App.Model.Restaurant;
import com.example.Food_App.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResturantService {
    @Autowired
    RestaurantRepo repo;
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> restaurants=repo.findAll();
        System.out.println(restaurants);
        return new ResponseEntity<>(restaurants,HttpStatus.OK);
    }

    public ResponseEntity<Restaurant> getRestaurantById(int Id) {
        Restaurant restaurant =repo.findById(Id).orElse(null);
        if(restaurant !=null)
            return new ResponseEntity<>(restaurant,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Restaurant> addRestaurant(Restaurant restaurant) {
        Integer maxId=repo.findTopByOrderByIdDesc().map(Restaurant::getId).orElse(0);
        restaurant.setId(maxId+1);
        return new ResponseEntity<>(repo.save(restaurant),HttpStatus.OK);
    }

    public ResponseEntity<Restaurant> patchRestaurant(Restaurant restaurant, int id) {
        Restaurant r1=repo.findById(id).orElse(null);
        if(r1==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(restaurant.getName()!=null){
            r1.setName(restaurant.getName());
        }

        if(restaurant.getLocation()!=null){
            r1.setLocation(restaurant.getLocation());
        }
        return new ResponseEntity<>(repo.save(r1),HttpStatus.OK);
    }

    public ResponseEntity<List<Restaurant>> searchRestaurants(String searchKeyword) {
        List<Restaurant> restaurants=repo.searchRestaurants(searchKeyword);
        if(restaurants!=null && restaurants.size()>0)
            return new ResponseEntity<>(restaurants,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteRestaurant(int Id) {
        repo.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<List<Restaurant>> addRestaurantList(List<Restaurant> restaurants) {
        for(Restaurant r:restaurants){
            Integer maxId=repo.findTopByOrderByIdDesc().map(Restaurant::getId).orElse(0);
            r.setId(maxId+1);
            r.setItems(r.getItems());
            repo.save(r);
        }
        return new ResponseEntity<>(restaurants,HttpStatus.OK);
    }
}


