import React from 'react';
import RichCodeEditor from './scenes/RichCodeEditor';
import CodeEditor from './scenes/CodeEditor/CodeEditor';
import FunctionHelper from './components/FunctionHelper/FunctionHelper';
import { APPEND_CODE } from './codeEditorActions';
import styles from './coderunner.css';
import {connect} from "react-redux";

@connect((store) => {
    return {
        functions: store.levelReducer.level.functions,
        isAnimating: store.codeEditorReducer.codeEditor.isAnimating
    }
})
export default class CodeRunner extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div className="codeRunnerWrapper" onClick={() => this.editor.getWrappedInstance().focus()}>
                { this.props.isAnimating ? <CodeEditor/> : <RichCodeEditor ref={instance => { this.editor = instance; }} /> }
                <FunctionHelper functions={this.props.functions} onFunctionClicked={this.appendCode.bind(this)} levelNumber={this.props.levelNumber}/>
            </div>
        )
    }

    appendCode(code){
        APPEND_CODE(this.props.dispatch, code + "(); ");
    }

}

