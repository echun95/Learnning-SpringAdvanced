package spring.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.callback.TraceCallback;
import spring.advanced.trace.callback.TraceTemplate;
import spring.advanced.trace.logtrace.LogTrace;
import spring.advanced.trace.template.AbstractTemplate;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate temptlate;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.temptlate = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){
        return temptlate.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "OK";
            }
        });
    }
}
