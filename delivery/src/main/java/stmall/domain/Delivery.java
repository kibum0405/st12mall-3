package stmall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.DeliveryApplication;
import stmall.domain.DeliveryReturned;
import stmall.domain.DeliveyCancelled;
import stmall.domain.DeliveyStarted;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long userId;

    private String productId;

    private Integer qty;

    private String status;

    @PostPersist
    public void onPostPersist() {
        DeliveyStarted deliveyStarted = new DeliveyStarted(this);
        deliveyStarted.publishAfterCommit();

        DeliveyCancelled deliveyCancelled = new DeliveyCancelled(this);
        deliveyCancelled.publishAfterCommit();

        DeliveryReturned deliveryReturned = new DeliveryReturned(this);
        deliveryReturned.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public void completeDelivery(
        CompleteDeliveryCommand completeDeliveryCommand
    ) {
        //implement business logic here:

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void startDelivery(Ordered ordered) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveyStarted deliveyStarted = new DeliveyStarted(delivery);
        deliveyStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(ordered.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveyStarted deliveyStarted = new DeliveyStarted(delivery);
            deliveyStarted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancelDelivery(OrderCancelled orderCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveyCancelled deliveyCancelled = new DeliveyCancelled(delivery);
        deliveyCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveyCancelled deliveyCancelled = new DeliveyCancelled(delivery);
            deliveyCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
