FROM node:argon

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json /usr/src/app/
COPY index.html /usr/src/app
COPY webpack.config.js /usr/src/app
COPY src /usr/src/app/src/

RUN npm install
RUN npm install -g webpack-dev-server

EXPOSE 80

CMD [ "npm", "start" ]