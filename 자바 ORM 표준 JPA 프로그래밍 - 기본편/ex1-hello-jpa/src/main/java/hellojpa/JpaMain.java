package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        /* JPA의 모든 데이터 변경은 트랜잭션 안에서 실행 */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 애플리케이션 로딩 시 딱 하나만 만들어놔야함

        EntityManager em = emf.createEntityManager();
        // 한 단위를 실행할 때마다 만들어줘야함

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1. 회원 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); // 저장할 때 persist

//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());
            /*
                Hibernate:
                    select
                        member0_.id as id1_0_0_,
                        member0_.name as name2_0_0_
                    from
                        Member member0_
                    where
                        member0_.id=?
                findMember = 1
                findMember = HelloA
            */

            // 2. 회원 수정
//            findMember.setName("HelloJPA");
            // em.persist(member); // 안 해도 됨
            /*
                update
                    hellojpa.Member update
                                Member
                        set
                        name=?
                        where
                        id=?
            */

            // 단순 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
            /*
                Hibernate:
                select
                    m
                from
                    Member as m select
                        member0_.id as id1_0_,
                                member0_.name as name2_0_
                        from
                        Member member0_

                member.name = HelloJPA
                member.name = HelloB
            */

            // 조건부 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(5).setMaxResults(8).getResultList();
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
            /*
                Hibernate:
                select
                    m
                from
                    Member as m select
                        member0_.id as id1_0_,
                                member0_.name as name2_0_
                        from
                        Member member0_ limit ? offset ?
            */

            // 비영속 -> jpa와 관련없음
//            Member member = new Member();
////            member.setId(100L);
//            member.setId(101L);
//            member.setName("HelloJPA");

            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member); // member가 영속성 컨텍스트에 의해 관리되기 시작
////            em.detach(member); // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            System.out.println("=== AFTER ===");
            /*
                === BEFORE ===
                === AFTER ===
                Hibernate: 
                    insert hellojpa.Member
                         insert
                                    into
                            Member
                                    (name, id)
                            values
                                    (?, ?)
            */


            // 없는 데이터 처음 실행
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            /*
                findMember.id = 101 // select 쿼리 없음. 1차 캐시에서 조회
                findMember.name = HelloJPA
                Hibernate:
                    insert hellojpa.Member
                        insert
                                into
                        Member
                                (name, id)
                        values
                                (?, ?)
            */

            // 영속
            // 1차 캐시에서 조회
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("result = " + (findMember1 == findMember2));
//            result = true
            /*
            Hibernate:
            select
                member0_.id as id1_0_0_,
                member0_.name as name2_0_0_
            from
                Member member0_
            where
                member0_.id=?
            */

            // 영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("=========================");
//            tx.commit(); // 이거 하는 시점에 db로 쿼리가 날라감
            /*
            =========================
            Hibernate:
                insert hellojpa.Member
                    insert
                            into
                    Member
                            (name, id)
                    values
                            (?, ?)
            Hibernate:
                insert hellojpa.Member
                insert
                        into
                Member
                        (name, id)
                values
                        (?, ?)
            */
            // System.out.println("=========================");가 출력된 이후에 쿼리로 날라감 -> tx.commit;을 함으로써 db에 저장됨을 알 수 있음

//            Member member = em.find(Member.class, 150L); // Member는 영속 상태 -> find를 통해 가져올 때 jpa가 150L을 영속성 컨텍스트에 올림
//            member.setName("ZZZZZ"); // 이게 변경 끝임

//            em.persist(member); // 이 코드가 있어야 하는 것이 아닐까? -> 쓰면 X
//            if(member.getName().equals("ZZZZZ")){
//                em.persist(member);
//            } // 이럴 때나 persist 씀

//            System.out.println("======================");
//            tx.commit(); // 변경 반영

            /*
            Hibernate:
                select
                    member0_.id as id1_0_0_,
                    member0_.name as name2_0_0_
                from
                    Member member0_
                where
                    member0_.id=?
            ======================
            Hibernate:
                update
                    hellojpa.Member update
                                Member
                        set
                        name=?
                        where
                        id=?
            */

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush();
//
//            System.out.println("======================");
//            tx.commit(); // 변경 반영

            /*
                Hibernate:
                    insert hellojpa.Member
                    insert
                                into
                        Member
                                (name, id)
                        values
                                (?, ?)
                ======================
            */

//            Member member = em.find(Member.class, 150L); // Member는 영속 상태 -> find를 통해 가져올 때 jpa가 150L을 영속성 컨텍스트에 올림
//            member.setName("AAAAA"); // 이게 변경 끝임
//
//            em.detach(member); // 더 이상 영속성 컨텍스트에서 관리하지 마라 => tx.commit()해도 쿼리를 안날림
//
//            Member member2 = em.find(Member.class, 150L);
//
//            System.out.println("======================");
//            tx.commit();

            /*
                Hibernate:
                    select
                        member0_.id as id1_0_0_,
                        member0_.name as name2_0_0_
                    from
                        Member member0_
                    where
                        member0_.id=?
                ======================
            */

//            Member member = em.find(Member.class, 150L); // Member는 영속 상태 -> find를 통해 가져올 때 jpa가 150L을 영속성 컨텍스트에 올림
//            member.setName("AAAAA"); // 이게 변경 끝임

//            em.clear(); // 초기화

//            Member member2 = em.find(Member.class, 150L);
//
//            System.out.println("======================");
//            tx.commit();
            /*
            Hibernate:
                select
                    member0_.id as id1_0_0_,
                    member0_.name as name2_0_0_
                from
                    Member member0_
                where
                    member0_.id=?
            Hibernate:
                select
                    member0_.id as id1_0_0_,
                    member0_.name as name2_0_0_
                from
                    Member member0_
                where
                    member0_.id=?
            ======================
            // 첫 번째 쿼리는 Member member = em.find(Member.class, 150L);에 관한 쿼리고
            // 두 번째 쿼리는 em.clear()를 했기 때문에 초기화 된 영속성 컨텍스트에서 다시 jpa가 150L을 영속성 컨텍스트에 올리기 위해 날리는 쿼리

            */

            Member member = new Member();
            member.setId(3L);
            member.setUsername("C");
            member.setRoleType(RoleType.GUEST);

            em.persist(member); // db에 저장

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
