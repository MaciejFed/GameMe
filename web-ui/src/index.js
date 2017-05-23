import React from 'react';
import { render } from 'react-dom';
import {createBrowserHistory} from "history";
import { syncHistoryWithStore } from 'react-router-redux';
import Level from './scenes/Level/Level';
import { Provider } from 'react-redux'
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import store from './store'


syncHistoryWithStore(createBrowserHistory(), store)

    let wrapComponent = function(component){
    return(<Provider store={store}>{component}</Provider>)
};

let levelPath = (
    <BrowserRouter history={ history } >
        <Route path="/*" component={() => wrapComponent(<Level/>)}/>
    </BrowserRouter>
);

render(levelPath,
    document.getElementById("root"));

