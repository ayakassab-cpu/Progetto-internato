package com.unibook.service;

import com.unibook.entity.*;
import com.unibook.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final RisorsaRepository risorsaRepository;
    private final UserRepository userRepository;
    private final BloccaManutenzioneRepository bloccoRepository;

    public Prenotazione creaPrenotazione(Long userId, Long risorsaId,
                                         LocalDateTime start, LocalDateTime end,
                                         Prenotazione prenotazione) {

        Risorsa risorsa = risorsaRepository.findById(risorsaId)
                .orElseThrow(() -> new RuntimeException("Risorsa non trovata"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User non trovato"));

        // Check blocchi manutenzione
        if (!bloccoRepository
                .findByRisorsaIdAndDataFineAfterAndDataInizioBefore(risorsaId, start, end)
                .isEmpty()) {
            throw new RuntimeException("Risorsa in manutenzione");
        }

        // Check conflitti prenotazioni
        if (!prenotazioneRepository
                .findByRisorsaIdAndDataFineAfterAndDataInizioBefore(risorsaId, start, end)
                .isEmpty()) {
            throw new RuntimeException("Risorsa già prenotata");
        }

        prenotazione.setUser(user);
        prenotazione.setRisorsa(risorsa);
        prenotazione.setDataInizio(start);
        prenotazione.setDataFine(end);

        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getAll() {
        return prenotazioneRepository.findAll();
    }

    public void delete(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}