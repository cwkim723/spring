package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate(){
//        MemberRepository bean = ac.getBean(MemberRepository.class);

        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));

        /* 실행 결과:
        org.springframework.beans.factory.NoUniqueBeanDefinitionException:
        No qualifying bean of type 'hello.core.member.MemberRepository' available: expected single matching bean but found 2: memberRepository1,memberRepository2

        원인 => MemberRepository가 memberRepository1인지 memberRepository2인지 모름.
        */
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);

        // 실행 결과: 잘 됨
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        // ctrl shift enter : 줄 넘어가기
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
            // key = memberRepository1 value = hello.core.member.MemoryMemberRepository@ec2cc4
            // key = memberRepository2 value = hello.core.member.MemoryMemberRepository@2a5b3fee
        }
        System.out.println("beansOfType = " + beansOfType);
        // beansOfType = {memberRepository1=hello.core.member.MemoryMemberRepository@ec2cc4, memberRepository2=hello.core.member.MemoryMemberRepository@2a5b3fee}

        assertThat(beansOfType.size()).isEqualTo(2);

    }



    @Configuration
    static class SameBeanConfig {
        // class 안에다가 class 작성 -> 여기서만 쓰겠다

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }


}
