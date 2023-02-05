package LOL.demo.domain.member;

import lombok.Getter;
import lombok.Setter;

// 회원 가입 시 사용할 Dto
@Getter
@Setter
public class MemberRequestDto {

    /**
     * 회원명, 로그인 아이디, 비밀번호를 입력받아 저장
     */
    private String name;
    
    private String loginId;
    
    private String password;

    public Member toEntity(){
        return new Member(name, loginId, password);
    }

}
