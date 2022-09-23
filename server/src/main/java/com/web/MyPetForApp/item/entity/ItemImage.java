package com.web.MyPetForApp.item.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemImageId;

    @Column
    private String itemThumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public void changeItem(Item item){
        if(this.item != null){
            this.item.getItemImages().remove(this);
        }
        this.item = item;
        if(!item.getItemImages().contains(this)){
            item.addItemImage(this);
        }
    }
}
