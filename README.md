# Getting Started

## Start MongoDB in Docker

### 1) Create new Docker network:
     `docker network create my-mongo-cluster`

### 2) Start containers using the next commands:

`docker run \
-p 27017:27017 \
--name mongo1 \
--net my-mongo-cluster \
-d \
mongo mongod --replSet my-mongo-set`

`docker run \
-p 27016:27017 \
--name mongo2 \
--net my-mongo-cluster \
-d \
mongo mongod --replSet my-mongo-set`

`docker run \
-p 27015:27017 \
--name mongo3 \
--net my-mongo-cluster \
-d \
mongo mongod --replSet my-mongo-set`

### 3) Connect to the mongo container:
     `docker exec -it mongo1 mongo`

### 4) Execute the next commands: 
    `db = (new Mongo('localhost:27017')).getDB('app1')`
    `config = {"_id" : "my-mongo-set","members" : [{"_id" : 0,"host" : "mongo1:27017"},{"_id" : 1,"host" : "mongo2:27017"},{"_id" : 2,"host" : "mongo3:27017"}]}`
    `rs.initiate(config)`
 
### 5) Create database and collections:
    `use app1`
    `db.createCollection("activity")`
    `db.createCollection("history")`

 