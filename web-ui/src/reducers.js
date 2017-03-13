import { combineReducers } from 'redux';
import levelReducer from './scenes/Level/levelReducer'
import codeEditorReducer from './scenes/Level/scenes/CodeRunner/scenes/CodeEditor/codeEditorReducer'
import boardReducer from './scenes/Level/scenes/Board/boardReducer'

export default combineReducers({
    levelReducer, codeEditorReducer, boardReducer
});