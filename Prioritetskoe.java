class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {

    @Override
    public void leggTil(E x) {

        // hvis lista er tom
        if (stoerrelse() == 0) {
            super.leggTil(x);
            return;
        }

        // hvis elementet vi ønsker å legge til har førsteprioritet
        if (start.data.compareTo(x) > 0) {
            Node nyNode = new Node(x);
            start.forrige = nyNode;
            nyNode.neste = start;
            start = nyNode;
            return;
        }

        // hvis lista ikke er tom og elementet ikke har førsteprioritet 
        Node peker = start;
        while (peker != null) {
            
            if (peker.data.compareTo(x) > 0) {
              
                // hvis lista bare har ett element
                if (stoerrelse() == 1) {
                    Node nyNode = new Node(x);
                    peker.forrige = nyNode; // setter inn ny node som noden før
                    nyNode.neste = peker;
                    nyNode.forrige = start;
                    return;
                }

                Node nodenFoer = peker.forrige; // tar vare på gamle noden før
                Node nyNode = new Node(x);

                nyNode.neste = peker; // får den nye noden til å peke på neste node
                nyNode.forrige = nodenFoer; // ny node må også peke på noden før
                nodenFoer.neste = nyNode; // noden før må peke på den nye noden
                peker.forrige = nyNode; // noden etter må peke på den nye 
                return;
            }
            peker = peker.neste;
        }

        // hvis if-testen i while-løkka aldri slår til har det nye elementet lavest prioritet, og settes bakerst i lista
        super.leggTil(x); 

    }
}
