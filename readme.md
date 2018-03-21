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

## Guidlines for GitLab

This is a short introduction to how we organize our issues and connect them to our commits.

1. We categorize our issues by using different labels.
2. The different user-stories are divided into a variable number of issues. An issue for a user-story is marked with a label named “User-story X”, where X is that particular user-story number.
3. Additionally, all the issues for the different user-stories are labeled with the associated sprint number as well as a milestone for the sprint.
4. Issues which are not part of a user-story are labeled with a name that explains their purpose. E.g “Unplanned items”, “Feedback user test 1”.
5. Some issues are probably part of a sprint, but not of a particular user-story so they are only labeled with the sprint number.

All our issues are hashtagged with a specific number, e.g #14, #16. These numbers are used to connect specific commits to a specific issue. So, when we write out commit messages we hashtag the particular number in the message. As a consequence of this hashtag, the commit message is also shown as a comment on the issue. When you click the link in the comment (in the issue) you get more information about what commit was actually made. 

When an issue is completed we make a merge request into the develop branch with a message containing the steps taken to complete the issue. After the merge request is made, it is the test managers responsibility to merge the current branch into the develop branch. After the merging is completed, we can close the issue.



--- 

## Code style

We decided to write in the [google java style](https://google.github.io/styleguide/javaguide.html)

---

## Tech/framwork used

* [Spring Boot](https://projects.spring.io/spring-boot/)
* [Junit](https://junit.org/junit5/)
* [TestFx](https://github.com/TestFX/TestFX)
* [JSON](https://github.com/stleary/JSON-java)
* [Spring](https://spring.io/)

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
	        (...)}		
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

---

## License

MIT © 




