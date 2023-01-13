package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    /*
        [ InheritanceType ]
        SINGLE_TABLE: 한 테이블에 다 때려박음 => 이거 선택할 것
        TABLE_PER_CLASS: BOOK, MOVIE, ALBUM 이렇게 나뉘어져 있음
        JOINED
    */

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity; // 재고 수량
    // 이 수량을 변경할 일이 있으면 핵심 비즈니스 로직을 통해 변경해야 한다!!!!, setter를 가지고 변경하는 것이 아님!!!

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
       int restStock = this.stockQuantity - quantity;
       if(restStock < 0){
           throw new NotEnoughStockException("need more stock");
       }
       this.stockQuantity = restStock;
    }

}
