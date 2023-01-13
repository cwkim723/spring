package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            /*
                item.getId() == null?
                -> item은 jpa에 저장하기 전까지 id값이 없음.
                    ==> id값이 없다는 것은 완전히 새로 생성하는 객체라는 의미
            */
            em.persist(item);
        } else {
            // 이미 db에 등록된 것을 가져옴
            em.merge(item); // update와 비슷
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
