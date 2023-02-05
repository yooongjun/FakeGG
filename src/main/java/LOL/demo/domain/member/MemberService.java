package LOL.demo.domain.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    public void saveMember(MemberRequestDto memberRequestDto) {
        memberRepository.save(memberRequestDto.toEntity());
    }

    // 회원 조회
    public MemberResponseDto findMemberById(Long id){
        MemberResponseDto memberResponseDto = new MemberResponseDto( memberRepository.findById(id));
        return memberResponseDto;
    }

    // 회원 리스트 조회
    public List<MemberResponseDto> findAllMember(){
       return memberRepository.findAll()
               .stream().map(MemberResponseDto::new)
               .collect(Collectors.toList());
    }

    // 회원 정보 수정
    public Long EditMember(Long id, MemberRequestDto memberRequestDto) {
        Member editMember = memberRequestDto.toEntity();
        return memberRepository.edit(id, editMember);
    }

    // 회원 삭제
    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }


}
