package de.umfrage.feign;

import de.umfrage.clientmodel.Antwortmoeglichkeit;
import de.umfrage.clientmodel.Umfrage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Virus on 01.06.2017.
 */
@FeignClient("umfrage-backend")
public interface UmfrageClient {

    @GetMapping(value = "/online-umfrage/alleUmfragen", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Umfrage>> holeUmfrageInformationen();

    @GetMapping(value = "/online-umfrage/umfrageByTitel",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Umfrage> holeUmfrageByTitel(@RequestParam("umfragetitel") String umfragetitel);

    @PostMapping(value = "/online-umfrage/updateAntwort", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Antwortmoeglichkeit> updateAntworthäufigkeit(@RequestBody int antwortID);

    @GetMapping(value = "/online-umfrage/antwortenByUmfragetitel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Antwortmoeglichkeit>> holeUmfrageAntwortenByTitel(@RequestParam("umfragetitel") String umfragetitel);

    @PostMapping(value = "/online-umfrage/deleteUmfrage", consumes = MediaType.APPLICATION_JSON_VALUE)
    void deleteUmfrage(@RequestBody int umfrageID);
}
