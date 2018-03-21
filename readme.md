# Work out application for Lier IL

**Version 1.1.0**

We shall develop a program for Lier IL, which collects data about the work outs, 
to the athletes in this team. The collected data is shown in a way that both the athlete
and the coach can get an overview of the intensity, quality and develop of the work outs.
The athletes can also comment on his or hers own work out, and chose whether or not the work out
should be visible for the coach. 

---

## Motivation

In the subject TDT4180 at NTNU we will work together as a team to create an application. We could choose what kind of 
application we wanted to make, and our team agreed upon making a workout-application for Lier IL. 
We are working with Scrum in our team, and the main motivation is to make a working application together. 

---

## Internal guidlines for GitLab

1. All communication on gitblab should be in English. E. g. commit messages, comments on issues, this readme and so on. If you are in doubt - go English.

2. Every issue related to the project should be solved in a branch named "NameOfISsue"+"#"+"issue number". Notice the camel case in issue name. E. g. your issue is login controller, and your issue number is 23. Your branch name should the be LoginController#23.

3. !!The master branch is sacred and is not to be trifled with!! 

4. We will have two main branches; master and develop. When branching out for your issues, make sure your current branch is develop. Before branching use "git pull" in terminal or pull directly in eclipse, to make sure you have the must updated version of our project.

5. When commiting to your branch, make sure your commit message is descriptive enough for the other group members to understand what you have done, and to some extent how you have done it.

6. When you have completed your commits and feel ready to close the issue. Create a pull request to develop branch. 

7. Each group member is responsible for updating their own issues. When you start working on your issue, move it from to-do to doing. Leave a comment and close the issue when you finish.

8. Avoid acronyms and initialism. Even though your shortening might seem obivious to you, that is not necessarliy the case for the rest of the group.

--- 

## Code style

We decided to write in the [google java style](https://google.github.io/styleguide/javaguide.html)

---

## Tech/framwork used

* [Spring Boot](https://projects.spring.io/spring-boot/)

### Built with

* [Maven](https://maven.apache.org/)

--- 
## API refrences

(...) = Code not included in readme.


How our application interacts with server and database:



* Method creating request to server

        
        public final class BackendConnector {
    	
    	static String baseUrl = "http://larserikfagernaes.com:8000/"; 
    	public static JSONObject makeRequest(HashMap<String, String> dict, Method method, String path)
        (...)
 
* Example usage in one of the applications models

    (...)
    HashMap myMap = new HashMap<String, String>();
	    myMap.put("username", phoneNumber);
	    myMap.put("password", password);

		try {
			JSONObject response = BackendConnector.makeRequest(myMap, Method.POST, "login");
	(...)		
* Server from Spring 

    // Fires up the server from our Spring dependency
    
    @SpringBootApplication
    public class Server {
    
        public static void main(String[] args) {
            SpringApplication.run(Server.class, args);
        }
    }

* Endpoint for request

    @RequestMapping("/login") //mapping to login endpoint
    public String login(@RequestParam("username") String username,
    					@RequestParam("password") String password) {
    (...)
    					   boolean loginResult = ServerLogic.login(username, password);
    (...)					   

* Queries to DB

    public static boolean login(String username, String password) {
    			
        	 MysqlDataSource dataSource = new MysqlDataSource();
             dataSource.setUser("root");
             dataSource.setPassword("password");
             dataSource.setServerName("localhost");
             dataSource.setPort(3306);
             dataSource.setDatabaseName("PU");
             
             String sql = "select * from users where username = ? and password = ?";
    (...)


## Tests

This is how we tests our code. We write our test first (TDD), and after the code is written,
we test it with the jUnit unit test. 

Here is an example of how we test the code above. 


	@Test
	public void returnMySport() {
		
		Sport tester = new Sport("Basket"); 
		assertEquals("Basket", tester.getSport());
	}


---

## How to run our application

* Run file: tdt4140.gr1812.app.ui.FxApp.java

---

## Credits

* Marte Kristinesdatter Bolstad - Scrum Master
* Lars Erik Fagernæs – Test manager 
* Maren Schliekelmann Barth – Developer
* Caroline Lysebo - Developer
* Sigrid Schaanning – Developer
* Håkon Collett Bjørgan – Developer
* Toralf Frich – Developer
* Ingrid Kindem – Developer






