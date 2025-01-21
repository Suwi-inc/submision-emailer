# submision-emailer
A simple email service that sends emails to a given  email address

# How to run 
### 1. Clone the github repo to your prefered directory using
```
git clone https://github.com/Suwi-inc/submision-emailer.git
``` 
### 2. Navigate to the root directory of the project
```
cd email 
```

### 3. Create an application.yml config file with the following feilds in ``src/main/recources``
```
server:
    port: 
spring:
    application:
        name: 
    mail:
        host: {smtp server address} # smtp.gmail.com for gmail
        port:  {smtp server port} # 587  for gmail
        properties:
            mail:
                smtp:
                    auth: true
                    starttls:
                        enable: true
        username: {sender email address}
        password: {email app password} # check how to genrate one here : (https://support.google.com/mail/answer/185833?hl=en) 
target: {destination email}
```
### 4. Build the image and Run the application using
```
docker build -t image-name
docker run -p 8080:8080 image-name
```
### 5. Send a tst request with 
```
curl.exe -X POST "http://localhost:8080/api/send" ^
    -d "name=John Doe" ^
    -d "email=johndoe@example.com" ^
    -d "message=Hello, this is a test message!"

```
