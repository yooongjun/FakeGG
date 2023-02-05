package LOL.demo.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager entityManager;

    public void save(Member member){
        entityManager.persist(member);
    }

    public Member findById(Long id){
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll(){
        return entityManager.createQuery("SELECT m FROM Member m ", Member.class)
                .getResultList();
    }

    public Long edit(Long id, Member editMember) {
        Member member = findById(id);
        member.editMemberEntity(editMember.getName(), editMember.getLoginId(), editMember.getPassword());
        return member.getId();
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }


}
