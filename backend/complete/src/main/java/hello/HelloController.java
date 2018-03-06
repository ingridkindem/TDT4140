package hello;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import test.JdbcUtoverDAO;
import test.Utover;

@RestController
@RequestMapping("/signup")
public class HelloController {

    @RequestMapping(method = RequestMethod.POST)
    public String index(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("sport") String sport,
                        @RequestParam("firstname") String firstname,
                        @RequestParam("surname") String surname,
                        @RequestParam("maxpulse") String maxpulse,
                        @RequestParam("weight") String weight,
                        @RequestParam("gender") String gender
                        ) {



        //testDbConnection db = new testDbConnection();
        //Utover lars =  db.findByUtoverID(1);

        //System.out.println(lars.getNavn());

        testDbConnection.signup();

        return username + password + sport + firstname + surname + maxpulse + weight + gender;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String randomfuc() {

        //testDbConnection db = new testDbConnection();
        //Utover lars =  db.findByUtoverID(1);

        //System.out.println(lars.getNavn());

        return "DetteKjœrespget" ;
    }
    
}
