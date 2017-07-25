# This is a project working with Intellij IDEA, Spring Boot, MyBatis, Swagger2, Log4j2, Junit, Redis, Mockito and RESTful API.
--------------------------------------------------------------------------------------
## Deployment
  - Create database using the sql below:
    ```
    DROP DATABASE IF EXISTS `test`;
    CREATE DATABASE test;
    USE test;
    CREATE TABLE `user` (
        `id` bigint(16) AUTO_INCREMENT NOT NULL,
        `name` varchar(255) NOT NULL,
        `password` varchar(255) NOT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    CREATE TABLE `message` (
        `id` bigint(16) AUTO_INCREMENT NOT NULL,
        `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
        `msg` varchar(255) NOT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    ```
  - Deploy Redis Server on localhost:6379.
  - Open the project In Intellij IDEA and change mysql settings and tomcat port by editing application.properties.
  - Run the application.
  - Open http://localhost/swagger-ui.html (default settings).

## API List

Visit http://localhost/swagger-ui.html for more information.

| API Name | URL |
| --- | --- |
| GetUsers | http://localhost/user |
| InsertUser | http://localhost/user |
| DeleteUser | http://localhost/user/{id} |
| GetUserById | http://localhost/user/{id} |
| UpdateUser | http://localhost/user/{id} |

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
