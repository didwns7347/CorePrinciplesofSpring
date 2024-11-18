package hello.core.singleton;

import hello.core.beanfind.ApplicationContextExtendsFindTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import javax.swing.plaf.nimbus.State;

public class SingletonServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService stateFullService1 = ac.getBean(StateFullService.class);
        StateFullService stateFullService2 = ac.getBean(StateFullService.class);

        stateFullService1.order("userA",10000);
        stateFullService2.order("userB",20000);

        int price = stateFullService1.getPrice();
        System.out.println("price =" + price);

        Assertions.assertThat(price).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StateFullService stateFullService() {
            return new StateFullService();
        }
    }


}
