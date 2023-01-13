package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // jpa가 관리하는 객체, db과 매핑해서 씀 / 기본적으로 name은 클래스 이름으로 쓰기 때문에 굳이 name을 쓸 일은 별로 없음
//@Table(name = "MBR") // 클래스 이름과 db 테이블명이 다를 경우 매핑하기 위해 씀 -> 이걸 쓰면 from MBR로 됨
public class Member {

    @Id
    private Long id;

//    @Column(name = "name", insertable = true, updatable = false, nullable = false, columnDefinition = "varchar(100) default ‘EMPTY’")
    // insertable, updatable: jpa를 쓸 때에는 insert와 update때 절대 반영되지 않음(false일 경우)
    // nullable = false: not null
    // columnDefinition: 데이터베이스 컬럼 정보
    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum 타입 매핑 -> 반드시 STRING으로 할 것, ORDINAL로 할 경우 문제 발생
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob // 큰 컨텐츠를 넣고 싶으면 Lob
    private String description;

    @Transient
    private int temp;

    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
