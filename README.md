# Library web service
* Upgraded library web service https://github.com/eigirdas03/libraryService that uses plants shop service https://github.com/KarolisRazma/plants_shop
* Books about plants can now be linked with corresponding plant objects


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
* books[] (book resource array, in POST/PUT body book IDs are written here, book object with that id is automatically added to the array)

### Book **(updated)**
* id (auto generated)
* author (name + surname)
* title
* published (year)
* **plants[]**

### Plant **(new)**
* id (auto generated)
* name
* type


## Available commands

### GET
* http://localhost:80/libraries - get all libraries
* http://localhost:80/libraries/1 - get specific library
* http://localhost:80/books - get all books **(and plants linked to them)**
* http://localhost:80/books/1 - get specific book **(and plants linked to it)**

### POST
* http://localhost:80/libraries - create a library
* http://localhost:80/books - create a book **(and plants if specified)**

### PUT
* http://localhost:80/libraries/1 - update specific library
* http://localhost:80/books/1 - update specific book **(also, update plants if they exist or  create them if they do not exist)**
* http://localhost:80/libraries/1/books - update specific library "books" array

### DELETE
* http://localhost:80/libraries/1 - delete specific library
* http://localhost:80/books/1 - delete specific book **(and plants linked to it)**
* http://localhost:80/libraries/1/books/2 - remove book(id 2) from library(id 1)


## POST/PUT body structures

### Library
* library id will be ignored
* "books" array cannot be empty in PUT commands

#### POST/PUT

```json
{
    "name": "library1",
    "address": "address 1-2",
    "opened": "1990",
    "books": [1, 2]
}
```

#### PUT (http://localhost:80/libraries/1/books)
```json
{
    "books": [1, 3]
}
```

### Book **(updated)**
* book id will be ignored
* in POST plant IDs are ignored
* in PUT plant IDs can be specified and if a plant with that id exists it will be updated, otherwise (if a book with that id does not exist or no id is specified) a new plant will be created (it will have an auto generated id)

#### POST

```json
{
    "author": "name1 surname1",
    "title": "title1",
    "published": "1991",
    "plants": 
    [
        {
            "name": "plant1",
            "type": "type1"
        },
        {
            "name": "plant2",
            "type": "type2"
        }
    ]
}
```

#### PUT
```json
{
    "author": "name1 surname1",
    "title": "title1",
    "published": "1991",
    "plants": 
    [
        {
            "name": "plant1",
            "type": "type1",
            "id": 1
        },
        {
            "name": "plant2",
            "type": "type2"
        }
    ]
}
```