abstract class Resept {

    protected final Legemiddel legemiddel;
    protected final Lege utskrivendeLege;
    protected static int antOpprettetResepter = 0;
    protected final int reseptId = antOpprettetResepter; // ID nummer starter fra 0 og folger derfor antall opprettet resepter
    protected final Pasient pasient;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        antOpprettetResepter ++;
    }

    public int hentId(){
        return reseptId;
    }

    public Legemiddel hentLegemiddel(){
        return this.legemiddel;
    }

    public Lege hentLege(){
        return this.utskrivendeLege;
    }

    public Pasient hentPasient(){
        return this.pasient; 
    }

    public int hentReit(){
        return this.reit;
    }

    public boolean bruk(){
        if (this.reit > 0){

            this.reit --; // bruk en reit hvis man har flere igjen
            return true;

        } else return false;
        
    }

    public abstract String typeResept();

    public abstract String farge();

    public abstract int prisAaBetale();

    public String toString(){
        String linje1 = "Resept for: " + this.hentLegemiddel().hentNavn();
        String linje2 = "Utskrivende lege: " + this.hentLege().hentNavn(); 
        String linje3 = "Resept ID: " + reseptId;
        String linje4 = "Pasient: " + this.pasient;
        String linje5 = "Antall reit: " + this.reit;
        String linje6 = "Pris: " + this.prisAaBetale();

        return linje1 + "\n" + linje2 + "\n" + linje3 + "\n" + linje4 + "\n" + linje5 + "\n" + linje6;
    }
}
