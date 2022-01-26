# agos-Front
N.B: begin with agos-Back instructions for good testing 

Prerequisites:
-Node js
-Angular

Usage
- Open agos-app directory
-Open agos-ng-app directory in a text editor 
-Run the command: npm install
-Run the command: ng serve
-The frontend application is running on http://localhost:4200
- To authenticate as a professor : username : fcouchot 
- To authenticate as a student : username : mboufelj
N.B : the password for all users is : 1234
 

# agos-Back

About the project : 
It's a REST API developed using Spring Boot 2.5.6 Framework and Spring MVC Core.
This API offers many end-points that will be consumed by th frontend Application.

Prerequisites 
- Java 15.0.2
- Maven
- XXAMP 


Usage
- Open agos-app directory in a text editor (Intellij IDEA is recommended)
- open agos-api which contains api code spring project
- Reload your maven dependencies
- Run MySQL and Apache servers using XXAMP Control Panel v3.3.0
- Create a database in MySQL with the name agos
- Checkout your application.properties file and up to date if necessary 
- Run your application
- You can visualize all existing endPoints by a Swagger using the link http://localhost:8080/swagger-ui.html 

Contributing 
Fork the repo and create a pull request. 
You can do this by following the steps bellow
0. Fork the Project
1. Open agos-app directory
2. Switch to the branch develop using the command : git checkout develop
3. To work with git-flow extension, use the command : git flow init 
4. Create your Feature Branch (git flow feature start feature_branch)
4.Commit your Changes (git commit -m 'Add some AmazingFeature')
N.B : When committing, please respect the messages : (feat(api): the-title-of-the-fonctionality)
5.Push to the Branch (git push origin HEAD)
6.Open a Pull Request in th develop branch
5. Merge with develop after the approval of all contributors

#AGOS Docker

Prerequisites
-Docker
-Docker Desktop ( To visualize the running containers)

To run docker containers
Follow the steps bellow:
1-Open agos-app directory
2-Open agos-api directory
3-Run the command: docker-compose up
N.B: Be sure that all necessary ports are available

#Contact
AGOS Team - @Agos - agos@gmail.com

Project Link: https://github.com/AgosApp/agos-app
