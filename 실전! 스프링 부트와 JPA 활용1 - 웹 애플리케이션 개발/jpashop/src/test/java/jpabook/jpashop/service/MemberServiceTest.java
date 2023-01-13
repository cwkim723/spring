package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // jUnit 실행할 때 스프링이랑 같이 엮어서 실행하고 싶을 때
@SpringBootTest // spring boot를 띄운 상태에서 test를 하고 싶을 때 -> 이거 없으면 @Autowired 사용 불가
@Transactional
// 같은 transaction 안에서 pk값이 같으면 같은 영속성 컨텍스트에서 똑같은 애가 관리(1개로만 관리)
// 기본적으로 rollback함
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(value = false) // -> 이걸 해야 db에 insert됨, 안 하면 바로 롤백됨
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member); // 영속성 컨텍스트에 member반영
        /*
            insert
            into
                user
                (city, street, zipcode, name, member_id)
            values
                (?, ?, ?, ?, ?)
        */

        // then
        em.flush(); // 영속성 컨텍스트에 있는 것을 db에 반영 -> insert문 볼 수 있음, 대신 db는 롤백됨
        // flush(): 강제로 영속성 컨텍스트에 있는 쿼리를 db에 날림
        // 2022-03-10 14:27:06.108  INFO 39340 --- [           main] p6spy                                    : #1646890026108 | took 3ms | rollback | connection 3| url jdbc:h2:tcp://localhost/~/jpashop

        assertEquals(member, memberRepository.findOne(savedId)); // test통과
   }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2); // 예외가 발생해야 함! -> 중복은 안되기 때문에!
//            // java.lang.IllegalStateException: 이미 존재하는 회원입니다.
//        } catch (IllegalStateException e){
//            return; // test 성공
//        }

        memberService.join(member2); // 예외가 발생해야 함! -> 중복은 안되기 때문에!
        // @Test(expected = IllegalStateException.class)로 try catch 안 해도 된다!


        // then
        fail("예외가 발생해야 한다."); // -> 예외가 발생하지 않았을 경우 테스트 실패 문구(이 테스트는 예외가 터져야 하는 테스트이다.)
    }

}