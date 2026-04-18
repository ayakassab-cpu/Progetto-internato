package com.unibook.service;

import com.unibook.entity.BloccaManutenzione;
import com.unibook.entity.Risorsa;
import com.unibook.repository.BloccaManutenzioneRepository;
import com.unibook.repository.RisorsaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloccaManutenzioneService {

    private final BloccaManutenzioneRepository repository;
    private final RisorsaRepository risorsaRepository;

    public BloccaManutenzione create(Long risorsaId, BloccaManutenzione blocco) {
        Risorsa r = risorsaRepository.findById(risorsaId)
                .orElseThrow(() -> new RuntimeException("Risorsa non trovata"));

        blocco.setRisorsa(r);
        return repository.save(blocco);
    }

    public List<BloccaManutenzione> getAll() {
        return repository.findAll();
    }
}