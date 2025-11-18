package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.dto.TransactionRequestDTO;
import com.desafio_itau_backend.model.Transaction;
import com.desafio_itau_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
