import React from 'react';
import PointerSpan from './components/PointerSpan/PointerSpan';
import EnterTextAnimation from './components/EnterTextAnimation/EnterTextAnimation'
import styles from './codeeditor.css';
import { END_INTRODUCTION } from '../../codeEditorActions';
import CodeEditorService from './../../codeEditorService';
import {connect} from "react-redux";


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
        this.codeEditorService = new CodeEditorService();
    }

    render(){
        return(
            <div id="code-container" className="codeRunner">
                <EnterTextAnimation introductionText={this.props.introductionText} endCallback={this.endAnimationCallback.bind(this)}/>
                <div style={{visibility: this.props.textVisibility}} className="codeTextContainer">
                    <PointerSpan text={this.codeEditorService.formatCode(this.props.code)}/>
                </div>
            </div>
        )
    }

    endAnimationCallback(){
        END_INTRODUCTION(this.props.dispatch);
    }

}