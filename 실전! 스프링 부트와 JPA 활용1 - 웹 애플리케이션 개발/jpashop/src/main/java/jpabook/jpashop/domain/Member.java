package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    // @GeneratedValue: db에서 만든 값을 그대로 쓰겠다.

    @NotEmpty
    private String name;
    /*
        [ 문제점 ]
        1. 화면에 대한 검증을 Member 엔티티에 함께 하고 있다.
        ex. 어떤 api는 @NotEmpty가 필요하고 어떤 api는 @NotEmpty가 필요하지 않을 수도 있다.

        2. name을 username으로 바꾼 경우
        api가 오류가 날 수 있다.

        => 엔티티를 수정해서 api 스펙 자체가 변하는 것이 문제가 된다.
            엔티티는 수정 가능성이 높음.
            ===> api 스펙을 위한 별도의 dto를 만들어야 한다!
    */

    @Embedded // 내장 타입
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
