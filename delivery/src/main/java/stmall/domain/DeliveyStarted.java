package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveyStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long userId;
    private String productId;
    private Integer qty;
    private String status;

    public DeliveyStarted(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveyStarted() {
        super();
    }
}
//>>> DDD / Domain Event
