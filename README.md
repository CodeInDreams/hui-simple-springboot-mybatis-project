# This is a project working with Spring Boot, MyBatis, Intellij IDEA and RESTful API.
--------------------------------------------------------------------------------------
## Deploy
  - Create database using the sql below:
    ```
    DROP DATABASE IF EXISTS `test`;
    CREATE DATABASE test;
    USE test
    CREATE TABLE `user` (
      `id` bigint(16) AUTO_INCREMENT NOT NULL,
      `name` varchar(255) DEFAULT NULL,
      `password` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    ```
  - Open the project In Intellij IDEA and change mysql setting by editing application.properties.
  - Run the application.

## Test

##### GetUsers
| URL | [http://localhost/users/] [PlDb] |
|---|---|
| Method | GET |
| Param | null |
| Response | List<User> |
##### GetUserById
| URL | [http://localhost/users/{id}] [PlDb] |
|---|---|
| Method | GET |
| Param | null |
| Response | User |
##### InsertUser 
| URL | [http://localhost/users] [PlDb] |
|---|---|
| Method | POST |
| Param | String name, String password |
| Response | boolean |
##### UpdateUser 
| URL | [http://localhost/users/{id}] [PlDb] |
|---|---|
| Method | PUT |
| Param | String name, String password |
| Response | boolean |
##### DeleteUser
| URL | [http://localhost/users/{id}] [PlDb] |
|---|---|
| Method | DELETE |
| Param | null |
| Response | boolean |

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
