package tdt4140.gr1812.app.core.models.coachModel;

import java.util.ArrayList;
import java.util.List;

import tdt4140.gr1812.app.core.dataClasses.Athlete;

public class CoachModel {
	
	
	public static List<String> getSportsForCoach(String coach){
		List myList = new ArrayList();
		
		myList.add("Basket");
		myList.add("Fotball");
		
		return myList;
	}
	
	public static List<Athlete> getAthletesForSport(String sport){
		List myList = new ArrayList();
		
		Athlete aAthlete = new Athlete("46643025", "Lars erik", "Fagernæs");
		
		myList.add(aAthlete);
		
		
		return myList;
		
	}  
	
}
