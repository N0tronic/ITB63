package de.umfrage.clientmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Getter @Setter @EqualsAndHashCode
public class Ersteller {

    private Integer erstellerID;
    private String name;
    private String email;
    private List<Umfrage> umfragen;
}
