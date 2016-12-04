import React from 'react';
import { render } from 'react-dom';
import Stage from 'components/stage';
import { Router, Route, Link } from 'react-router'

render(
    <Router>
        <Route path="/level/:levelNumber" component={Stage}/>
    </Router>,
    document.getElementById('stage'));