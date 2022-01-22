AGOS(Aide à la Gestion et à l'Organisation des Soutenances) is a Web Application that offers centralised tool for managing theses defences,a new simplified and clear evaluation system and a smooth allocation of time slots.


###clone the project :

```
git clone https://github.com/AgosApp/agos-app.git
```


# agos-Front

##Prerequisites 
- NodeJS
- npm package manager (installed by default with NodeJS)
- AngularCLI 13.0.3

##Run the Project :

1. Access Angular project :

```
# angular folder
cd agos-ng-app
```


2. Install Dependencies :

```
# javascript dependencies
npm install
```

3. Run the Project :

```
# run project
ng serve --open
```

The project will run by default on http://localhost:4200/

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


Contact
AGOS Team - @Agos - agos@gmail.com

Project Link: https://github.com/AgosApp/agos-app
