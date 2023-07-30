class Spesialist extends Lege implements Godkjenningsfritak {

    protected String kontrollkode;

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollkode(){
        return this.kontrollkode;
    }

    @Override
    public String toString(){

        String linje2 = "kontrollkode: " + this.kontrollkode;

        return navn + " (spesialist), " + linje2;
    }
}
