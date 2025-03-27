package com.example.Food_App.Controller;

import com.example.Food_App.Model.Item;
import com.example.Food_App.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    @Autowired
    ItemService service;
    @PostMapping("/addItem/{id}")
    public ResponseEntity<Item> addItemToRestaurant(@RequestBody Item item, @PathVariable int id){

        return service.addItemToRestaurant(item,id);
    }
    @PutMapping("/editItem/{Rid}/{Iid}")
    public ResponseEntity<Item> editItem(@RequestBody Item item,@PathVariable int Rid,@PathVariable int Iid){
        return service.editItem(item,Rid,Iid);
    }
    @DeleteMapping("/deleteItem/{Rid}/{Iid}")
    public ResponseEntity<?> deleteItem(@PathVariable int Rid,@PathVariable int Iid){
        return service.deleteItem(Rid,Iid);
    }

}
