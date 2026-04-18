package com.unibook.controller;

import com.unibook.entity.Prenotazione;
import com.unibook.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService service;

    @PostMapping
    public Prenotazione create(
            @RequestParam Long userId,
            @RequestParam Long risorsaId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end,
            @RequestBody Prenotazione prenotazione
    ) {
        return service.creaPrenotazione(userId, risorsaId, start, end, prenotazione);
    }

    @GetMapping
    public List<Prenotazione> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}