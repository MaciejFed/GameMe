var jsonServer = require('json-server');
var server = jsonServer.create();
var router = jsonServer.router('./mock_server/gameMe.json');
var middlewares = jsonServer.defaults();

server.use(middlewares);
server.use(jsonServer.bodyParser);


server.use(function (req, res, next) {
    setTimeout(function () {
        if (req.method === 'POST') {
            res.jsonp({
                success: true,
                road: [{dX: 1, dY: 0}]}
            )
        }
        next()
    }.bind(this), 1000)
});


server.use(router);
server.listen(3000, function () {
    console.log('JSON Server is running')
});
