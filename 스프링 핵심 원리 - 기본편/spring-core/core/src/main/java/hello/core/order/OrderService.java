package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // 주문 서비스는 할인 결과를 포함한 주문 결과를 반환
}
