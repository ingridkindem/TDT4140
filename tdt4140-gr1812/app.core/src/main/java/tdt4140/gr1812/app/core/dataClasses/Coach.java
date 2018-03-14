package tdt4140.gr1812.app.core.dataClasses;

public class Coach {
    
    private String name;
    private int age;
    private String sport;
    
    public Coach(String name, int age, String sport) {
        this.name=name;
        this.age=age;
        this.sport=sport;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name.matches("[a-zA-Z ]+ ")==true) {
            this.name = name;
        }
        
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age>=0 && age<150) {
            this.age = age;
        }
       
    }
    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    
    

}
