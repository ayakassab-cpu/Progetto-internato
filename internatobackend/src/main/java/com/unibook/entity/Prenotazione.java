package com.unibook.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "prenotazioni",
        uniqueConstraints = {
                // Impedisce prenotazioni doppie sulla stessa risorsa nello stesso momento
                @UniqueConstraint(columnNames = {"risorsa_id", "data_inizio", "data_fine"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "risorsa_id", nullable = false)
    private Risorsa risorsa;

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    private String motivazione;
    private int numeroPartecipanti;

    @Enumerated(EnumType.STRING)
    private StatoPrenotazione stato;

    // Per prenotazioni ricorrenti — tutte le istanze dello stesso gruppo
    // condividono lo stesso gruppoId
    private String gruppoId;

    private LocalDateTime creatoIl;

    @PrePersist
    public void prePersist() {
        this.creatoIl = LocalDateTime.now();
        if (this.stato == null) this.stato = StatoPrenotazione.CONFERMATA;
    }

    public enum StatoPrenotazione {
        CONFERMATA, CANCELLATA, IN_ATTESA
    }
}