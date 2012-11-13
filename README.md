Simple Feedback Site
========
  
Building  
---------  
1. Download and unpacking Maven  
2. Clone project repository (Example: git clone https://github.com/Leks13/feedback.git
)  
3. Build project with Maven.  
 3.1. cd feedback  
 3.2. path_to_folber/apache-maven-3.0.4/bin/mvn package  
  
Project building as target/feedback-1.0.war  
Please move to "webapps" directory in your Tomcat Server.  
  
  
Setting  
-------  
Default settings:  
-Server PostgreSQL - localhost  
-Port - 5432  
-Base - db  
-User - example  
-Password - example  

The path to the configuration file to change the settings - */tmp/config.cfg*  
ip=1.2.3.4  
port=1234  
db=db_name  
user=login  
password=password  
  
Create a database and user in your server!
------------------------------------------
  
Use it!  
-------  
http://localhost:8080/feedback-1.0/   

