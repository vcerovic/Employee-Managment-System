<h1 align="center">Employee Management System</h1>


![Employee Management System Banner](https://i.ibb.co/4N9S8NM/emp-home.png)

<br>

## Content
  1. [Technologies](#Technologies)
  2. [User guide](#Guide)
  3. [Functionality](#Functionality)
  4. [Validation](#Validation)


<br>
<br>

## <a name="Technologies"></a> Technologies
  * Java
  * Hibernate
  * HQL for queries
  * MySQL
  * Swing

<br>
<br>

## <a name="Guide"></a> User guide
1. You need to execute a script for creating the database that is in the file `src/main/java/com/veljko/project/mysql/company.sql`
2. Modify the database connection configuration to suit your settings. All settings can be found in the file `src/main/resources/hibernate.cfg.xml`. 
3. Finally you can start application by running `main()` method inside `src/main/java/com/veljko/project/App/` file.


<br>
<br>

## <a name="Functionality"></a> Functionality

<br>

>**1.** View all employees in the database.

<br>

![All Employees Page](https://i.ibb.co/gvjcXPG/all-emp.gif)

<br>

>**2.** Create, delete and edit employees.

<br>

![Manage Employees Page](https://i.ibb.co/dL3Grgy/manage-emp.gif)

<br>

>**3.** Search for employees based on name, age and salary.

<br>


![Filter Employees Page](https://i.ibb.co/VW8NBZx/filter-emp.gif)

<br>

## <a name="Validation"></a> Validation

<br>

The application validates the following:
* Whether the appropriate content is entered in the text field.
* Whether the appropriate data type is entered in the text field.
* Whether the text content is shorter than 40 characters.
* Whether any of the checkboxes on the employee filter page are ticked.
* Is there a user in the database with the entered ID.

<br>
