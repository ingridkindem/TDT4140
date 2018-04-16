package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;

public class coachModelTest {
	
	CoachModel model = new CoachModel(new Coach("", new Sport("fotball")));

    @Test
    public void getSportForCoachTest() {
    		System.out.println(CoachModel.getSportForCoach("46643025") + " is the sport");
        assert(CoachModel.getSportForCoach("46643025").equals("basket") );
        assert(CoachModel.getSportForCoach("46643025").equals("fotball") == false);
        assert(CoachModel.getSportForCoach("12345").equals("Not a registered coach"));
    }
    
    @Test
    public void getAthletesForSportTest() {
    		assertFalse(CoachModel.getAthletesForSport("fotball") == null);
    }
    
    @Test
    public void testGetAthletesFullName() {
    		assertFalse(CoachModel.getAthletesFullName("12345678", "langrenn")==null);
    }
    
}