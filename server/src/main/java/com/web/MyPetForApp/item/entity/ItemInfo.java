package com.web.MyPetForApp.item.entity;

import javax.persistence.*;

@Entity
public class ItemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemInfoId;

    @Column
    private String manufacture;

    @Column
    private String asPhone;

    @Column
    private String certification;

    @Column
    private String qas;

    @Column
    private String displayUnit;

    @Column
    private String totalCapacity;

    @Column
    private String unitCapacity;

    @Column
    private String color;

    @Column
    private String size;
}
