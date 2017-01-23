import React from 'react';
import CodeEditor from './components/CodeEditor/CodeEditor';
import FunctionHelper from './components/FunctionHelper/FunctionHelper';
import styles from './coderunner.css';


export default class CodeRunner extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        console.log(this.props.startCode);
        return (
            <div className={styles.codeRunnerWrapper}>
                <CodeEditor ref="codeEditor" introductionText={this.props.introductionText} startCode={this.props.startCode}/>
                <FunctionHelper onFunctionClicked={this.appendCode.bind(this)} showExample={this.props.showExample}/>
            </div>
        )
    }

    appendCode(code){
        this.refs.codeEditor.appendText(code + "(); ");
    }

}

