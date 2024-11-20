package hello.core.beanScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


public class SingleTonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addAccount();
        Assertions.assertEquals(bean1.count, 1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addAccount();
        Assertions.assertEquals(bean2.count, 1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);


        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertEquals(count1, 1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertEquals(count2,1);
    }

    static class ClientBean {
//        private PrototypeBean prototypeBean;

//        public  ClientBean(PrototypeBean prototypeBean) {
//
//            this.prototypeBean = prototypeBean;
//        }


        // 단위테 스트 어려워짐
//        @Autowired
//        private ApplicationContext ac;

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        // java 표준이지만 별도 라이브러리 추가가 필요함.
        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            /*
            의존관계를 주입받는게 아니라 필요한 의존관계를 찾아내는것 -> Dependency Lookup (DL)
           ac를 주입받으면 스프링 컨테이너에 종속적이고, 단위 테스트가 어려워짐.
             */
            prototypeBean.addAccount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {


        private int count = 0;

        public  void  addAccount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }


    }
}
