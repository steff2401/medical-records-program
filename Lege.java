public class Lege implements Comparable<Lege> {
    
    protected final String navn;
    protected IndeksertListe<Resept> utskrevneResepter;

    public Lege(String navn){
        this.navn = navn;
        utskrevneResepter = new IndeksertListe<>();
    }

    public String hentNavn(){
        return this.navn;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        // bare spesialister kan skrive ut narkotiske legemidler
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        
        // narkotiske legemidler kan bare skrives ut på blaa resept
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(legemiddel, this);
        }

        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvitResept);
        pasient.leggTilResept(hvitResept);
        return hvitResept;
    }

    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(legemiddel, this);
        }

        MilResept milResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milResept);
        pasient.leggTilResept(milResept);
        return milResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(legemiddel, this);
        }

        PResept PResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(PResept);
        pasient.leggTilResept(PResept);
        return PResept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);
        return blaaResept;
    }


    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    @Override
    public int compareTo(Lege annenLege) {
        return this.navn.compareTo(annenLege.navn);
    }

    @Override
    public String toString(){
        
        return navn;
    }
}
