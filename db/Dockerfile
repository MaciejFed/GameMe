FROM mongo

COPY initData.json /initData.json
CMD mongoimport --host mongodb --db GameMeDB --collection gameMap --type json --file /initData.json --jsonArray

CMD ["mongod"]