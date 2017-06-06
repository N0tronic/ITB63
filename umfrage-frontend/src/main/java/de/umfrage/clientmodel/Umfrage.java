package de.umfrage.clientmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Getter @Setter @EqualsAndHashCode
public class Umfrage {

    @JsonFormat(pattern = "dd.MM.yyyy, HH:mm:ss")
    private Date erstellungsdatum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date startdatum;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endedatum;
    private String titel;
    private String email;
    private Integer umfrageID;

    private List<Frage> fragen;

}
