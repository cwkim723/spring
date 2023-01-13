package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // 방법 1
//        MemberService memberService = new MemberServiceImpl();

        // 방법 2
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 방법 3
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // AppConfig에서 @Configuration 설정해준 것을 AnnotationConfigApplicationContext이 JavaConfig 클래스를 읽어들여 IoC와 DI를 적용해줌
        // 설정파일 중에 @Bean이 붙어 있는 메소드들을 AnnotationConfigApplicationContext이 자동으로 실행해 그 결과로 리턴하는 객체들을 기본적으로 싱글턴으로 관리하게 됨
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //                                     이름               타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName()); // new member = memberA
        System.out.println("find Member = " + findMember.getName()); // find Member = memberA
    }
}
