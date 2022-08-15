package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // enhancer를 사용해서 프록시 생성

        enhancer.setSuperclass(ConcreteService.class); // 구체 클래스 상속 지정
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 프록시에 적용할 실행 로직 할당
        ConcreteService proxy = (ConcreteService) enhancer.create(); // 프록시 생성

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();

        //jdk 동적 프록시는 인터페이스를 구현해서 프록시를 생성, CGLIB는 구체 클래스를 상속해서 프록시를 생성
    }
}
