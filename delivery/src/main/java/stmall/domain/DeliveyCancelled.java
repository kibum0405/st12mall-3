package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveyCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long userId;
    private String productId;
    private Integer qty;
    private String status;

    public DeliveyCancelled(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveyCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
