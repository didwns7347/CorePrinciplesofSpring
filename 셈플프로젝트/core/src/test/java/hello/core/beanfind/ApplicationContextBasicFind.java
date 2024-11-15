package hello.core.beanfind;

import hello.core.config.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFind {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("search bean by name")
    void findBeanByName() {
        MemberService ms = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(ms).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("search bean by Type")
    void findBeanByType() {
        MemberService ms = ac.getBean( MemberService.class);
        Assertions.assertThat(ms).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("search bean by Real Type")
    void findBeanByImplType() {
        MemberService ms = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(ms).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("search bean by name not found")
    void findBeanByNameX() {
//        MemberService ms = ac.getBean("xxxx", MemberService.class);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            System.out.println("Throw Error");
            ac.getBean("xxxx", MemberService.class);

        });
    }
}
