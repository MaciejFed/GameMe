import React from 'react'
import styles from './coderunner.css'
const WRONG_FUNCTION = '#882104';
const CORRECT_FUNCTION = '#88994a';
const DICTIONARY = ["code", "for", "while", "if", "int"];
const ENTER_TEXT = "Write Your Code Here!";

export default class CodeRunner extends React.Component{
    constructor(props){
        super(props);
        this.inputArea = null;
        this.state = {
            text: '',
            enterText: '',
            pointer: '|'
        };
    }

    render(){
        return (
            <div className={styles.codeRunnerWrapper}>
                <div id="code-container" className={styles.codeRunner} onClick={this.focusOnInput.bind(this)}>
                    <div className={styles.codeTextContainer}>
                        {this.state.text}
                        <span style={{color: CORRECT_FUNCTION, fontSize: 22}}>
                            {this.state.enterText}{this.state.pointer}
                        </span>
                    </div>
                    <input ref={(input) => this.inputArea = input} onChange={(text) => this.changeText(text)} style={{opacity: 0}}/>
                    <div className={styles.compileButtonContainer}>
                        <button className={styles.compileButton}>Compile</button>
                    </div>
                </div>
                <div className={styles.codeButtons}>
                    <button className={styles.savedCodeButton}/>
                </div>
            </div>
        )
    }

    componentDidMount() {
        setTimeout(this.appendEnterText.bind(this), 100);
    }

    appendEnterText(){
        if(this.state.enterText.length < ENTER_TEXT.length){
            this.setState({
                enterText: ENTER_TEXT.substr(0, this.state.enterText.length + 1)
            });
            setTimeout(this.appendEnterText.bind(this), 100);
        }else
            setTimeout(this.subEnterText.bind(this), 1000);
    }

    subEnterText(){
        if(this.state.enterText.length > 0){
            this.setState({
                enterText: ENTER_TEXT.substr(0, this.state.enterText.length - 1)
            });
            setTimeout(this.subEnterText.bind(this), 75);
        }else
            this.animatePointer();
    }

    animatePointer(){
        this.setState({
            pointer: this.state.pointer === '' ? '|' : ''
        });
        setTimeout(this.animatePointer.bind(this), 500);
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

