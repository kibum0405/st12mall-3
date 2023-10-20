package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import stmall.infra.AbstractEvent;

@Data
public class Ordered extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String status;
    private String address;
}
