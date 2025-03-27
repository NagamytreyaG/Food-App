package com.example.Food_App.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private int id;
    private String name;
    private Integer cost;
    private Integer discount;
    private String itemType;
}
