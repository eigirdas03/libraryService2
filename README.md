# Library web service
Simple library web service that implements CRUD operations 

## How to run
1. `git clone https://github.com/eigirdas03/libraryService`
2. `cd libraryService`
3. `docker-compose up --build` or `docker-compose up --build -d` (detached mode)
4. open browser and go to http://localhost:80/swagger
   

## Resources

### Library
* id (auto generated)
* name
* address
* opened (year)

### Book

* id (auto generated)
* author (name + surname)
* title
* published (year)

## Available commands

### GET
* http://localhost:80/libraries - get all libraries
* http://localhost:80/libraries/1 - get specific library
* http://localhost:80/books - get all books
* http://localhost:80/books/1 - get specific book
* http://localhost:80/libraries/1/books - get all books in specific library

### POST
* http://localhost:80/libraries - create a library
* http://localhost:80/books - create a book
* http://localhost:80/libraries/2/books/3 - add book(id 3) to library(id 2)

### PUT
* http://localhost:80/libraries/1 - update specific library
* http://localhost:80/books/1 - update specific book

### DELETE
* http://localhost:80/libraries/1 - delete specific library
* http://localhost:80/books/1 - delete specific book
* http://localhost:80/libraries/1/books/2 - remove book(id 2) from library(id 1)


## POST/PUT body structure (id will be ignored)

### Library
```json
{
    "name": "library1",
    "address": "address 1-2",
    "opened": "1990"
}
```

### Book
```json
{
    "author": "name1 surname1",
    "title": "title1",
    "published": "1991"
}
```

