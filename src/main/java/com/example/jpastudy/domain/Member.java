package com.example.jpastudy.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //1 : N 관계 => member 를 기준으로 봤을 때 order 는 여러 개 가능하기 때문에
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}

