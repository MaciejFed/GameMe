import React from 'react';
import { render } from 'react-dom';
import Level from './scenes/Level/Level';
import { Provider } from 'react-redux'
import { Router, Route, browserHistory } from 'react-router'
import store from './store'

let reduxWrapper = function(component){
    return(<Provider store={store}>{component}</Provider>)
};


render(<Provider store={store}><Level/></Provider>,
    document.getElementById("root"));

