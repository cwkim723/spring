package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // readOnly 안됨
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        // findItem -> 영속상태

//        findItem.change(price, name, stockQuantity); // 이렇게 메서드로 바꿔야지 set을 통해 바꾸는 것도 좋지는 않음
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        // @Transactional에 의해 commit됨 -> commit이 되면 jpa는 flush()를 날림
        // flush() : 영속성 컨텍스트에서 변경된 애가 누군지 다 찾음
        // set으로 설정한 값들을 db에 update시킴

        // 데이터가 많을 경우 dto(ex. UpdateItemDto)를 생성해서 파라미터를 UpdateItemDto로 받은 다음
        // findItem에 UpdateItemDto에서 뽑아온 값들을 넣어준다.
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
