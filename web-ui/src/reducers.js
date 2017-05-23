import { combineReducers } from 'redux';
import { routerReducer as routing } from 'react-router-redux';
import levelReducer from './scenes/Level/levelReducer'
import codeEditorReducer from './scenes/Level/scenes/CodeRunner/codeEditorReducer'
import boardReducer from './scenes/Level/scenes/Board/boardReducer'

export default combineReducers({
    levelReducer, codeEditorReducer, boardReducer, routing
});