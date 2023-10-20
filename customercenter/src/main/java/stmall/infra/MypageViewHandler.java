package stmall.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

@Service
public class MypageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1(@Payload Ordered ordered) {
        try {
            if (!ordered.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setOrderId(String.valueOf(ordered.getId()));
            mypage.setProudctId(String.valueOf(ordered.getProductId()));
            mypage.setProductName(ordered.getProductName());
            mypage.setQty(ordered.getQty());
            mypage.setOrderState(ordered.getStatus());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCompleted_then_UPDATE_1(
        @Payload DeliveryCompleted deliveryCompleted
    ) {
        try {
            if (!deliveryCompleted.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByOrderId(
                String.valueOf(deliveryCompleted.getOrderId())
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setDeliveryStatus(deliveryCompleted.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryReturned_then_UPDATE_2(
        @Payload DeliveryReturned deliveryReturned
    ) {
        try {
            if (!deliveryReturned.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByOrderId(
                String.valueOf(deliveryReturned.getOrderId())
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setDeliveryStatus(deliveryReturned.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
