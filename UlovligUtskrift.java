class UlovligUtskrift extends Exception {

    UlovligUtskrift (Lege l, Legemiddel lm) {
	super("Legen " + l.hentNavn() + " har ikke lov til aa skrive ut " +
	      lm.hentNavn());
    }

    UlovligUtskrift(Legemiddel lm, Lege l) {
        super("Legen " + l.hentNavn() + " kan bare skrive ut narkotiske legemidler på blå resept! Det narkotiske legemiddelet " + lm.hentNavn() + " ble ikke lagt til resepten.");
    }
}