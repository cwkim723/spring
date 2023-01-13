package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        // memberService = hello.core.member.MemberServiceImpl@1c3b9394
        System.out.println("memberService.getClass() = " + memberService.getClass());
        // memberService.getClass() = class hello.core.member.MemberServiceImpl

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 맞으면 성공, 아니면 실패 ==> 성공
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 맞으면 성공, 아니면 실패 ==> 성공
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 맞으면 성공, 아니면 실패 ==> 성공
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
//        ac.getBean("xxxxx", MemberService.class);

//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxxx' available 실패 에러 뜸

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
        //                                                        이 로직을 실행하면
        //                  이 예외가 터져야 함
        // ==> 예외가 터짐(성공)
    }

}
