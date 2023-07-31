class Vanedannende extends Legemiddel {
    
    public final int styrke;

    public Vanedannende(String navn, int pris, double virkestoffMg, int styrke){
        super(navn, pris, virkestoffMg);
        this.styrke = styrke;
    }

    @Override
    public String toString(){
        // bruker Legemiddel sin toString-metode til aa printe alt utenom vanedannende styrke
        return super.toString() + "\nVanedannende styrke: " + this.styrke + "\n";
    }
}
