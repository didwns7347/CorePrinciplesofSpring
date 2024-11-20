package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 빈 자동 등록
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired // 의존성 자동 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findByMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
