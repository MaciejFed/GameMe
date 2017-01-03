import React from 'react';
import { render } from 'react-dom';
import Stage from 'components/stage/stage';
import { Router, Route, browserHistory } from 'react-router'


render(
    <Router history={browserHistory}>
        <Route path="/" component={Stage}/>
    </Router>,
    document.getElementById("root"));

