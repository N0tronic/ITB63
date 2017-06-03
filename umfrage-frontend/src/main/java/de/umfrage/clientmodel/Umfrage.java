package de.umfrage.clientmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Getter @Setter @EqualsAndHashCode
public class Umfrage {

    @JsonFormat(pattern = "dd.MM.yyyy, HH:mm:ss")
    private Date erstellungsdatum = new Date();
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date startdatum;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date endedatum;
    private String titel;
    private String email;
    private Integer umfrageID;

    private List<Frage> fragen;

}
