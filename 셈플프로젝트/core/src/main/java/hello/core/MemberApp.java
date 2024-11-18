package hello.core;

import hello.core.config.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig.xml = new AppConfig();
//        MemberService memberService = appConfig.xml.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        memberService.join(member);
        Member findMember = memberService.findByMember(1L);
        System.out.println("new member = " + member.getName() );
        System.out.println("find member = " + findMember.getName() );

    }
}
