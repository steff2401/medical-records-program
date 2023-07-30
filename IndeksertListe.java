class IndeksertListe<E> extends Lenkeliste<E> {

    
    public void leggTil(int pos, E x) {

        // hvis ugyldig index kast exception
        if (pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        // hvis man ikke ønsker å legge til et element helt i slutten eller helt i starten av lista, og lista ikke er tom
        if (pos != stoerrelse() && pos != 0 && stoerrelse() != 0) { 

            int index = 0;
            Node peker = start;
            Node nodenFoer = null;
            Node nodenEtter = null;

            // loop til noden som ligger på index = pos
            while (index <= pos) {

                // ta også vare på noden på indexen før der vi skal sette inn
                if (index == pos - 1) { 
                    nodenFoer = peker;
                }

                // ta også vare på noden som ligger på indexen vi skal sette den nye node inn i
                if (index == pos) {
                    nodenEtter = peker;
                }

                peker = peker.neste;
                index ++;
            } 
            
            Node nyNode = new Node(x);
            nodenFoer.neste = nyNode; // noden før må peke på den nye noden
            nyNode.neste = nodenEtter; // den nye noden må peke på noden etter
            

        } else if (stoerrelse() == 0) { // hvis lista er tom

            start = new Node(x);

        } else if (pos == stoerrelse() && stoerrelse() != 0) { // hvis man ønsker å legge til et element helt på enden av en ikke-tom liste
            
            Node peker = start;
            
            while (peker.neste != null) { 
                peker = peker.neste;
            } // når while løkka er ferdig er peker-variabelen den hittil siste noden

            peker.neste = new Node(x);

        } else if (pos == 0 && stoerrelse() != 0) { // hvis man ønsker å legge til et element helt på starten av en ikke-tom liste
            
            Node forrigeStart = start;
            Node nyStart = new Node(x);
            start = nyStart;
            nyStart.neste = forrigeStart;
        }
    }

    public void sett(int pos, E x) {

        if (pos < 0 || pos > (stoerrelse() - 1)) {
            throw new UgyldigListeindeks(pos);
        }

        if (pos == 0) { // hvis man ønsker å endre første element
            Node forrigeStart = start;
            start = new Node(x);
            start.neste = forrigeStart.neste;
            return;
        }

        Node peker = start;
        int index = 0;
        Node denneSkalErstattes = null;
        Node nodenFoer = null;

        while (index < pos) {

            if (index == pos - 1) {
                nodenFoer = peker;
            }

            if (index == pos) {
                denneSkalErstattes = peker;
            }

            peker = peker.neste;
            index ++;
        } // når while løkka er ferdig er peker-variabelen den noden som ligger på spurt posisjon/index

        denneSkalErstattes = new Node(x); // erstatter den som skal erstattes med ny
        denneSkalErstattes.neste = peker.neste; // den nye må peke på samme som den gamle pekte på
        nodenFoer.neste = denneSkalErstattes; // noden før må peke på den nye
    }

    public E hent(int pos) {

        if (pos < 0 || pos > (stoerrelse() - 1)) {
            throw new UgyldigListeindeks(pos);
        }

        Node peker = start;
        int index = 0;

        while (index < pos) {
            peker = peker.neste;
            index ++;
        }

        return peker.data;
    }

    public E fjern(int pos) {
        
        if (pos < 0 || pos > (stoerrelse() - 1)) {
            throw new UgyldigListeindeks(pos);
        }

        if (pos == 0) { // hvis man ønsker å fjerne første element
            Node forrigeStart = start;
            start = start.neste;
            return forrigeStart.data;
        }

        Node peker = start;
        int index = 0;
        Node nodenFoer = null;

        while (index < pos) {
            
            if (index == pos - 1) {
                nodenFoer = peker;
            }

            peker = peker.neste;
            index ++;
        }

        Node denneSkalFjernes = peker;
        nodenFoer.neste = denneSkalFjernes.neste; // for å fjerne en node må bare noden før den peke på samme som den gjør
        return denneSkalFjernes.data; 

     }
}
