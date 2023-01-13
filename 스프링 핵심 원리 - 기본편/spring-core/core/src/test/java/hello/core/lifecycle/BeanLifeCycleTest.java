package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // ApplicationContext는 close() 지원 X, 그래서 ApplicationContext를 상속받는 ConfigurableApplicationContext를 대신 쓴다.

        /*
            생성자 호출, url = null
            connect : null
            call : null message = 초기화 연결 메시지
        */

        /*
            생성자 호출, url = null
            NetworkClient.afterPropertiesSet
            connect : http://hello-spring.dev
            call : http://hello-spring.dev message = 초기화 연결 메시지
            00:42:04.191 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@6253c26, started on Thu Mar 03 00:42:03 KST 2022
            NetworkClient.destroy
            close : http://hello-spring.dev
        */
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
