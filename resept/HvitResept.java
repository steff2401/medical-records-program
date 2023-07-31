class HvitResept extends Resept {
    
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String typeResept() {
        return farge();
    }

    @Override
    public String farge(){
        return "hvit";
    }

    @Override
    public int prisAaBetale() {
        return this.hentLegemiddel().hentPris();
    }

    @Override
    public String toString(){
        return "Type resept: " + "Hvit\n" + super.toString();
    }
}
