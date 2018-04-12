package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.dataClasses.LoginModel;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;

public class CoachModelTest {
    
    CoachModel coachModel;
    Sport sport;
    Coach coach;
    
    @Before 
    public void setUp(){
        this.sport = new Sport("basket");
        this.coach = new Coach("Larry",this.sport);
        this.coachModel = new CoachModel(this.coach);
    }
    
    @Test
    public void testgetAthletesFullName() {
        try {
            //Checks if we get a name
            this.coachModel.getAthletesFullName("11111111", "basket");
            assert true;
        } catch (Exception e){
            assert false;
        }
        
    }
    
    @Test
    public void testgetAthletesForSport() {
        try {
            this.coachModel.getAthletesForSport("basket");
            assert true;
        } catch (Exception e) {
            assert false;
        }
        
    }
    
    @Test
    public void testgetSportForCoach() {
        try {
            this.coachModel.getSportForCoach("12345678");
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }
}
