package LOL.demo.domain.Member;

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

}
