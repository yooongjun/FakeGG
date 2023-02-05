package LOL.demo.domain.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveMember() throws Exception {
        //given
        Member member = new Member("TestMember", "test" , "1234");

        //when
        memberRepository.save(member);

        //then
        Member findMember = entityManager.find(Member.class, member.getId());
        assertThat(findMember.getName()).isEqualTo("TestMember");
    }

    @Test
    public void findMember() throws Exception {
        //given
        Member member = new Member("kim", "user" , "123");

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());

        //then
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    public void findAll() throws Exception {
        //given
        Member memberA = new Member("memberA", "user" , "123");
        Member memberB = new Member("memberB", "user" , "123");

        //when
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> list = memberRepository.findAll();
        //then

        assertThat(list.size()).isEqualTo(2);

    }

}