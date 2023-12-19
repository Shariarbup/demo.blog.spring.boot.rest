# demo.blog
 Blog Application (Technology used- Spring boot, Spring REST, Spring security)
 Technology used:
 - Spring web
 - Spring Security
 - Spring DevTools
 - Spring Mariadb
 - lombok
 - model mapper
 - Spring boot JPA
 - Spring boot starter validation API
 - JJWT(Json Web Token)
 - Spring boot starter mail
 - Spring doc Open API
 - Spring boot starter validation
 - Apache poi
 - Apache poi ooxml
 - Jasper Report

 
 Database:
 -MySql,

# Tool
- Intellij Idea
- VS code
- MYSQL WORKBENCH
- Notepad
- Chrome

# Username and Password
- Two role automatically created when first running the project
- One user need to created for login into the system
- please use the encoded password for the first use
- Encode password for xyz is $2a$10$uKUQJrjX7RxJbzv3cGzupOzpsw.Ztpp.L3tQ2.opAzdfOm52qrU0O

# Swagger path
http://localhost:9090/swagger-ui/index.html

# Installing Maven in windows
1. Download the zip file
2. Past the apache folder to c drive (folder name like: apache-maven-3.9.5)
3. After pasting copy the folder url (like: C:\Program Files\apache-maven-3.9.5\bin)
4. Then Go to environment variable
5. Create two variable name "MAVEN_HOME", "M2_HOME" and for value past C:\Program Files\apache-maven-3.9.5
6. Edit the path variable and add two variable there "MAVEN_HOME\bin" and "M2_HOME\bin"
7. Without this "M2_HOME\bin" windows may be not recognize the maven

# Twilio.com(for otp)
1. Used email: itmasjoy@gmail.com
2. Used mobile number: 01968385155



# Tutorial

- Alt + Entr press korle local variable assign hoi
- ctrl + F full project e kono text search korar jonne use
- ctrl + N full project e file search korar jonne use hoi
- ctrl + G - ekta file jekono line e jaoar jonne use hoi

# How to create github app

- Owned by: @Shariarbup
- App ID: 669676
- Client ID: Iv1.93bd50a5ac2e228d
- public link: https://github.com/apps/shariar-demo-coding
- client secret: a894a42a88b1cb08b5af5672779be74570167d75

- Go to Setting -> then go to developer setting(see in the lower portion) -> create github app
- provide home url and call back url - http://localhost:8080 (in my case)
- uncheck which is not necessary in my case(webhook)

# How to create google app

- Client ID: 254969945458-fbmtbh649piqc128rps1fv7dfpksqaei.apps.googleusercontent.com
- Client Secret: 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

- Go- https://console.cloud.google.com/
- Create an app
- Then got Rest API and Services -> Create credentials -> Oauth client id -> Applicatio Type(desktop App)


# Jasper studio

Dowload community Link- https://community.jaspersoft.com/files/file/19-jaspersoft%C2%AE-studio-community-edition/


# Jasper report different firld:
- Parameter - $P{} -> simple input to the subreport (<parameter_key>(string), value(object)), you can pass this to subreport or datasource
- Fields - $F{} -> Fields is the datasource object that are passed to the report, Fields are also contain multiple entries
, ex: column entries in a table
- Variable - $V{} -> Variables are not data passed to report that they are created in the report to hold aggregated values
They are predefined variables in the jasper report
- Internationalization - $R{} -> This holds the key of the resource bundle to support internationalization. Based on the key and locale 
provided it gets corresponding value from resource bundle