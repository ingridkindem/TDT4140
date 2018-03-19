package tdt4140.gr1812.app.core.dataClasses;

public class Coach {
    
    private String name;
    private int age;
    private Sport sport;
    
    
    //Constructor to create a Coach. Init with name and sport.
    public Coach(String name, Sport sport) {
        this.name=name;
        this.sport=sport;
    }
        
    // Set age
    public void setAge(int age) {
        if (age<0 || age>150) {
            throw new IllegalArgumentException("Illegal age");
        }
        this.age = age;

    }
    
    //Getters for attributes
    public String getName() {
        return name;
    }
        
    
    public int getAge() {
        return age;
    }

    public Sport getSport() {
        return sport;
    }

    
    

}
