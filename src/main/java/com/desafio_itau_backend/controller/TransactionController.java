package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.dto.TransactionRequestDTO;
import com.desafio_itau_backend.model.Transaction;
import com.desafio_itau_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    private Transaction criar(@RequestBody TransactionRequestDTO transactionRequestDTO){
        Transaction transaction = new Transaction(transactionRequestDTO.getValor(), transactionRequestDTO.getDataHora());
        return transactionService.criar(transaction);
    }

    @GetMapping("/transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Transaction buscarPorId(@PathVariable Long id){
        return transactionService.buscarPorId(id);
    }

    @GetMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    private List<Transaction> buscarTodas(){
        return transactionService.buscarTodas();
    }

    @PutMapping("/transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Transaction atualizar(@PathVariable Long id,@RequestBody TransactionRequestDTO transactionDTO){
        return transactionService.atualizar(id, transactionDTO);
    }
}
