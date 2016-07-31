docker rm -f GameMeDB
docker rm -f GameMeService
docker rm -f GameMeUI

echo building the GameMeDB...

cd db
docker build -t maciejfed/gameme-db .

echo building the GameMeService...

cd ..
cd service 
docker build -t maciejfed/gameme-service .

echo building the GameMeUI...

cd ..
cd web-ui

docker build -t maciejfed/gameme-ui .

echo starting dockers...

docker run --name GameMeDB -d -p 27017:27017 maciejfed/gameme-db
docker run --name GameMeService -d -p 8080:8080 --link GameMeDB maciejfed/gameme-service
docker run --name GameMeUI -d -p 80:80 --link GameMeService maciejfed/gameme-ui

echo success!
