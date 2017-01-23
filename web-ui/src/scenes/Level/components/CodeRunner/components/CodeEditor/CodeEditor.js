import React from 'react';
import PointerSpan from './components/PointerSpan/PointerSpan';
import EnterTextAnimation from './components/EnterTextAnimation/EnterTextAnimation'
import styles from './codeeditor.css';
const WRONG_FUNCTION = '#882104';
const CORRECT_FUNCTION = '#88994a';
const DICTIONARY = ["code", "for", "while", "if", "int", "goRight();", "goDown();"];

export default class CodeEditor extends React.Component{
    constructor(props){
        super(props);
        this.inputArea = null;
        this.state = {
            textVisibility: 'hidden',
            animationEnded: false
        };
    }

    render(){
        return(
            <div id="code-container" className={styles.codeRunner} onClick={this.focusOnInput.bind(this)}>
                {
                    this.state.animationEnded ? null :
                    <EnterTextAnimation introductionText={this.props.introductionText} endCallback={this.endAnimationCallback.bind(this)}/>
                }
                <div style={{visibility: this.state.textVisibility}} className={styles.codeTextContainer}>
                    <PointerSpan text={this.state.text}/>
                </div>
                <input ref={(input) => this.inputArea = input} onChange={(event) => this.changeText(event.target.value)} style={{opacity: 0}}/>
                <div className={styles.compileButtonContainer}>
                    <button className={styles.compileButton}>Compile</button>
                </div>
            </div>
        )
    }

    endAnimationCallback(){
        this.setState({
            textVisibility: 'visible',
            animationEnded: true
        });
        this.inputArea.value = this.props.startCode;
        this.changeText(this.props.startCode);
        this.focusOnInput();
    }

    componentWillReceiveProps(nextProps){
        this.setState({
            textVisibility: 'hidden',
            animationEnded: false
        });
    }

    focusOnInput(){
        this.inputArea.focus();
    }

    changeText(text){
        this.inputArea.value = text;
        this.setState({
            text: CodeEditor.format(text)
        });
        this.focusOnInput();
    }

    appendText(text){
        this.inputArea.value = this.inputArea.value + text;
        this.setState({
            text: CodeEditor.format(this.inputArea.value),
            animationEnded: true,
            textVisibility: 'visible',
        });
        this.focusOnInput();
    }

    getCode(){
        return this.inputArea.value.split(" ");
    }

    static format(text){
        return text.split(" ").map(CodeEditor.style);
    }

    static style(word, index){
        let wordStyle = CORRECT_FUNCTION;
        if(DICTIONARY.indexOf(word) === -1)
            wordStyle = WRONG_FUNCTION;
        let codeSpan = (<span key={index} style={{color: wordStyle, fontSize: 22}}> {word}</span>);

        return wordStyle === CORRECT_FUNCTION ? (<div>{codeSpan} </div>) : codeSpan;
    };
}