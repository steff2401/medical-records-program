class BlaaResept extends Resept {
    
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String typeResept() {
        return farge();
    }

    @Override
    public String farge(){
        return "blaa";
    }

    @Override
    public int prisAaBetale(){
        int nyPris = (int)Math.round(this.hentLegemiddel().hentPris() * 0.25);
        return nyPris;
    }

    @Override
    public String toString(){
        return "Type resept: " + "Blaa\n" + super.toString();
    }
}
