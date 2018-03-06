package test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.UtoverDAO;
import test.Utover;

public class App {

    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        UtoverDAO UtoverDAO = (UtoverDAO) context.getBean("UtoverDAO");
        Utover Utover = new Utover(1, 28,"Håkon");
        UtoverDAO.insert(Utover);

        Utover utover1 = UtoverDAO.findByUtoverID(1);
        System.out.println(utover1);

    }
}

