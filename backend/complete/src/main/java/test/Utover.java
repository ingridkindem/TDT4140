package test;

public class Utover {

    int utoverID;
    String navn;
    int alder;

    public Utover(int utoverID, int alder, String navn){
        this.utoverID=utoverID;
        this.alder=alder;
        this.navn=navn;
    }


    public int getUtoverID() {
        return utoverID;
    }

    public void setUtoverID(int utoverID) {
        this.utoverID = utoverID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }


}
