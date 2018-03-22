package treningsdagbok.core;

public abstract class Ovelse implements ActiveDomainObject {

    protected int ovelseID;
    protected String ovelseNavn;
    protected int gruppeID;

    protected int IDCounter = 1;

    public int getIDCounter() {
        return IDCounter;
    }

    public int getOvelseID() {
        return ovelseID;
    }

    public String getOvelseNavn() {
        return ovelseNavn;
    }

    public void setOvelseNavn(String ovelseNavn) {
        this.ovelseNavn = ovelseNavn;
    }
}
