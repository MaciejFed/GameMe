sudo rm -r gameme

git clone http://github.com/maciejfed/gameme.git

cd gameme

sudo docker kill $(docker ps -q)
sudo docker rm $(docker images -q)
sudo docker rmi $(docker images -q -f dangling=true)
sudo docker rmi $(docker images -q)

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
sudo docker run --name GameMeUI -d -p 80:80 -p 3000:3000 --link GameMeService maciejfed/gameme-ui

echo success!
