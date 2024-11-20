package hello.core.lifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient  {
    private String url;

    public NetworkClient() {
        System.out.println("call construct method url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public  void call(String message) {
        System.out.println("call: " + url + " message: " + message);
    }


    public void disconnect() {
        System.out.println("close: " + url);
    }
    // 자바 표준 언노테이션임.
    @PostConstruct
    public void init()  {
        System.out.println("PostConstruct");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("PreDestroy");
        disconnect();
    }

    // 외부 라이브러리 사용중이라 언노테이션 추가가 불가능한경우 @Bean(init, distroy)를 활용해라.
}
