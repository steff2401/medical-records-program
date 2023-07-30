abstract class Legemiddel {

    public final String navn;
    public static int antOpprettetLegemidler = 0;
    public final int id = antOpprettetLegemidler; // ID nummer starter fra 0 og folger derfor antall opprettet legemidler
    public int pris;
    public final double virkestoffMg;

    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        virkestoffMg = virkestoff;
        antOpprettetLegemidler ++;
    }

    public int hentPris(){
        return this.pris;
    }

    public void settNyPris(int nyPris){
        this.pris = nyPris;
    }

    public String hentNavn() {
        return this.navn;
    }

    public int hentId() {
        return id;
    }

    public String toString(){
        
        String linje1 = "Navn: " + this.navn;
        String linje2 = "Legemiddel-ID: " + this.id;
        String linje3 = "Pris: " + this.pris;
        String linje4 = "Virkestoff i mg: " + this.virkestoffMg;

        return  linje1 + "\n" + linje2 + "\n" + linje3 + "\n" + linje4;
    }
}