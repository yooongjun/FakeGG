package LOL.demo.domain.member;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Member {

    // PK
    @Id @GeneratedValue
    @Column(name = "Member_id")
    private Long id;

    // 사용자 이름
    private String name;

    // 로그인 아이디
    private String loginId;

    // 비밀번호
    private String password;

    // 회원 가입 날짜
    private LocalDateTime joinDate;

    protected Member(){}

    public Member(String name, String loginId, String password){

        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.joinDate = LocalDateTime.now();

    }

    public void editMemberEntity(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}
