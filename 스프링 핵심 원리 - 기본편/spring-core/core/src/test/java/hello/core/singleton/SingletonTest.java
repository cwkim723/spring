package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);
        // memberService1 : hello.core.member.MemberServiceImpl@bccb269
        // memberService2 : hello.core.member.MemberServiceImpl@609cd4d8
        // 다른 객체 값을 가져오는 것을 확인할 수 있음 -> 효율 떨어짐

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
        // 통과함 -> true
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        // singletonService1 = hello.core.singleton.SingletonService@63475ace
        // singletonService2 = hello.core.singleton.SingletonService@63475ace
        // 같은 인스턴스 반환

        assertThat(singletonService1).isSameAs(singletonService2);
        // same: == (객체 인스턴스가 같은지 비교)
        // equal: 자바 equals
        // 테스트 통과 -> true
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);
        // memberService1 : hello.core.member.MemberServiceImpl@61ce23ac
        // memberService2 : hello.core.member.MemberServiceImpl@61ce23ac
        // 참조값이 같다

        assertThat(memberService1).isSameAs(memberService2);
        // 통과함 -> true
    }
}
