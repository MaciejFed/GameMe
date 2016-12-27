#!/bin/bash

echo "Starting InteliJ..."
#there insert path to InteliJ eg "/c/Program Files/IntelliJ IDEA 15.0.1/bin"
cd "fill"
./idea64.exe &

echo "Starting Mongo server..."
mongod &

echo "Starting Robomongo..."
#there insert path to Robomongo eg "/c/Program Files/Robomongo 0.9.0-RC9"
cd "fill"
./Robomongo.exe

read -p "Press any key..."
exit 0