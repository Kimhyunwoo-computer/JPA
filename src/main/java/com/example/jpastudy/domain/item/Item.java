package com.example.jpastudy.domain.item;

import com.example.jpastudy.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//상속관계 Mapping 하는 방법
@DiscriminatorColumn(name = "dtype")
//한 테이블에 모두 저장하고 dytpe 으로 구분한다 .
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stackQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
