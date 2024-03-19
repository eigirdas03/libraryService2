# Library web service
* Upgraded library web service `https://github.com/eigirdas03/libraryService` that uses plants shop service `https://github.com/KarolisRazma/plants_shop`
* Books about plants can now be linked with corresponding plants objects
* Also, all plants are able to be viewed and a new plant can be added

## How to run
1. `git clone https://github.com/eigirdas03/libraryService2`
2. `cd libraryService2`
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

## New resources (from plants shop service)

### Plant (sellers field is ignored)
* id (auto generated)
* name
* type

## Commands

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


## New commands

### GET

* http://localhost:80/plants - get all plants
* http://localhost:80/books/1/plants - get all plants linked to specific book


### POST

* http://localhost:80/plants - create a plant
* http://localhost:80/books/1/plants/2 - link plant(id 2) to book(id 1)


### DELETE

* http://localhost:80/books/1/plants/2 - unlink plant(id 2) from book(id 1)


## Library/Book POST/PUT body structure (id is ignored)

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

## Plant POST body structure (id and sellers(will be set to empty list) fields are ignored)
```json
{
    "name": "plant1",
    "type": "type1"
}
```
