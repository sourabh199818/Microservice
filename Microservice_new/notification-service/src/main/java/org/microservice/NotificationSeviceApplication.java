package org.microservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationSeviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationSeviceApplication.class, args);
    }

    @KafkaListener(topics = "")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent)
    {
                 //// send out an email notification
        log.info("Received Notification -{}", orderPlacedEvent);

    }

}