package com.unibook.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "risorse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Risorsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoRisorsa tipo;

    @Positive
    private int capienza;

    private String edificio;
    private String piano;

    // Dotazioni (proiettore, PC, lavagna, ecc.)
    private boolean proiettore;
    private boolean pc;
    private boolean lavagna;
    private boolean videoconferenza;

    private boolean attiva = true;

    @OneToMany(mappedBy = "risorsa", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;

    @OneToMany(mappedBy = "risorsa", cascade = CascadeType.ALL)
    private List<BloccaManutenzione> blocchi;

    public enum TipoRisorsa {
        AULA, LABORATORIO, POSTAZIONE_PC, SALA_RIUNIONI
    }
}