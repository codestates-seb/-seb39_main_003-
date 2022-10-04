package com.web.MyPetForApp.item.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name = "ITEM_SEQ_GEN",
        sequenceName = "Item_SQE",
        initialValue = 1,
        allocationSize = 1
)
public class ItemSeq {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "ITEM_SEQ_GEN")
    public int itemSeq;
}
