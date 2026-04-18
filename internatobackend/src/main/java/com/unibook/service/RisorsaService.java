package com.unibook.service;

import com.unibook.entity.Risorsa;
import com.unibook.repository.RisorsaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RisorsaService {

    private final RisorsaRepository repository;

    public List<Risorsa> getAll() {
        return repository.findAll();
    }

    public Risorsa save(Risorsa r) {
        return repository.save(r);
    }

    public Risorsa getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Risorsa non trovata"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}