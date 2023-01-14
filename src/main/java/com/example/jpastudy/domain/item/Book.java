package com.example.jpastudy.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
//테이블 안에서 dtype 으로 구별하기 위해서 사용
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;

}
