package stmall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.ProductApplication;
import stmall.domain.OutofStocked;
import stmall.domain.StockDecreased;
import stmall.domain.StockIncreased;

@Entity
@Table(name = "Product_table")
@Data
//<<< DDD / Aggregate Root
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private Integer stock;

    @PostPersist
    public void onPostPersist() {
        OutofStocked outofStocked = new OutofStocked(this);
        outofStocked.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        StockDecreased stockDecreased = new StockDecreased(this);
        stockDecreased.publishAfterCommit();

        StockIncreased stockIncreased = new StockIncreased(this);
        stockIncreased.publishAfterCommit();
    }

    public static ProductRepository repository() {
        ProductRepository productRepository = ProductApplication.applicationContext.getBean(
            ProductRepository.class
        );
        return productRepository;
    }

    //<<< Clean Arch / Port Method
    public static void stockDecrease(DeliveryCompleted deliveryCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Product product = new Product();
        repository().save(product);

        StockDecreased stockDecreased = new StockDecreased(product);
        stockDecreased.publishAfterCommit();
        OutofStocked outofStocked = new OutofStocked(product);
        outofStocked.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryCompleted.get???()).ifPresent(product->{
            
            product // do something
            repository().save(product);

            StockDecreased stockDecreased = new StockDecreased(product);
            stockDecreased.publishAfterCommit();
            OutofStocked outofStocked = new OutofStocked(product);
            outofStocked.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void stockIncrease(DeliveryReturned deliveryReturned) {
        //implement business logic here:

        /** Example 1:  new item 
        Product product = new Product();
        repository().save(product);

        StockIncreased stockIncreased = new StockIncreased(product);
        stockIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryReturned.get???()).ifPresent(product->{
            
            product // do something
            repository().save(product);

            StockIncreased stockIncreased = new StockIncreased(product);
            stockIncreased.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
