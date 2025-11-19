package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.dto.StaticResponseDTO;
import com.desafio_itau_backend.model.Transaction;
import com.desafio_itau_backend.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/statics")
public class StaticController {
    private final TransactionService transactionService;

    public StaticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<StaticResponseDTO> getStatics(){
        DoubleSummaryStatistics statics = transactionService.getStatics();
        return ResponseEntity.ok(new StaticResponseDTO(statics));
    }
}
