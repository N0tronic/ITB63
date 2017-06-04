package de.umfrage.clientmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Virus on 27.05.2017.
 */
@Getter
@Setter
@EqualsAndHashCode
public class Antwortmoeglichkeit {

    private Integer antwortID;
    private String antworttext;
    private Integer antworthaeufigkeit = 0;

}
