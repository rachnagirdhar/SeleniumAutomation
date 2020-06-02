About Project
=============

AmazonAssignment is an automation project created for automating below
problem statement.

Problem Statement:
------------------

1.  The proposed test scenario is for adding item to the cart.

2.  Open [www.amazon.com](http://www.amazon.com/)

3.  Select "Books" from search category dropdown

4.  Enter search key: "Selenium"

5.  Click "Go" button

6.  Click the first search result item title

7.  Verify that product title is correct

8.  Click "Add to Cart" button

9.  Verify confirmation test appears: "item added to Cart"

10. Navigate to Cart page from main menu

11. Verify item is displayed on Shopping Cart list

Project Structure 
=================

Different component used are
----------------------------

**Automation Tool**: Selenium

**Framework** : POM and TestNG frameworks.

**Build Management Tool** -- Maven

**Logs creation-** Log4j

**Reports** -- Extent reports

**src/main/java**- contains all the reusable functions.
-------------------------------------------------------

There are 3 packages in the src/main/java.

### **[com.amazon.base]{.underline}**

it includes a base class having all basic functions that can be used in
all over the project like driver initiation, Capturing screen shots,
reading property file, reading excel File etc.

### **[com.amazon.pages]{.underline}**

it includes all the page classes using POM model . Each class has their
related functions and element in that.

### **[com.amazon.utils]{.underline}**

it includes Constants that are fetched by reading property and Excel
file.

It also includes the web function class having all the common functions
like click on element, wait for an element, enter a text ,select any
option etc.

**src/test/java**- contains all the testcase related functions 
--------------------------------------------------------------

There are 2 packages in that.

### **[com.amazon.productTests]{.underline}**

It includes all the classes having testcases for different scenarios.

### **[com.amazon.test.utils]{.underline}**

It includes listener class that is implementing ITestListner interface
of TestNG to handle results.

**Resources**

It contains 2 folders to provide test data and all browsers exe.

***[Note: This project has all the available exe supported on Mac. To
run on windows please download exe for windows and place it in
Resources/browsers folder of this project.]{.underline}***

***Similarly in src/main/java-\> AmazonBaseClass, we need to give the
.exe extension as well while setting the system property.***

***Example:***

***System.setProperty("webdriver.chrome.driver",chromedriver.exe)***

***To run application in chrome , give browser value as chrome in
property file.***

***For Firefox, make it firefox and for Intenet Explorer make it as
IE.***

1.  Resource folder also contains **log4j2.xml** for logging purpose.

2.  For searching a keyword add the value of **search text** in
    TestData.xlsx

3.  For searching a keyword giving no result add value for **No Result
    text** in Testdata.xlsx

4.  To select an option from category dropdown place value for Search
    option in testData.xlsx

Project Execution
=================

Pre-requisite
-------------

> Software that needs to be installed in your system:

-   JDK (1.8)

-   Maven

-   Eclipse

-   TestNG

> **JDK Setup:**

-   Download JDK from here:
    > <https://www.oracle.com/java/technologies/javase-jdk8-downloads.html>

-   Install jdk to your system

-   Add the path "C:\\Program Files\\Java\\jdk1.8.0\_161\\bin" of your
    > jdk/bin folder in your environment variable path.

> ![A screenshot of a cell phone Description automatically
> generated](media/image1.png){width="5.788194444444445in"
> height="2.1406988188976377in"}

-   Open command prompt and type "java -version" to check setup is done
    > and output will be shown as below

> ![A picture containing black, orange, red, close Description
> automatically generated](media/image2.png){width="5.84634186351706in"
> height="0.7819444444444444in"}

**Maven Setup:**

-   Download maven binary zip from here:
    <https://maven.apache.org/download.cgi>

-   Extract zip to your directory

-   Add the path
    "D:\\Softwares\\apache-maven-3.5.2\\apache-maven-3.5.2-bin\\apache-maven-3.5.2\\bin"
    of your bin folder in your environment variable path

> ![A screenshot of a cell phone Description automatically
> generated](media/image1.png){width="5.788194444444445in"
> height="2.1406988188976377in"}

-   Open command prompt and type "mvn -version" to check setup is done
    and output will be shown as below

By command prompt(Windows)/terminal(Mac)
----------------------------------------

Steps:

1.  Download the project

2.  Open the command prompt.

3.  Goto the path where project has been downloaded till
    AmazonAssignment

4.  Run command mvn clean test

5.  Check the result in Reports/ExtentReports folder of same project

6.  To verify the logs there is Reports/Logs folder

7.  To capture screenshots , there is Reports/Screenshots folder.

By IDE
------

Steps:

1.  Import the Project from the directory where project has been
    downloaded and placed.

2.  Install TestNG plugin from marketplace

3.  Add resource folder in add library under Build Path of project to
    map Log4j2.xml.

4.  Go to testing.xml and right click to run that as TestNG suite.

    1.  

Reports
=======

To verify results:

1.  Check the result in Reports/ExtentReports folder of same project

2.  To verify the logs there is Reports/Logs folder

3.  To capture screenshots , there is Reports/Screenshots folder.
