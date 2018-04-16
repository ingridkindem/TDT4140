# Work out application for Lier IL

**Version 1.1.1**

We shall develop a program for Lier IL, which collects data about the work outs, 
to the athletes in this team. The collected data is shown in a way that both the athlete
and the coach can get an overview of the intensity, quality and develop of the work outs.
The athletes can also comment on his or hers own work out, and chose whether or not the work out
should be visible for the coach. 

---

## Motivation

In the subject TDT4140 at NTNU we will work together as a team to create an application. We could choose what kind of 
application we wanted to make, and our team agreed upon making a workout-application for Lier IL. 
We are working with Scrum in our team, and the main motivation is to make a working application together. 

---

## Guidelines for GitLab

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
* [EclEmma](https://www.eclemma.org/)

### Built with

* [Maven](https://maven.apache.org/)

---

## EclEmma

We have used EclEmma to get information about over code covarege. Our goal was to keep the covarege abouve 70 %. 


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

## Installation guide

This is what you need to have installed to run our application:  

For Mac, Windows and Linus; download and install [java](https://www.java.com/en/download/help/download_options.xml)
* Click on your Operation System
* Choose "How do I install java on "*your operation system*"
* Follow the step by step guide to get the full experience

Furthermore, you have to be able to run a [.jar](https://www.wikihow.com/Run-a-.Jar-Java-File) file. This should work just fine without any further ado, but click on "*.jar*" if you are in trouble.

To make sure that the dependencies are correct, install [maven](https://maven.apache.org/install.html)

This project is build with the IDE [Eclipse](https://wiki.eclipse.org/Eclipse_Installer), but you can also use the IDE [IntelliJ](https://www.jetbrains.com/idea/). 


---

## How to run our application

1. Download an IDE, for example [IntelliJ](https://www.jetbrains.com/idea/)
2. In use of IntelliJ, choose import project from the welcome-menu
3. Choose the "tdt-4140-gr1812", 
4. Choose import project from external modul and choose maven and click next, and next again
5. ... and next again. 
6. Then click finished 
7. Navigate to the file tdt4041-gr1812/app.ui/src/main/java/tdt4140.gr1812.app.ui/FxApp
8. Then press run from the top navigation menu, and select run...

Or

1. Download the IDE [Eclipse](https://wiki.eclipse.org/Eclipse_Installer) 
2. For installation, run the file you downloaded, and choose library tdt4180
3. Click install
4. Click accept
5. Click accept again
6. Click launch
6. Choose your perferred workspace
7. Click launch
8. Then you should see project explorer in the top left corner
9. Right click, and choose import
10. Open the folder called general, and choose "existing projects into workspace"
11. Hit next
12. Hit "browse" next to select "root directory"
13. Navigate to where you downloaded the project, usually in downloads
14. Select the folder you downloaded, and hit "open". This should be named "12-master(...)"
15. Click finish
16. Then you should see a folder named 12! 
17. Navigate to tdt4140-gr1812 and right click on app.core
18. Select import 
19. Select "existing projects into workspace" and hit next, and then finish. 
20. Repeat step 18 and 19 for app.ui. 
21. In app.ui navigate to "app.ui/src/main/java/tdt4140.gr1812.app.ui"
22. Run FxApp.java as Java Application


Or

* Run file: tdt4140-gr1812/executable/treningsapp1.1.0.jar 

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




