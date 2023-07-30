import java.util.Scanner;

public class Hovedprogram {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Legesystem legesystem = new Legesystem();
        legesystem.lesInnFraFil("legedata-stor.txt"); 
        String fortsett = "ja";

        System.out.println("\nVelkommen til ditt legesystem!");
        System.out.println("Hva ønsker du å gjøre i dag?");
        
        while (fortsett.equals("ja")) {

            System.out.println();
            System.out.println("Valg 1: Vis fullstendig oversikt over pasienter, leger, legemidler og resepter.");
            System.out.println("Valg 2: Legg til en lege/pasient/resept/legemiddel.");
            System.out.println("Valg 3: Bruk en resept til en pasient.");
            System.out.println("Valg 4: Vis statistikk.");
            System.out.println("Valg 5: Skriv til ny fil.");
            System.out.println("Valg 6: Avslutt program.");
            System.out.print("\nSvar med tallene (1-6): ");

            int valg;

            try {

                valg = Integer.parseInt(scanner.nextLine());

                if (valg < 1 || valg > 6) {
                    System.out.println("\nUgyldig valg. Velg mellom 1-6.");
                    continue;
                } 

            } catch (NumberFormatException e) {
                System.out.println("\nDu må velge et heltall mellom 1-6.");
                continue;
            }
            
            System.out.println();

            switch (valg) {

                case 1:
                    legesystem.printAlt();
                    break;
                
                case 2:
                    System.out.print("Hva ønsker du å legge til? Svar enten lege, pasient, resept eller legemiddel: ");
                    String objektAaLeggeTil = scanner.nextLine();

                    // hvis man ønsker å legge til lege
                    if (objektAaLeggeTil.equals("lege")) {

                        System.out.print("Hva heter legen: ");
                        String navnLege = scanner.nextLine();
                        System.out.print("Er legen en legespesialist (ja/nei): ");
                        String svar = scanner.nextLine();

                        if (svar.equals("ja")) {

                            System.out.print("Hva er kontrollkoden: ");
                            String kontrollkode = scanner.nextLine();
                            legesystem.leggTilLegespesialist(navnLege, kontrollkode);

                        } else if (svar.equals("nei")) {

                            legesystem.leggTilLege(navnLege);

                        } else {

                            System.out.println("\nUgyldig valg. Svar enten ja eller nei."); 
                            continue;
                        }

                    // hvis man ønsker å legge til pasient
                    } else if (objektAaLeggeTil.equals("pasient")) {

                        System.out.print("Hva heter pasienten: ");
                        String navnPasient = scanner.nextLine();
                        System.out.print("Hva er fødselsnummeret til pasienten: ");
                        String fnr = scanner.nextLine();
                        legesystem.leggTilPasient(navnPasient, fnr);

                    // hvis man ønsker å legge til legemiddel
                    } else if (objektAaLeggeTil.equals("legemiddel")) {

                        System.out.print("\nHva slags legemiddel? Svar enten vanlig, narkotisk eller vanedannende: ");
                        String typeLegemiddel = scanner.nextLine();

                        if (typeLegemiddel.equals("vanlig")) {

                            System.out.print("\nSkriv navn, pris (heltall) og virkestoff i mg etter hverandre, separert med kommaer slik: navn, pris, virkestoff. Desimaltall skrives med punktum: ");
                            String[] legemiddelInformasjon = scanner.nextLine().split(","); 

                            for (int i = 0; i < legemiddelInformasjon.length; i++) {
                                legemiddelInformasjon[i] = legemiddelInformasjon[i].replaceAll("\\s", ""); // denne fjerner all mellomrom i strengen
                            }

                            String navn = legemiddelInformasjon[0];
                            int pris;
                            double virkestoff;

                            try {
                                pris = Integer.parseInt(legemiddelInformasjon[1]);
                                virkestoff = Double.parseDouble(legemiddelInformasjon[2]);

                            } catch (NumberFormatException e) {
                                System.out.println("\nPris må oppggis i heltall, og virkestoff i heltall eller desimaltall. En eller flere av disse var feil.");
                                continue;

                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("\nAll nødvendig informasjon ble ikke oppgitt. Prøv på nytt.");
                                continue;
                            }
                            
                            legesystem.leggTilVanligLegemiddel(navn, pris, virkestoff);

                        } else if (typeLegemiddel.equals("narkotisk")) {

                            System.out.print("\nSkriv navn, pris (heltall), virkestoff i mg og narkotisk styrke etter hverandre, separert med kommaer slik: navn, pris, virkestoff, styrke. Desimaltall skrives med punktum: ");
                            String[] legemiddelInformasjon = scanner.nextLine().split(","); 

                            for (int i = 0; i < legemiddelInformasjon.length; i++) {
                                legemiddelInformasjon[i] = legemiddelInformasjon[i].replaceAll("\\s", ""); // denne fjerner all mellomrom i strengen
                            }

                            String navn = legemiddelInformasjon[0];
                            int pris;
                            double virkestoff;
                            int styrke;

                            try {
                                pris = Integer.parseInt(legemiddelInformasjon[1]);
                                virkestoff = Double.parseDouble(legemiddelInformasjon[2]);
                                styrke = Integer.parseInt(legemiddelInformasjon[3]);

                            } catch (NumberFormatException e) {
                                System.out.println("\nPris må oppggis i heltall, virkestoff i heltall eller desimaltall, og nakrotisk styrke i heltall. En eller flere av disse var feil.");
                                continue;

                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("\nAll nødvendig informasjon ble ikke oppgitt. Prøv på nytt.");
                                continue;
                            }

                            legesystem.leggTilNarkotiskLegemiddel(navn, pris, virkestoff, styrke);

                        } else if (typeLegemiddel.equals("vanedannende")) {

                            System.out.print("\nSkriv navn, pris (heltall), virkestoff i mg og vanedannende styrke etter hverandre, separert med kommaer slik: navn, pris, virkestoff, styrke. Desimaltall skrives med punktum: ");
                            String[] legemiddelInformasjon = scanner.nextLine().split(","); 

                            for (int i = 0; i < legemiddelInformasjon.length; i++) {
                                legemiddelInformasjon[i] = legemiddelInformasjon[i].replaceAll("\\s", ""); // denne fjerner all mellomrom i strengen
                            }

                            String navn = legemiddelInformasjon[0];
                            int pris;
                            double virkestoff;
                            int styrke;

                            try {
                                pris = Integer.parseInt(legemiddelInformasjon[1]);
                                virkestoff = Double.parseDouble(legemiddelInformasjon[2]);
                                styrke = Integer.parseInt(legemiddelInformasjon[3]);

                            } catch (NumberFormatException e) {
                                System.out.println("\nPris må oppggis i heltall, virkestoff i heltall eller desimaltall, og vanedannende styrke i heltall. En eller flere av disse var feil.");
                                continue;

                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("\nAll nødvendig informasjon ble ikke oppgitt. Prøv på nytt.");
                                continue;
                            }

                            legesystem.leggTilVanedannendeLegemiddel(navn, pris, virkestoff, styrke);

                        } else {
                            
                            System.out.println("\nUgyldig valg. Velg mellom vanlig, narkotisk eller vanedannende.");
                        }

                    // hvis man ønsker å legge til resept
                    } else if (objektAaLeggeTil.equals("resept")) {

                        int reit = -1; // placeholder-verdi

                        System.out.print("\nHva slags resept? Svar enten hvit, militaer, p eller blaa: ");
                        String typeResept = scanner.nextLine();

                        if (!(typeResept.equals("hvit") || typeResept.equals("militaer") || typeResept.equals("p") || typeResept.equals("blaa"))) {
                            System.out.println("\nUgydlig valg. Velg mellom hvit, militaer, p eller blaa.");
                            continue;
                        }

                        legesystem.printLeger();
                        System.out.print("Hvilken av legene over skal skrive ut resepten? Svar med hele navnet, inkludert tittel hvis det står: ");
                        String legeNavn = scanner.nextLine();

                        legesystem.printPasienter();
                        System.out.print("Hvilken av pasientene over skal respeten gjelde for? Svar med pasient-ID: ");
                        int pasientId;

                        try {
                            pasientId = Integer.parseInt(scanner.nextLine());

                        } catch (NumberFormatException e) {
                            System.out.println("\nPasient-ID må være et heltall.");
                            continue;
                        }
                        

                        legesystem.printLegemidler();
                        System.out.print("Hvilken av legemidlene over skal resepten skrive for? Svar med legemiddel-ID: ");
                        int legemiddelId;

                        try {
                            legemiddelId = Integer.parseInt(scanner.nextLine());

                        } catch (NumberFormatException e) {
                            System.out.println("Legemiddel-ID må være et heltall.");
                            continue;
                        }
                        

                        if (!typeResept.equals("militaer")) {
                            System.out.print("\nHvor mange reit skal resepten ha: ");

                            try {
                                reit = Integer.parseInt(scanner.nextLine());

                            } catch (NumberFormatException e) {
                                System.out.println("Antall reit må være et heltall.");
                            }
                            
                        }

                        legesystem.leggTilResept(typeResept, legeNavn, pasientId, legemiddelId, reit);
                    
                    // hvis man har oppgitt noe annet enn lege, pasient, resept eller legemiddel
                    } else {

                        System.out.println("\nUgyldig valg. Vennligst velg mellom lege, pasient, resept eller legemiddel.");
                        continue;
                    }

                    break;

                case 3:
                    System.out.println("Hvilken pasient vil du se resepter for?");
                    legesystem.printPasienter();
                    System.out.print("Velg pasient ved å skrive pasient-ID her: ");
                    int pasientId;
                    try {
                        pasientId = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("\nPasient-ID må være et heltall.");
                        continue;
                    }
                    

                    System.out.println("Hvilken respept vil du bruke?\n");
                    boolean fantMinstEnResept = false;

                    // for-løkka printer bare resepter for valgt pasient
                    for (Resept resept : legesystem.resepterListe) {

                        if (resept.hentPasient().hentId() == pasientId) {

                            System.out.print(resept.hentLegemiddel().hentNavn().substring(0, 1).toUpperCase() + resept.hentLegemiddel().hentNavn().substring(1)); // denne printer legemiddelnavnet med stor forbokstav 
                            System.out.print(", reit: " + resept.hentReit());
                            System.out.println(", resept-ID: " + resept.reseptId + "\n");
                            fantMinstEnResept = true;
                            
                        }
                    }

                    if (!(fantMinstEnResept)) {
                        System.out.println("Pasienten med pasient-ID " + pasientId + " har ingen resepter, eller det finnes ingen pasient med denne pasient-IDen.");
                        break;
                    }

                    System.out.print("Velg resept ved å skrive inn resept ID-en her: ");
                    int reseptId;
                    try {
                        reseptId = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Resept-ID må være et heltall");
                        continue;
                    }
                    
                    legesystem.brukResept(pasientId, reseptId);
                    break;

                case 4:
                    System.out.println("Hvilken statistikk vil du se?\n");
                    System.out.println("Valg 1: Totalt antall utskrevne resepter på vanedannende legemidler");
                    System.out.println("Valg 2: Totalt antall utskrevne resepter på narkotiske legemidler");
                    System.out.println("Valg 3: Statistikk om mulig misbruk av narkotika\n");
                    System.out.print("Svar med tallene (1-3): ");

                    int statistikkValg;

                    try {
                        statistikkValg = Integer.parseInt(scanner.nextLine());

                        if (statistikkValg < 1 || statistikkValg > 3) {
                            System.out.println("\nUgyldig valg. Velg mellom 1-3.");
                            continue;
                        } 

                    } catch (NumberFormatException e) {
                        System.out.println("Du må velge et heltall mellom 1-3.\n");
                        continue;
                    }

                    System.out.println();

                    switch (statistikkValg) {

                        case 1:
                            int antallUtskrevneVanedannende = 0; 

                            for (Resept resept : legesystem.resepterListe) {

                                if (resept.hentLegemiddel() instanceof Vanedannende) {
                                    antallUtskrevneVanedannende ++;
                                }
                            }

                            System.out.println("Totalt antall utskrevne resepter på vanedannene legemidler: " + antallUtskrevneVanedannende);

                            break;
                        
                        case 2:
                            int antallUtskrevneNarkotisk = 0; 

                            for (Resept resept : legesystem.resepterListe) {

                                if (resept.hentLegemiddel() instanceof Narkotisk) {
                                    antallUtskrevneNarkotisk ++;
                                }
                            }

                            System.out.println("Totalt antall utskrevne resepter på narkotiske legemidler: " + antallUtskrevneNarkotisk);

                            break;

                        case 3:
                            System.out.println("Legestatistikk:\n");

                            for (Lege lege : legesystem.legerListe) {

                                int skrevneNarkotiske = 0;

                                for (Resept resept : lege.hentUtskrevneResepter()) {
                                    if (resept.hentLegemiddel() instanceof Narkotisk) {
                                        skrevneNarkotiske ++;
                                    }
                                }
                                if (skrevneNarkotiske != 0) {
                                    System.out.println(lege.hentNavn() + " har skrevet ut " + skrevneNarkotiske + " narkotiske legemidler");
                                }
                                
                            }

                            System.out.println("\nPasientstatistikk:\n");
                            
                            for (Pasient pasient : legesystem.pasienterListe) {
                                int antallNarkotiske = 0;
                                
                                for (Resept resept : pasient.hentResepter()) {
                                    if (resept.hentLegemiddel() instanceof Narkotisk) {
                                        antallNarkotiske ++;
                                    }
                                }
                                if (antallNarkotiske != 0) {
                                    System.out.println("Pasienten " + pasient.hentNavn() + " har fått utskrevet " + antallNarkotiske + " narkotiske legemidler på sitt navn");
                                }
                            }

                            break;
                    }

                    break;

                case 5:
                    System.out.println("Hva skal filnavnet være (f.eks. filnavn.txt): ");
                    String filnavn = scanner.nextLine();
                    legesystem.skrivTilFil(filnavn);

                    break;

                case 6:
                    System.out.println("Program avsluttet. Ha en fin dag!");
                    return;
            }

            System.out.print("\nØnsker du å gjøre noe mer i dag? ja/nei: ");
            fortsett = scanner.nextLine();
            System.out.println();

        }
        
        scanner.close();
        System.out.println("Program avsluttet. Ha en fin dag!");
    }
}
