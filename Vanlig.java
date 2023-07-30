class Vanlig extends Legemiddel {

  public Vanlig(String navn, int pris, double virkestoffMg){
    super(navn, pris, virkestoffMg);
  }
  
  @Override
  public String toString() {
    return super.toString() + "\n";
  }
}
