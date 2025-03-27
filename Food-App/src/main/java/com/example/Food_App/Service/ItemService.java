package com.example.Food_App.Service;

import com.example.Food_App.Model.Item;
import com.example.Food_App.Model.Restaurant;
import com.example.Food_App.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {

    @Autowired
    private RestaurantRepo restaurantRepo;
    public ResponseEntity<Item> addItemToRestaurant(Item item, int id){
        Restaurant r1=restaurantRepo.findById(id).orElse(null);
        if(r1==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        int maxId=r1.getItems().size();
        System.out.println("maxId : "+maxId);
        if(r1==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(r1.getItems()==null)r1.setItems(new ArrayList<>());
        item.setId(maxId+1);
        r1.getItems().add(item);
        restaurantRepo.save(r1);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    public ResponseEntity<Item> editItem(Item item1, int RestaurantId, int ItemId) {
        Restaurant restaurant=restaurantRepo.findById(RestaurantId).orElse(null);
        if(restaurant==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Item item=restaurant.findItemById(ItemId);
        if(item1==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(item1.getName()!=null)
            item.setName(item1.getName());
        if(item1.getCost()!=null)
            item.setCost(item1.getCost());
        if(item1.getDiscount()!=null)
            item.setDiscount(item1.getDiscount());
        restaurantRepo.save(restaurant);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteItem(int RestaurantId,int ItemId) {
        Restaurant restaurant=restaurantRepo.findById(RestaurantId).orElse(null);
        Item item=restaurant.findItemById(ItemId);
        System.out.println("Restaurent : "+ restaurant +" Item : "+item);
        restaurant.getItems().remove(item);
        restaurantRepo.save(restaurant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
