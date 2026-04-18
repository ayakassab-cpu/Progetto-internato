package com.unibook.repository;

import com.unibook.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByUserId(Long userId);

    List<Prenotazione> findByRisorsaId(Long risorsaId);

    // Prenotazioni sovrapposte
    List<Prenotazione> findByRisorsaIdAndDataFineAfterAndDataInizioBefore(
            Long risorsaId,
            LocalDateTime start,
            LocalDateTime end
    );
}