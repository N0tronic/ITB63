CREATE TABLE `Ersteller` (
  `ErstellerID`	INTEGER NOT NULL,
  `Name`	TEXT NOT NULL,
  `E-Mail`	TEXT NOT NULL UNIQUE ,
  PRIMARY KEY(`ErstellerID`)
);

CREATE TABLE `Umfrage` (
  `UmfrageID`	INTEGER NOT NULL,
  `Erstellungsdatum`	DATE NOT NULL,
  `Startdatum`	DATE NOT NULL,
  `Endedatum`	DATE NOT NULL,
  `Titel`	TEXT NOT NULL UNIQUE,
  `ErstellerID`	INTEGER NOT NULL,
  PRIMARY KEY(`UmfrageID`),
  FOREIGN KEY(`ErstellerID`) REFERENCES `Ersteller`(`ErstellerID`)
);

CREATE TABLE `Frage` (
  `FrageID`	INTEGER NOT NULL,
  `Fragetext`	TEXT NOT NULL,
  `Erläuterung`	TEXT,
  `UmfrageID` INTEGER NOT NULL,
  PRIMARY KEY(`FrageID`),
  FOREIGN KEY(`UmfrageID`) REFERENCES `Umfrage`(`UmfrageID`)
);

CREATE TABLE `Umfrageergebnis` (
  `ErgebnisID`	INTEGER NOT NULL,
  `Teilnehmerzahl`	INTEGER NOT NULL,
  `UmfrageID`	INTEGER NOT NULL,
  PRIMARY KEY(`ErgebnisID`),
  FOREIGN KEY(`UmfrageID`) REFERENCES `Umfrage`(`UmfrageID`)
);

CREATE TABLE `Antwortmöglichkeit` (
  `AntwortID`	INTEGER NOT NULL,
  `Antworttext`	TEXT NOT NULL,
  `Antworthäufigkeit`	INTEGER NOT NULL,
  `FrageID`	INTEGER NOT NULL,
  PRIMARY KEY(`AntwortID`),
  FOREIGN KEY(`FrageID`) REFERENCES `Frage`(`FrageID`)
);