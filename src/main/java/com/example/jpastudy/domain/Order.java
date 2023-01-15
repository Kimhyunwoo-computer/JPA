package com.example.jpastudy.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order  {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    //N : 1 관계 => order 여러 개가 member 하나를 기준으로 하기 때문에
    //양방향 관계를 가진다 .
    @JoinColumn(name = "member_id")
    //연관관계 주인
    private Member member;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    //cascade 모든 Entity 는 저장하려고 할때 persist 를 따로 해줘야한다. 그런 일을 없애기 위해 cascade 사용
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    //연관관계 주인
    //1 : 1 관계 에서 PK 를 잡을때 access 가 자주 있는 곳에서 연관관계 주인을 잡아준다.
    private Delivery delivery;

    private LocalDateTime orderData; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 (ORDER , CANCEL)

    //==연관관계 메소드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
