package com.example.jpastudy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter

//내장 type Embeddable 을 이용해서 기본 값을 모아서 복합 값을 만들어 낸다 .
//Embeddable 생성자 부분에서 사용 & Embedded 사용하는 부분에서 사용

public class Address {

    private String city;
    private String street;
    private String zipcode;

}
