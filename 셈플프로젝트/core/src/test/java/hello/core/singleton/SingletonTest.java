package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수 자바")
    void pureContainer() {
        AppConfig ac = new AppConfig();
        MemberService memberService2 = ac.memberService();
        MemberService memberService1 = ac.memberService();
        Assertions.assertNotEquals(memberService1,memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService service1 = SingletonService.getInstance();
        SingletonService service2 = SingletonService.getInstance();
        Assertions.assertSame(service1,service2);
    }

    @Test
    @DisplayName("spring container and singleton")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        Assertions.assertSame(memberService1,memberService2);
    }
}
