package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class); // bean 조회
        StatefulService statefulService2 = ac.getBean(StatefulService.class); // bean 조회

        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadA: B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);


        /*
            < 상황 >
            A가 주문하고 주문 금액 조회하는 사이에 B사용자가 주문을 함

            < test 결과 >
            name = userA price = 10000
            name = userB price = 20000
            price = 10000
         */

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}