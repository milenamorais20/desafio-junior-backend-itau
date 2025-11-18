package com.desafio_itau_backend.service;

import com.desafio_itau_backend.model.Transaction;
import com.desafio_itau_backend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    private TransactionRepository transactionRepository;

    public Transaction criar (Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction buscarPorId(Long id){
        Optional<Transaction> transactionId = transactionRepository.findById(id);
        if (transactionId.isPresent()){
            return transactionId.get();
        }else {
            throw new RuntimeException("Transação não encontrada.");
        }
    }

    public List<Transaction> buscarTodas(){
        return transactionRepository.findAll();
    }
}
