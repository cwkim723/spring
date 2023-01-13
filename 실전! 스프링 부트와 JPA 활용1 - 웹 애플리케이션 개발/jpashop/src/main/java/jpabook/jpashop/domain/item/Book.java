package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 엔티티를 저장할 때 구분컬럼에 입력할 값을 지정
@Getter
@Setter
public class Book extends Item{

    private String author;
    private String isbn;

}
