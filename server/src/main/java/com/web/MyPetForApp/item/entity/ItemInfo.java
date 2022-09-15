package com.web.MyPetForApp.item.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class ItemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemInfoId;

    @Column(nullable = false)
    private String manufacture;

    @Column(nullable = false)
    private String asPhone;

    @Column(nullable = false)
    private String certification;

    @Column(nullable = false)
    private String qas;

    @Column(nullable = false)
    private String displayUnit;

    @Column(nullable = false)
    private String totalCapacity;

    @Column(nullable = false)
    private String unitCapacity;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String size;
}
