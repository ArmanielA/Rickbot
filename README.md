# Rickbot
Created and powered by [Discord JavaAPI](https://github.com/DV8FromTheWorld/JDA#dependencies).

## Build upon Java 11 and Maven 3.6.3
Use `mvn package` to package the app and build the bat files.

## Deployed to Heroku
Set the target file in .gitignore and create a Procfile to reference the [worker](https://devcenter.heroku.com/articles/run-non-web-java-processes-on-heroku). 
Then in Heroku use `heroku ps:scale worker=1`.

## Calling the bot
simply call `!Rickbot` in the channel and get an infamous quote from Rick and Morty. 
