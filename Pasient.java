public class Pasient {
    
    private String navn;
    private String fodselsnr;
    private static int idNr = 0;
    public int id;
    private IndeksertListe<Resept> resepter;

    public Pasient(String navn, String fodselsnr) {
        this.navn = navn;
        this.fodselsnr = fodselsnr;
        resepter = new IndeksertListe<>();
        id = idNr;
        idNr ++;
    }

    public void leggTilResept(Resept resept) {
        resepter.leggTil(resept);
    }

    public IndeksertListe<Resept> hentResepter() {
        return resepter;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentId() {
        return id;
    }

    public String hentFodselsnr() {
        return fodselsnr;
    }

    public String toString() {
        return navn + " (pasient-ID: " + id + ")" + ", fnr: " + fodselsnr;
    }
}
