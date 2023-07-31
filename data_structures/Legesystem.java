package data_strukturer;

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Lege;
import Legemiddel;
import MilResept;
import Narkotisk;
import Pasient;
import Resept;
import Spesialist;
import UlovligUtskrift;
import Vanedannende;
import Vanlig; 

public class Legesystem {

    public IndeksertListe<Pasient> pasienterListe = new IndeksertListe<>();
    public IndeksertListe<Legemiddel> legemidlerListe = new IndeksertListe<>();
    public Prioritetskoe<Lege> legerListe = new Prioritetskoe<>(); // prioritetskoe slik at leger blir sortert alfabetisk etter navn
    public IndeksertListe<Resept> resepterListe = new IndeksertListe<>();


    public void lesInnFraFil(String filnavn) {

        Scanner scanner = null;

        try {

            File fil = new File(filnavn);
            scanner = new Scanner(fil);

        } catch (FileNotFoundException e) {

            System.out.println("Filnavnet '" + filnavn + "' ikke funnet.");
            System.exit(1);
        }
            

            while(scanner.hasNextLine()) {
                String[] linje = scanner.nextLine().split(","); 

                // leser inn pasienter
                if (linje[0].contains("Pasienter")) {

                    linje = scanner.nextLine().split(",");

                    while (!(linje[0].contains("#"))) {

                        String pasientNavn = linje[0];
                        String pasientFnr = linje[1];

                        pasienterListe.leggTil(new Pasient(pasientNavn, pasientFnr));
                        linje = scanner.nextLine().split(",");
                    }
                }

                // leser inn legemidler
                if (linje[0].contains("Legemidler")) {
                    
                    linje = scanner.nextLine().split(",");

                    while (!(linje[0].contains("#"))) {

                        // variabler som er felles for alle legemidler
                        String navn = linje[0];
                        int pris = Integer.parseInt(linje[2].trim());
                        double virkestoff = Double.parseDouble(linje[3].trim());

                        if (linje[1].equals("narkotisk")) {

                            int styrke = Integer.parseInt(linje[4].trim());
                            legemidlerListe.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));

                        } else if (linje[1].equals("vanedannende")) {

                            int styrke = Integer.parseInt(linje[4].trim());
                            legemidlerListe.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));

                        } else if (linje[1].equals("vanlig")) {

                            legemidlerListe.leggTil(new Vanlig(navn, pris, virkestoff));
                        }

                        linje = scanner.nextLine().split(",");
                    }
                }
                
                // leser inn leger
                if (linje[0].contains("Leger")) {

                    linje = scanner.nextLine().split(",");

                    while (!(linje[0].contains("#"))) {

                        String navn = linje[0];
                        String kontrollkode = linje[1];

                        // hvis kontrollkode er 0 er legen en vanlig lege
                        if (kontrollkode.equals("0")) { 
        
                            Lege lege = new Lege(navn);
                            legerListe.leggTil(lege);

                        // ellers er legen en legespesialist
                        } else { 

                            Spesialist spesialist = new Spesialist(navn, kontrollkode);
                            legerListe.leggTil(spesialist);
                        }

                        linje = scanner.nextLine().split(",");
                    }
                }

                // leser inn resepter
                if (linje[0].contains("Resepter")) {

                    linje = scanner.nextLine().split(",");

                    while (!(linje[0].contains("#"))) {

                        // felles for alle resepter
                        int legemiddelId = Integer.parseInt(linje[0]);
                        String legeNavn = linje[1];
                        int pasientId = Integer.parseInt(linje[2]);
                        String typeResept = linje[3];

                        int reit = -1; // denne verdien er en placeholder

                        if (!(typeResept.equals("militaer"))) {
                            reit = Integer.parseInt(linje[4]);
                        }

                        leggTilResept(typeResept, legeNavn, pasientId, legemiddelId, reit);
                        
                        if (!(scanner.hasNextLine())) {
                            return;
                        }

                        linje = scanner.nextLine().split(",");
                        
                    }
                }
            }
            
            scanner.close();
        } 
    
    public void leggTilLege(String navn) {
        legerListe.leggTil(new Lege(navn));
    }

    public void leggTilLegespesialist(String navn, String kontrollkode) {
        legerListe.leggTil(new Spesialist(navn, kontrollkode));
    }

    public void leggTilPasient(String navn, String fnr) {
        pasienterListe.leggTil(new Pasient(navn, fnr));
    }

    public void leggTilVanligLegemiddel(String navn, int pris, double virkestoffMg) {
        legemidlerListe.leggTil(new Vanlig(navn, pris, virkestoffMg));
    }

    public void leggTilVanedannendeLegemiddel(String navn, int pris, double virkestoffMg, int styrke) {
        legemidlerListe.leggTil(new Vanedannende(navn, pris, virkestoffMg, styrke));
    }

    public void leggTilNarkotiskLegemiddel(String navn, int pris, double virkestoffMg, int styrke) {
        legemidlerListe.leggTil(new Narkotisk(navn, pris, virkestoffMg, styrke));
    }

    public void leggTilResept(String typeResept, String legeNavn, int pasientId, int legemiddelId, int reit) {

        // instansierer objekter som en resept er avhengig av
        Lege lege = null;
        Pasient pasient = null;
        Legemiddel legemiddel = null;

        // finn legeobjektet og lagre det i lege
        for (Lege legeILoop : legerListe) {
            if (legeILoop.hentNavn().equals(legeNavn)) {
                lege = legeILoop;
            }
        }
        // hvis legenavnet ikke blir funnet i loopen over
        if (lege == null) {
            System.out.println("Legen " + legeNavn + " finnes ikke i systemet. Prøv på nytt.");
            return;
        }
        
        // finn pasientobjektet og lagre det i pasient
        for (Pasient pasientILoop : pasienterListe) {
            if (pasientILoop.hentId() == pasientId) {
                pasient = pasientILoop;
            }
        }
        // hvis pasientnavnet ikke blir funnet i loopen over
        if (pasient == null) {
            System.out.println("Pasient-ID " + pasientId + " finnes ikke i systemet. Prøv på nytt.");
            return;
        } 

        // finn legemiddelobjektet og lagre det i legemiddel
        for (Legemiddel legemiddelILoop : legemidlerListe) {
            if (legemiddelILoop.hentId() == legemiddelId) {
                legemiddel = legemiddelILoop;
            }
        }
        // hvis legemiddelnavnet ikke blir funnet i loopen over
        if (legemiddel == null) {
            System.out.println("Legemiddel-ID " + legemiddelId + " finnes ikke i systemet. Prøv på nytt.");
            return;
        }

        // lag hvit resept
        if (typeResept.equals("hvit")) {
                            
            try {
                
                resepterListe.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));

            } catch (UlovligUtskrift u) {
                System.out.println(u);

            } 
        
        // lag millitaer resept
        } else if (typeResept.equals("militaer")) {

            try {
                
                resepterListe.leggTil(lege.skrivMilResept(legemiddel, pasient));

            } catch (UlovligUtskrift u) {
                System.out.println(u);
            } 

        // lag p-resept
        } else if (typeResept.equals("p")) {

            try {
                
                resepterListe.leggTil(lege.skrivPResept(legemiddel, pasient, reit));

            } catch (UlovligUtskrift u) {
                System.out.println(u);
            } 

        // lag blaa resept
        } else if (typeResept.equals("blaa")) {

            try {
                
                resepterListe.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));

            } catch (UlovligUtskrift u) {
                System.out.println(u);  
            } 
        }
    }

    public void brukResept(int pasientId, int valgtReseptId) {

        for (Resept resept : resepterListe) {
            if (resept.hentId() == valgtReseptId) {
                if (resept.hentPasient().hentId() != pasientId) {
                    System.out.println("Resept-ID " + valgtReseptId + " tilhører ikke denne pasienten.");
                    return;
                }
                if (resept.reit == 0) {
                    System.out.println("Kunne ikke bruke resept for " + resept.hentLegemiddel().hentNavn() + " pga. ingen gjenværene reit.");
                    break;
                }
                resept.reit --;
                System.out.println("Resepten for " + resept.hentLegemiddel().hentNavn() + " ble brukt. Gjenværende reit: " + resept.reit);
                break;
            }
        }
    }

    public void skrivTilFil(String filnavn) {
        
        try {

            FileWriter nyFil = new FileWriter(filnavn);

            nyFil.write("# Pasienter (navn,fnr)\n");
            for (Pasient pasient : pasienterListe) {
                nyFil.write(pasient.hentNavn() + "," + pasient.hentFodselsnr() + "\n");
            }

            nyFil.write("# Legemidler (navn,type,pris,virkestoff[,styrke])\n");
            for (Legemiddel legemiddel : legemidlerListe) {

                if (legemiddel instanceof Vanlig) {
                    nyFil.write(legemiddel.hentNavn() + ",vanlig," + legemiddel.hentPris() + "," + legemiddel.virkestoffMg + "\n");

                } else if (legemiddel instanceof Narkotisk) {
                    nyFil.write(legemiddel.hentNavn() + ",narkotisk," + legemiddel.hentPris() + "," + legemiddel.virkestoffMg + "," + ((Narkotisk) legemiddel).styrke + "\n");

                } else if (legemiddel instanceof Vanedannende) {
                    nyFil.write(legemiddel.hentNavn() + ",vanedannende," + legemiddel.hentPris() + "," + legemiddel.virkestoffMg + "," + ((Vanedannende) legemiddel).styrke + "\n");
                }
            }

            nyFil.write("# Leger (navn, kontrollid / 0 hvis vanlig lege)\n");
            for (Lege lege : legerListe) {

                nyFil.write(lege.hentNavn() + ",");

                if (lege instanceof Spesialist) {
                    nyFil.write(((Spesialist) lege).kontrollkode);  

                } else {
                    nyFil.write("0");
                }

                nyFil.write("\n");
            }

            nyFil.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
            for (Resept resept : resepterListe) {

                nyFil.write(resept.hentLegemiddel().hentId() + "," + resept.hentLege().hentNavn() + "," + resept.hentPasient().hentId() + "," + resept.typeResept());

                if (!(resept instanceof MilResept)) {
                    nyFil.write("," + resept.hentReit());
                }

                nyFil.write("\n");
            }

            System.out.println("Filen " + filnavn + " ble suksessfullt opprettet!");
            nyFil.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printPasienter() {

        System.out.println("---------------------------------------");
        System.out.println("Pasienter:\n");

        for (Pasient pasient : pasienterListe) {
            System.out.println(pasient);
        }
        System.out.println("---------------------------------------");
    }

    public void printLeger() {

        System.out.println("---------------------------------------");
        System.out.println("Leger:\n");

        for (Lege lege : legerListe) {
            System.out.println(lege);
        }
        System.out.println("---------------------------------------");
    }

    public void printLegemidler() {
        System.out.println("---------------------------------------");
        System.out.println("Legemidler:\n");

        for (Legemiddel legemiddel : legemidlerListe) {
            System.out.println(legemiddel);
        }
        System.out.println("---------------------------------------");
    }

    public void printResepter() {
        System.out.println("---------------------------------------");
        System.out.println("Resepter:\n");

        for (Resept resept : resepterListe) {
            System.out.println(resept + "\n");
        }
        System.out.println("---------------------------------------");
    }

    public void printAlt() {
        printPasienter();
        printLeger();
        printLegemidler();
        printResepter();
    }

}
