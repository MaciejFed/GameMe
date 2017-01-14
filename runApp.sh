sudo rm -r gameme

git clone http://github.com/maciejfed/gameme.git

cd gameme

sudo docker rm -f GameMeDB
sudo docker rm -f GameMeService
sudo docker rm -f GameMeUI
sudo docker kill -f $(sudo docker ps -q)
sudo docker rm -f $(sudo docker images -q)
sudo docker rmi -f $(sudo docker images -q -f dangling=true)
sudo docker rmi -f $(sudo docker images -q)

echo building the GameMeDB...

cd db
sudo docker build -t maciejfed/gameme-db . >> GameMeDB.log

echo building the GameMeService...

cd ..
cd service 
sudo docker build -t maciejfed/gameme-service . >> GameMeService.log

echo building the GameMeUI...

cd ..
cd web-ui

sudo docker build -t maciejfed/gameme-ui .  >> GameMeUI.log

echo starting dockers...

sudo docker run --name GameMeDB -d -p 27017:27017 maciejfed/gameme-db
sudo docker run --name GameMeService -d -p 8080:8080 --link GameMeDB maciejfed/gameme-service
sudo docker run --name GameMeUI -d -p 80:80 -p 3000:3000 --link GameMeService maciejfed/gameme-ui

sudo docker logs -f GameMeDB & >> GameMeDB.log
sudo docker logs -f GameMeService & >> GameMeService.log
sudo docker logs -f GameMeUI & >> GameMeUI.log

echo success!
