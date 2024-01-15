# ecohub-webapp
Ecohub Web Application using Spring Boot


## Build and run

* Clean and package jar file
```
./mvnw clean package 
```

* Run webapp
```
./run.bat
```

* Run in development mode
```
./mvnw spring-boot:run
```

* Open application in default browser
```
 http://localhost:9090/ecohub
```

## Postgresql DB in docker

* Navigate to `db` folder.
* To create a new DB, just execute `create-db.bat`. All DDLs from `init.sql` will be applied.
* To connect to local DB in docker, execute `./connect-db.bat`

## Start/stop DB

* Check db state in docker using: `docker ps`
You should see your docker container up and running.

* Start DB `docker start ecohub-db`

* Stop DB  `docker stop ecohub-db`

# Banner generation

Banner generated using https://manytools.org/hacker-tools/ascii-banner/ with `ANSI Shadow` style.