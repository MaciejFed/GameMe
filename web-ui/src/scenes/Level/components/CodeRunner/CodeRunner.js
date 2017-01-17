import React from 'react';
import CodeEditor from './components/CodeEditor/CodeEditor';
import SavedCodeList from './components/SavedCodeList/SavedCodeList';
import styles from './coderunner.css';


export default class CodeRunner extends React.Component{
    constructor(props){
        super(props);

    }

    render(){
        return (
            <div className={styles.codeRunnerWrapper}>
                <CodeEditor/>
                <SavedCodeList/>
            </div>
        )
    }

}

