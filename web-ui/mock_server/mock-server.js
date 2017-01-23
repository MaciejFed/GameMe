var jsonServer = require('json-server');
var server = jsonServer.create();
var router = jsonServer.router('./mock_server/gameMe.json');
var middlewares = jsonServer.defaults();

server.use(middlewares);
server.use(jsonServer.bodyParser);


server.use(function (req, res, next) {
    if (req.method === 'POST') {
        console.log(req.body.path);
        res.jsonp(req.body.path)
    }

    next()
});


server.use(router);
server.listen(3000, function () {
    console.log('JSON Server is running')
});
