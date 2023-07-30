class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale(){
        int opprinneligPris = this.hentLegemiddel().hentPris();
        
        if (opprinneligPris >= 108) {
            
            return (opprinneligPris - 108);

        } else return 0; // hvis opprinnelig pris er 108 eller mindre blir det gratis
    }

    @Override
    public String typeResept() {
        return "p";
    }

    @Override
    public String toString(){

        String linje1 = "Type resept: Prevensjon (hvit)";
        String linje2 = "Resept for: " + this.hentLegemiddel().hentNavn();
        String linje3 = "Utskrivende lege: " + this.hentLege().hentNavn();  
        String linje4 = "Resept ID: " + reseptId;
        String linje5 = "Pasient: " + this.pasient;
        String linje6 = "Antall reit: " + this.reit;
        String linje7 = "Pris: " + this.prisAaBetale();

        return linje1 + "\n" + linje2 + "\n" + linje3 + "\n" + linje4 + "\n" + linje5 + "\n" + linje6 + "\n" + linje7;
    }
}
