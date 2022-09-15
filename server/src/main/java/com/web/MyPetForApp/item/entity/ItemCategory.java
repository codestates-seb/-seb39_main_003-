package com.web.MyPetForApp.item.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCategoryId;

    @Column(nullable = false, unique = true)
    private String itemCategory;

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items = new ArrayList<>();

}
