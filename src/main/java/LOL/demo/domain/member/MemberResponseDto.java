package LOL.demo.domain.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
// 회원 조회용 Dto
public class MemberResponseDto {

    /**
     * 회원 정보 요청 시 응답할 Dto 객체
     */
    private Long id;
    private String name;

    private String loginId;

    private String password;

    private LocalDateTime joinDate;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.joinDate = member.getJoinDate();
    }
}
