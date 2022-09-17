package com.web.MyPetForApp.item.entity;

import com.web.MyPetForApp.board.entity.Board;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCategoryId;

    @Column(nullable = false, unique = true)
    private String itemCategory;

    @Builder.Default
    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items = new ArrayList<>();

    // ItemCategory-Item 양방향 연관관계 편의 메서드
    public void addItem(Item item){
        this.items.add(item);
        if(item.getItemCategory() != this){
            item.setItemCategory(this);
        }
    }
}
