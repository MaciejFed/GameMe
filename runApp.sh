sudo rm -r gameme

git clone http://github.com/maciejfed/gameme.git

cd gameme

sudo docker rm -f GameMeDB
sudodocker rm -f GameMeService
sudo docker rm -f GameMeUI

echo building the GameMeDB...

cd db
sudo docker build -t maciejfed/gameme-db .

echo building the GameMeService...

cd ..
cd service 
sudo docker build -t maciejfed/gameme-service .

echo building the GameMeUI...

cd ..
cd web-ui

sudo docker build -t maciejfed/gameme-ui .

echo starting dockers...

sudo docker run --name GameMeDB -d -p 27017:27017 maciejfed/gameme-db
sudo docker run --name GameMeService -d -p 8080:8080 --link GameMeDB maciejfed/gameme-service
sudo docker run --name GameMeUI -d -p 80:80 --link GameMeService maciejfed/gameme-ui

echo success!
