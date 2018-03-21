# Work out application for Lier IL

**Version 1.0.1**

We shall develop a program for Lier IL, which collects data about the work outs, 
to the athletes in this team. The collected data is shown in a way that both the athlete
and the coach can get an overview of the intensity, quality and develop of the work outs.
The athletes can also comment on his or hers own work out, and chose whether or not the work out
should be visible for the coach. 

---

### Motivation

We wanted to make a fun application that every member of the team enjoyed working with. 

---

## Code style

We decided to write in the [google java style](https://google.github.io/styleguide/javaguide.html)

---

## Tech/framwork used

### Built with

* [Maven](https://maven.apache.org/)

--- 

## Code Example

Here is an example of how we write our code. 

´´´
	public Sport(String sportsName) {
		this.sportsName = sportsName;
	}
  
	public String getSport() {
		return this.sportsName; 
	}
´´´
---

## Tests

This is how we tests our code. We write our test first (TDD), and after the code is written,
we test it with the jUnit unit test. 

Here is an example of how we test the code above. 

´´´
	@Test
	public void returnMySport() {
		
		Sport tester = new Sport("Basket"); 
		assertEquals("Basket", tester.getSport());
	}
´´´

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






