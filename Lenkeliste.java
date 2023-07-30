import java.util.Iterator;

abstract class Lenkeliste<E> implements Liste<E> {

    // indre klasse som gir objekter som inneholder elementet og en peker på neste element og forrige element
    protected class Node {
        Node neste = null;
        Node forrige = null;
        E data;

        Node(E data) {
            this.data = data;
        }
    }

    // indre klasse som gjør lenkelisten itererbar
    protected class LenkelisteIterator implements Iterator<E> {
        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < stoerrelse();
        }

        @Override
        public E next() {

            pos ++;
            int index = 0;
            Node peker = start;

            while (true) {

                if (index == (pos-1)) {
                    return peker.data;
                }
                
                peker = peker.neste;
                index ++;
            }
        }
    }

    // instansvariabler i ytre klasse
    protected Node start = null;
    protected Node slutt = null;

    // returner antall noder/størrelse på lista
    @Override
    public int stoerrelse() {

        int antallNoder = 0;
        Node peker = start;

        while (peker != null) {

            antallNoder ++;
            peker = peker.neste;
        } 
        return antallNoder;
    }

    // legg til element på slutten av lista
    @Override
    public void leggTil(E x) {
        // hvis lista er tom, lag den første noden som inneholder elementet
        if (start == null) {
            start = new Node(x);
            slutt = start;
            return;
        }
        
        // hvis lista ikke er tom, finn siste node og hent dens neste-peker (som er null). 
        Node peker = start;
        while (peker.neste != null) {
            peker = peker.neste;
        } // når while løkka er ferdig er peker-variabelen den hittil siste noden
        
        // lager ny node som inneholder elementet og får den tidligere siste noden til å peke på denne noden.
        Node nyNode = new Node(x);
        peker.neste = nyNode;
        nyNode.forrige = peker;
        slutt = nyNode;
    }
    
    // henter første element i lista
    @Override
    public E hent() {
        
        if (stoerrelse() != 0) {
            return start.data;

        } else throw new UgyldigListeindeks(-1);
        
    }

    // fjerner første element i lista og skyver alle andre elementer 1 plass mot venstre/start
    @Override
    public E fjern() {
        
        if (stoerrelse() != 0) {
            E forsteElement = start.data; // lagrer elementet i den opprinnelige start-noden
            start = start.neste; // overskriver start-noden til å bli neste node

            if (stoerrelse() > 1) {
                start.forrige = null; // fjerner forrige-pekeren siden denne noden er nå den første i lista, så lenge det er flere enn 1 node
            } 
            
            return forsteElement; // returnerer opprinnelig start element

        } else throw new UgyldigListeindeks(-1);
        
    }

    @Override
    public Iterator<E> iterator() {
        return new LenkelisteIterator();
    }

    // returnerer en string på formen [element1, element2, ..., elementn]
    @Override
    public String toString() {
        String string = "[";
        Node peker = start;

        // hvis lista bare har ett element
        if (start.neste == null) {
            return "[" + start.data + "]";
        }

        // ellers loop over alle nodene
        while (peker.neste != null) {
            string += peker.data + ", ";
            peker = peker.neste;

            // denne får med siste node også
            if (peker.neste == null) {
                string += peker.data;
            }
        }
        return string + "]";
    }


}
