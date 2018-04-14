package tdt4140.gr1812.app.backend.dataclasses;

//Ish copy of class from front-end. Created to simplify backend processes. 

public class Sport {


    private String sportsName;
    
    
    public Sport(String Sportsname) {
        this.sportsName = Sportsname;
    }
    
    public String getSport() {
        return this.sportsName;
    }

}
