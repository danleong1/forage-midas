package com.jpmc.midascore.listener;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {
    private final TransactionService service;

    public TransactionListener(TransactionService service) {
        this.service = service;
    }

    // Called when msg arrives at the topic
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Transaction tx) {
        System.out.println("Processing: " + tx);
        service.process(tx);
        System.out.println("Processed: " + tx);
    }
}
