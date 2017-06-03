package de.umfrage.clientmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Getter @Setter @EqualsAndHashCode
public class Frage {

    private Integer frageID;
    private String fragetext;
    private String erlaeuterung;
    private List<Antwortmoeglichkeit> antwortmoeglichkeiten;

}
