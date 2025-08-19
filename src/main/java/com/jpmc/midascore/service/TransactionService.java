package com.jpmc.midascore.service;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {
    private final UserRepository users;
    private final TransactionRecordRepository records;

    public TransactionService(UserRepository users, TransactionRecordRepository records) {
        this.users = users;
        this.records = records;
    }

    @Transactional
    public void process(Transaction tx) {
        var sender = users.findById(tx.getSenderId());
        var receiver = users.findById(tx.getRecipientId());
        // invalid sender or receiver
        if (sender == null || receiver == null) return;
        // insufficient sender funds
        if (sender.getBalance() < tx.getAmount()) return;

        // send transaction
        sender.setBalance(sender.getBalance() - tx.getAmount());
        receiver.setBalance(receiver.getBalance() + tx.getAmount());

        users.save(sender);
        users.save(receiver);

        var rec = new TransactionRecord();
        rec.setSender(sender);
        rec.setReceiver(receiver);
        rec.setAmount(tx.getAmount());
        records.save(rec);
    }
}
