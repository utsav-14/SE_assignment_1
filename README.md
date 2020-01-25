# Software Engineering Assignment 1 - Code Cleanup.
## Objective:
    To apply good coding practices to develop code that is easy to read, extend and maintain.
 
## Disclaimer:
    The code used for cleanup originally belongs to: https://github.com/unclebob/javaargs 

### Setup:
    To run the code on your machine:
    
      * Clone this repo 
      * install ant by running 'sudo apt-get install ant'
      * then go to the folder where you have cloned this repo
      * run 'ant compile'
      * run 'ant jar'
      * run 'java -cp build/jar/args.jar com.cleancoder.args.ArgsMain'
      
      Schema:
       - String    - Boolean arg.
       - String*   - String arg.
       - String#   - Integer arg.
       - String##  - double arg.
       - String[*] - one element of a string array.
      
      Example schema: (logging,directory*,port#,time##,authors[*])
      Coresponding command line: "-logging -directory Home -port 8081 -time 3.2 -authors uncleBob -authors utsav

      
### For the tests:
        * Run the command given below from the root folder of this repo
        * 'java -cp "lib/junit-4.13.jar:lib/hamcrest-core-1.3.jar:build/jar/args.jar" ./test/com/cleancoder/args/ArgsTest.java testCreateWithNoSchemaOrArguments'
    
### Changelog:
        Enlisted below are the changes made to the original code and the reasoning behind them:
        
        * Variable renaming : Several variables were renamed to better describe the purpose they serve.
        
        * Wrapper classes in place of primitive data types : Objects of wrapper classes are used to mark clear distinction
        between acceptable and unacceptable values.
        
        * Factory class for instance creation : The responsibility of instance creation has now been delegated to a Factory class
        thereby reducing the duties of Args.java class.
        
        * Optional class usage : In place of checking for null values, Optional class is used to better handle missing values 
        and enhance readability.
        
        * String parameters support : Command line parameters now support Strings which can describe the function of the 
        parameters better than single characters.  
        
        * More modularity in try/catch blocks : The exception handling code is made more modular using methods intsead
        of big blocks of code.
        