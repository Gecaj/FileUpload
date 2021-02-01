# FileUpload
App for storing files, tagging them with custom tags and searching them by these tags.

## Requirements:
- MongoDB with version at least 4.4 – can be installed from:  https://docs.mongodb.com/manual/administration/install-community/
- Java JDK 8 or newer
- Maven with version 3.2 or higher

## Testing instructions:
- run MongoDB as described here: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/#start-your-mongodb-database
- enter main directory of project and either first build and run it as packaged application or run it using maven plugin as described here: https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-running-your-application.html
- to more easily access REST API you can use swagger-ui which is provided by application and accessible by this url: http://localhost:8080/swagger-ui/

## Database choice:
I was thinking about using relational or non-relational database. Although I’ve never worked with non-relational databases I searched a little and decided that using Document NoSQL database would be a good choice for solving this problem.
Performance of querying data in SQL databases is slower than in NoSQL databases, because Data in relational databases is typically normalized, so queries for a single object or entity requires joining data from multiple tables.  Regarding this task - storing Files with Tags in relational database would require many-to-many relationship and querying such data would require traversing across lot of rows – number of files multiplied by number of tags for each and then looping through tags contained in IN and NOT IN clauses.
Document database like MongoDB on the other hand can store this tag data in single column and perform optimized text queries which searches text for occurrences of some words or restricts to not contain occurrence of other words.

