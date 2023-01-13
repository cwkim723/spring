package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        // basePackages를 지정하지 않음 경우 -> AutoAppConfig가 속해 있는 hello.core부터 시작해서 하위 패키지까지 다 검사
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 스프링으로 등록시키고 싶지 않은 파일을 설정해줌. Configuration은 수동으로 등록시켜 주는데 여기서 또 등록시켜주면 충돌이
        // 나기 때문에 제외시켜줌
)
public class AutoAppConfig {

    /* @ComponentScan
        @Component가 붙은 클래스를 찾아서 자동으로 스프링 빈으로 등록시켜줌
    */


    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
