import React from 'react';
import PointerSpan from './components/PointerSpan/PointerSpan';
import EnterTextAnimation from './components/EnterTextAnimation/EnterTextAnimation'
import styles from './codeeditor.css';
import { END_ANIMATION, CHANGE_CODE, CODE_EXECUTION_STARTED } from './codeEditorActions';
import {connect} from "react-redux";
import CodeEditorService from './codeEditorService';

@connect((store) => {
    return {
        isAnimating: store.codeEditorReducer.codeEditor.isAnimating,
        textVisibility: store.codeEditorReducer.codeEditor.textVisibility,
        code: store.codeEditorReducer.codeEditor.code,
        introductionText: store.levelReducer.level.introductionText
    }
})
export default class CodeEditor extends React.Component{
    constructor(props){
        super(props);
        this.inputArea = null;
        this.codeEditorService = new CodeEditorService();
    }

    render(){
        return(
            <div id="code-container" className={styles.codeRunner} onClick={this.focusOnInput.bind(this)}>
                {
                    !this.props.isAnimating ? null :
                    <EnterTextAnimation introductionText={this.props.introductionText} endCallback={this.endAnimationCallback.bind(this)}/>
                }
                <div style={{visibility: this.props.textVisibility}} className={styles.codeTextContainer}>
                    <PointerSpan text={this.codeEditorService.formatCode(this.props.code)}/>
                </div>
                <input ref={(input) => this.inputArea = input} onChange={(event) => this.changeText(event.target.value)} style={{opacity: 0}}/>
                <div className={styles.compileButtonContainer}>
                    <button className={styles.compileButton} onClick={this.runCode.bind(this)}>Compile</button>
                </div>
            </div>
        )
    }

    endAnimationCallback(){
        END_ANIMATION(this.props.dispatch);
        this.focusOnInput();
    }

    focusOnInput(){
        this.inputArea.focus();
    }

    componentDidUpdate(){
        this.inputArea.value = this.props.code;
        this.focusOnInput();
    }

    changeText(text){
        CHANGE_CODE(this.props.dispatch, text);
        this.focusOnInput();
    }

    runCode(){
        CODE_EXECUTION_STARTED(this.props.dispatch)
    }

}