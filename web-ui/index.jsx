var ReactDOM = require('react-dom');
var Stage = require('./src/stage');
var Router = require('react-router').Router;
var Route = require('react-router').Route;

ReactDOM.render(
    <Router>
        <Route path="/level/:levelNumber" component={Stage}></Route>
    </Router>,
    document.getElementById('stage'));
