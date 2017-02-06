import React from 'react';
import CodeEditor from './components/CodeEditor/CodeEditor';
import FunctionHelper from './components/FunctionHelper/FunctionHelper';
import CodeRunnerClient from './../../../../service/api/code_runner_client'
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
        this.codeRunnerClient = new CodeRunnerClient();
    }

    render(){
        return (
            <div className={styles.codeRunnerWrapper}>
                <CodeEditor ref="codeEditor" introductionText={this.props.introductionText} startCode={this.props.startCode}/>
                <FunctionHelper functions={this.props.functions} onFunctionClicked={this.appendCode.bind(this)} showExample={this.props.showExample} levelNumber={this.props.levelNumber}/>
            </div>
        )
    }

    appendCode(code){
        this.refs.codeEditor.appendText(code + "(); ");
    }

    getDirectionValues(){
        let code = this.refs.codeEditor.getCode();
        return this.codeRunnerClient.loadRoad(1, code, [0, 0])
    }

}

