package stmall.domain;

import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String status;
    private String address;
}
