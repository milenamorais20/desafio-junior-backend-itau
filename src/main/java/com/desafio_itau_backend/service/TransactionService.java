package com.desafio_itau_backend.service;

import com.desafio_itau_backend.dto.TransactionRequestDTO;
import com.desafio_itau_backend.model.Transaction;
import com.desafio_itau_backend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
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

    public Transaction atualizar(Long id,TransactionRequestDTO transactionDTO){
        Optional<Transaction> transactionId = transactionRepository.findById(id);

        if (transactionId.isPresent()){
            Transaction transaction = transactionId.get();

            transaction.setValor(transactionDTO.getValor());
            transaction.setDataHora(transactionDTO.getDataHora());

            return transactionRepository.save(transaction);
        }else {
            throw new RuntimeException("Transação não encontrada.");
        }

    }

    public void deletar(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            transactionRepository.delete(transaction.get());
        }else {
            throw new RuntimeException("Transação não encontrada.");
        }
    }

    public DoubleSummaryStatistics getStatics(){
        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

    }
}
