package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;
import org.junit.Test;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;

public class coachModelTest {

    
    
//    getSportForCoach(String name){
//      public static List<Athlete> getAthletesForSport(Coach coach){
    @Test
    public void getSportForCoachTest() {
    		System.out.println(CoachModel.getSportForCoach("46643025") + " is the sport");
        assert(CoachModel.getSportForCoach("46643025").equals("basket") );
        assert(CoachModel.getSportForCoach("46643025").equals("fotball") == false);
        assert(CoachModel.getSportForCoach("12345").equals("Not a registered coach"));
    }
    
    @Test
    public void getAthletesForSportCoachTest() {
    		Coach coach = new Coach("Larry", new Sport("basket"));
        if (coach.getName().equals("Larry")) {
            assert(coach.getSport().getSport().equals("basket"));
            assert(!coach.getSport().equals("fotball"));
        }
    }
    
    
    
    
    

}
