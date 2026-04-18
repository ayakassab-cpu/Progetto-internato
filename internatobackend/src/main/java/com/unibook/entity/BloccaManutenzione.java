package com.unibook.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "blocchi_manutenzione")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloccaManutenzione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "risorsa_id", nullable = false)
    private Risorsa risorsa;

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    @Column(nullable = false)
    private String motivo;
}
