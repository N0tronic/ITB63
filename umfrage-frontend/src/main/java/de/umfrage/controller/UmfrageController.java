package de.umfrage.controller;

import de.umfrage.clientmodel.Antwortmoeglichkeit;
import de.umfrage.clientmodel.Umfrage;
import de.umfrage.feign.UmfrageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Virus on 02.06.2017.
 */
@Controller
public class UmfrageController {

    @Autowired
    UmfrageClient umfrageClient;

    @PostMapping(value = "/aktualisiereAntworten")
    public String aktualisiereAntwort(Model model, int antwortID, String titel) {
        umfrageClient.updateAntworthäufigkeit(antwortID);
        return this.showUmfrageStatistics(titel,model);
    }

    @PostMapping(value = "/loescheUmfrage")
    public String loescheUmfrage(Model model, String titel, int umfrageID) {
        umfrageClient.deleteUmfrage(umfrageID);
        model.addAttribute("titel",titel);
        return "delete";
    }

    @GetMapping("/umfragestatistik")
    public String showUmfrageStatistics(@RequestParam("umfragetitel") String umfragetitel, Model model) {
        ResponseEntity<List<Antwortmoeglichkeit>> responseEntity = umfrageClient.holeUmfrageAntwortenByTitel(umfragetitel);
        ResponseEntity<Umfrage> umfrageEntity = umfrageClient.holeUmfrageByTitel(umfragetitel);
        Umfrage umfrage = umfrageEntity.getBody();
        List<Antwortmoeglichkeit> antwortmoeglichkeiten = responseEntity.getBody();
        int anzahl = 0,gesamtanzahl = 0;
        for (Antwortmoeglichkeit antwortmoeglichkeit : antwortmoeglichkeiten) {
            if(antwortmoeglichkeit.getAntworthaeufigkeit() > 0){
                gesamtanzahl += antwortmoeglichkeit.getAntworthaeufigkeit();
                anzahl++;
            }
        }
        Object[][] stats = new Object[anzahl+1][2];
        int i = 1;
        stats[0][0] = "Antwort";
        stats[0][1] = "Antwortanzahl";
        for(Antwortmoeglichkeit antwort : antwortmoeglichkeiten){
            if(antwort.getAntworthaeufigkeit() > 0){
                stats[i][0] = antwort.getAntworttext();
                stats[i][1] = antwort.getAntworthaeufigkeit();
                i++;
            }
        }
        model.addAttribute("gesamtanzahl", gesamtanzahl);
        model.addAttribute("titel", umfragetitel);
        model.addAttribute("frage", umfrage.getFragen().get(0).getFragetext());
        model.addAttribute("stats", stats);
        return "statistic";
    }

    @GetMapping("/alleUmfragen")
    public String showAlleUmfragen(Model model) {
        ResponseEntity<List<Umfrage>> umfragen = umfrageClient.holeUmfrageInformationen();
        model.addAttribute("umfragen", umfragen.getBody());
        return "alleUmfragen";
    }

    @GetMapping("/umfrage")
    public String showUmfrageByTitel(@RequestParam("umfragetitel") String umfragetitel, Model model) {
        String site = "error";
        ResponseEntity<Umfrage> umfrage = umfrageClient.holeUmfrageByTitel(umfragetitel);
        if (umfrage != null && umfrage.getBody() != null) {
            model.addAttribute("umfrage", umfrage.getBody());
            site = "umfrage";
        }
        return site;
    }
}
