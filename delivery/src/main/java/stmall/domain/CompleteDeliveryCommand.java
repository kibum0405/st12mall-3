package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CompleteDeliveryCommand {

    private String deliveryId;
    private String status;
}
