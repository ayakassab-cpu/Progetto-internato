package com.unibook.repository;

import com.unibook.entity.BloccaManutenzione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BloccaManutenzioneRepository extends JpaRepository<BloccaManutenzione, Long> {

    List<BloccaManutenzione> findByRisorsaId(Long risorsaId);

    // Blocchi che si sovrappongono a un intervallo
    List<BloccaManutenzione> findByRisorsaIdAndDataFineAfterAndDataInizioBefore(
            Long risorsaId,
            LocalDateTime start,
            LocalDateTime end
    );
}