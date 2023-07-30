class Narkotisk extends Legemiddel {

    public final int styrke;

    public Narkotisk(String navn, int pris, double virkestoffMg, int styrke) {
        super(navn, pris, virkestoffMg);
        this.styrke = styrke;
    }
    
    @Override
    public String toString(){
        // bruker Legemiddel sin toString-metode til aa printe alt utenom narkotisk styrke
        return super.toString() + "\nNarkotisk styrke: " + this.styrke + "\n";
    }
}
