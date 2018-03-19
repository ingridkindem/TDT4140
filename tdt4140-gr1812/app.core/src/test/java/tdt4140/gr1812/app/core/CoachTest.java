package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.dataClasses.Sport;


public class CoachTest {
    
    private Coach coach;
    
    private Sport sport;
    
    @Before
    public void setUp() throws Exception {
        this.sport = new Sport("Fotball");
        this.coach=new Coach("Ingrid",sport);
    }
    
    /*@Test
    public void testSetAgeThrowsIllegalArgumentException() {
        try {
            coach.setAge(151);
            System.out.println("Should have thrown illegal arg exp");
            assert false;
        }
        catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            coach.setAge(-1);
            System.out.println("Should have thrown illegal arg exp");
            assert false;
        }
        catch (IllegalArgumentException e) {
            assert true;
        }
    }*/
    
    @Test
    public void testSetAge() {
        coach.setAge(20);
        assertEquals(20,coach.getAge());
    }
    
    @Test
    public void testGetName() {
        assertEquals("Ingrid",coach.getName());
    }
    
    @Test
    public void testGetSport() {
        assertEquals(this.sport,coach.getSport());
    }

    @After
    public void tearDown() throws Exception{
        
    }
}
