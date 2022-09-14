package com.web.MyPetForApp.item.entity;

import javax.persistence.*;

@Entity
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCategoryId;

    @Column
    private String itemCategory;
}
