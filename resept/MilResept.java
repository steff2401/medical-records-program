class MilResept extends HvitResept {

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3); // alle millitaerresepter utskrives med 3 reit
    }

    @Override
    public int prisAaBetale(){
        return 0;
    }

    @Override
    public String typeResept() {
        return "militaer";
    }

    @Override
    public String toString(){

        String linje1 = "Type resept: Millitaer (hvit)";
        String linje2 = "Resept for: " + this.hentLegemiddel().hentNavn();
        String linje3 = "Utskrivende lege: " + this.hentLege().hentNavn(); 
        String linje4 = "Resept ID: " + reseptId;
        String linje5 = "Pasient: " + this.pasient;
        String linje6 = "Antall reit: " + this.reit;
        String linje7 = "Pris: " + this.prisAaBetale();

        return linje1 + "\n" + linje2 + "\n" + linje3 + "\n" + linje4 + "\n" + linje5 + "\n" + linje6 + "\n" + linje7;
    }
}
