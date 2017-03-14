import React from 'react';
import CodeEditor from './scenes/CodeEditor/CodeEditor';
import FunctionHelper from './components/FunctionHelper/FunctionHelper';
import { APPEND_CODE } from './scenes/CodeEditor/codeEditorActions';
import styles from './coderunner.css';
import {connect} from "react-redux";

@connect((store) => {
    return {
        functions: store.levelReducer.level.functions,
    }
})
export default class CodeRunner extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div className={styles.codeRunnerWrapper}>
                <CodeEditor />
                <FunctionHelper functions={this.props.functions} onFunctionClicked={this.appendCode.bind(this)} levelNumber={this.props.levelNumber}/>
            </div>
        )
    }

    appendCode(code){
        APPEND_CODE(this.props.dispatch, code + "(); ");
    }

}

