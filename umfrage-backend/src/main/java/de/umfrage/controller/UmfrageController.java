package de.umfrage.controller;

import de.umfrage.entity.Antwortmöglichkeit;
import de.umfrage.entity.Ersteller;
import de.umfrage.entity.Umfrage;
import de.umfrage.repository.AntwortRepository;
import de.umfrage.repository.ErstellerRepository;
import de.umfrage.repository.UmfrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@RestController
@RequestMapping("/online-umfrage")
@CrossOrigin(origins = "*")
public class UmfrageController {

    @Autowired
    private UmfrageRepository umfrageRepository;
    @Autowired
    private ErstellerRepository erstellerRepository;
    @Autowired
    private AntwortRepository antwortRepository;

    @GetMapping(value = "/umfrageByTitel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> holeUmfrageByTitel(@RequestParam("umfragetitel") String umfragetitel) {
        Umfrage umfrage = umfrageRepository.findByTitel(umfragetitel);
        return ResponseEntity.ok(umfrage);
    }

    @GetMapping(value = "/alleUmfragen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> holeUmfrageInformationen() {
        List<Umfrage> umfragen = umfrageRepository.findAllByOrderByErstellungsdatumDesc();
        return ResponseEntity.ok(umfragen);
    }

    @PostMapping(value = "/updateAntwort", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAntworthäufigkeit(@RequestBody Antwortmöglichkeit antwortmöglichkeit) {
        Antwortmöglichkeit antwort = antwortRepository.findByAntwortID(antwortmöglichkeit.getAntwortID());
        Integer antworthäufigkeit = antwort.getAntworthaeufigkeit() + 1;
        antwort.setAntworthaeufigkeit(antworthäufigkeit);
        antwort = antwortRepository.save(antwort);
        return ResponseEntity.ok(antwort);
    }

    @PutMapping(value = "/erstelleUmfrage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> erstelleUmfrage(@RequestBody Ersteller entry) {
        Ersteller ersteller = erstellerRepository.findAllByEmail(entry.getEmail());
        entry.setName(ersteller.getName());
        entry.setErstellerID(ersteller.getErstellerID());
        entry = erstellerRepository.save(entry);
        return ResponseEntity.ok(entry);
    }


}
