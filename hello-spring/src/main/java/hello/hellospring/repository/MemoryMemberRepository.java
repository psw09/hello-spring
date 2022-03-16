package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository  implements MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>();
    private  static long sequence =0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /*
    * 멤버를 루프로 돌리면서
    * 멤버의 이름이 파라미터로 넘어온 값과 같으면 필터링 하기
    * 끝가지 없으면 옵셔널로 반환
    * */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

//    @Override
//    public void clearStore(){
//        store.clear();
//    }
}
