import React from 'react';
import { render } from 'react-dom';
import Level from 'scenes/Level/Level';
import { Router, Route, browserHistory } from 'react-router'


render(
    <Router history={browserHistory}>
        <Route path="/" component={Level}/>
    </Router>,
    document.getElementById("root"));

