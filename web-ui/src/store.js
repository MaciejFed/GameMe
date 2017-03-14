import { applyMiddleware, createStore} from 'redux';

import thunk from 'redux-thunk';
import promise from 'redux-promise';
import createLogger from 'redux-logger';

import reducers from './reducers'

const middleware = applyMiddleware(thunk, promise, createLogger());

export default createStore(reducers, middleware);