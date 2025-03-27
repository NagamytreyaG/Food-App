package com.example.Food_App.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
//hello world
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Restaurant")
public class Restaurant {
    @Id
    private int id;
    @TextIndexed
    private String name;
    @TextIndexed
    private String location;
    private List<Item> items;
    @TextIndexed
    private Double rating;
    @TextIndexed
    private List<String> cuisines;
    private String address;
    private String phoneNumber;
    public Item findItemById(int id){
        for(Item i:items){
            if(i.getId()==id)return i;
        }
        return null;
    }
}
