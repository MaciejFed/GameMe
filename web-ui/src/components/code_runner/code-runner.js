import React from 'react'
import styles from './code-runner-style.css'
const WRONG_FUNCTION = '#884d29';
const CORRECT_FUNCTION = '#88994a';
const DICTIONARY = ["code", "for", "while", "if", "int"];

export default class CodeRunner extends React.Component{
    constructor(props){
        super(props);
        this.inputArea = null;
        this.state = {
            text: ''
        };
    }

    render(){
        return (
            <div id="code-container" className={styles.codeRunner} onClick={this.focusOnInput.bind(this)}>
                <div className={styles.codeTextContainer}>
                    {this.state.text}
                </div>
                <input ref={(input) => this.inputArea = input} onChange={(text) => this.changeText(text)} style={{opacity: 0}}/>
                <div className={styles.compileButtonContainer}>
                    <button className={styles.compileButton}>Compile</button>
                </div>
            </div>
        )
    }

    focusOnInput(){
        this.inputArea.focus();
    }

    changeText(text){
        this.setState({
            text: CodeRunner.format(text.target.value)
        });
    }

    static format(text){
        return text.split(" ").map(CodeRunner.style);
    }

    static style(word){
        let wordStyle = CORRECT_FUNCTION;
        if(DICTIONARY.indexOf(word) === -1)
            wordStyle = WRONG_FUNCTION;

        return <span style={{color: wordStyle, fontSize: 22}}>{word} </span>
    };
}

