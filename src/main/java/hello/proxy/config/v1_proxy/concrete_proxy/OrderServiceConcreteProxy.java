package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy( OrderServiceV2 target, LogTrace logTrace) {
        super(null); // 사용 안함 -> null처리, 클래스 기반 프록시의 단점 -> 자바 기본 문법에 의해 자식 클래스 생성 시 항상 super() 부모클래스 생성자 만들어야함.
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            // targer 호출(실제 객체)

            target.orderItem(itemId);

            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
