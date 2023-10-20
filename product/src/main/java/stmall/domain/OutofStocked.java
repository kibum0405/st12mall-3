package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OutofStocked extends AbstractEvent {

    private Long id;
    private String productName;
    private Integer stock;

    public OutofStocked(Product aggregate) {
        super(aggregate);
    }

    public OutofStocked() {
        super();
    }
}
//>>> DDD / Domain Event
