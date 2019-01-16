Feature: Users Menu

@book
Scenario: User view dummy book
When user is call "/dummy-book" page
Then  user receives response status code of 200
And user get dummy data

@book
Scenario: User view all book
When user is call "/get-all-book" page
Then  user receives response status code of 200
And user get all book

@library
Scenario Outline: User add book into shelf
When user is call "/add-book?bookid=<bookId>&shelfid=<shelfId>" page
Then user receives response status code of 200
And book id <bookId> is added in shelf

Examples:
|bookId|shelfId|
|11|1|
|12|2|