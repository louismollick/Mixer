# Mixer
ECSE 428 - Software Engineering Practice Project

## Development Database Installation Instructions

There's two ways of installing/using Postgres in your local dev environment:
### On local 
 1. Download Postgres server:
 * Mac: https://postgresapp.com/  
 Then open the Desktop app interface, click "Start".
 * Windows: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads  

 2. Open Terminal on Mac or 'psql' command line application on Windows (that was just installed, should be in Start).

 3. Input: `psql -h localhost -p 5432 -U postgres`

### Docker container
  1. Download Docker: 
  * Mac: https://hub.docker.com/editions/community/docker-ce-desktop-mac/
  * Windows: https://hub.docker.com/editions/community/docker-ce-desktop-windows/
  
  2. Then in your terminal:  
 `docker run --name postgres-ecse428 -e POSTGRES_HOST_AUTH_METHOD=trust -p 5432:5432 -d postgres`  

 3. Then: `docker exec -ti postgres-ecse428 psql -U postgres`

#### Common instructions

4. Input into psql prompt:  `CREATE DATABASE ecse428;`

5. `GRANT ALL PRIVILEGES ON "ecse428" TO postgres;`

6. Run the Spring backend, it should connect!

7. Optional: you can use psql to view the database tables yourself with `SELECT * FROM tablename;`






