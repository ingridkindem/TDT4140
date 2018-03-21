package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;
import org.junit.Test;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;

public class coachModelTest {

    
    
//    getSportForCoach(String name){
//      public static List<Athlete> getAthletesForSport(Coach coach){
    @Test
    public void getSportForCoachTest() {
        assert(CoachModel.getSportForCoach("46643025") == "basketball");
        assert(CoachModel.getSportForCoach("46643025") != "fotball");
        assert(CoachModel.getSportForCoach("123") == "Couldn't load sport");
    }
    
    @Test
    public void getAthletesForSportCoachTest(Coach coach) {
        if (coach.getName().equals("Larry")) {
            assert(coach.getSport().equals("basketball"));
            assert(!coach.getSport().equals("fotball"));
        }
        
    }
    
    @Test
    public void getAthletesForSportTest(Coach coach) {
        if (coach.getName().equals("Larry")) {
            assert(CoachModel.getAthletesForSport(coach) != null);
            assert(CoachModel.getAthletesForSport(coach))
        }
    }
    
    @Test
    public void constructorTest(Coach coach) {
        CoachModel modell = new CoachModel(coach);
        assert(modell == coach);
    }
    
    
    

}
