package com.unibook.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    private boolean attivo = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;

    public enum Ruolo {
        STUDENTE, DOCENTE, ADMIN
    }
}