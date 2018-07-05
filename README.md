# spring-demo

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

> Spring Customer DEMO

## Table of Contents

- [Install](#install)
- [Usage](#usage)
- [Maintainers](#maintainers)
- [License](#license)

## Install
#### Requirement
- Apache Maven 3.3.9 or later
- MySQL Database

#### Setup
- Extract zip file into separate folder

## Usage

> Go to extract folder
> Run with command below
```shell
mvn clean install
```
> Provided command
```shell
java -jar target/spring.demo-1.0.jar
```

> Properties file
In order to change datasource parameter, refer to this file:
/src/main/resources/application.properties

MySQL Setting:
spring.datasource.url = jdbc:mysql://localhost:3306/spring-demo?useSSL=false
spring.datasource.username = root

Folder Upload Setting:
upload.location=/Users/mohdtaufik/Documents/upload/

> Run in browser
```shell
http://localhost:8080/customer
```

## Maintainers

[@taoef1k](https://github.com/taoef1k)


## License

MIT © 2018 Taufik
