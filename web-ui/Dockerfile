FROM node:argon

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json /usr/src/app/
COPY run-app.sh /usr/src/app/
COPY profiles.js /usr/src/app/
COPY index.html /usr/src/app
COPY webpack.config.js /usr/src/app
COPY src /usr/src/app/src/
COPY mock_server /usr/src/app/mock_server/

RUN npm install
RUN npm install -g webpack-dev-server
RUN npm install -g json-server
RUN chmod 777 run-app.sh

EXPOSE 80 3000

ENTRYPOINT ["./run-app.sh", "dev_mock"]