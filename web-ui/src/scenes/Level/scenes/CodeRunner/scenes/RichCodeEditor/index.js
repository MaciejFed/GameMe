import React from 'react';
import {Editor, EditorState} from 'draft-js';
import styles from './coderunner.css';
import { CHANGE_CODE } from '../../codeEditorActions';
import {connect} from "react-redux";


@connect((store) => {
    return {
        code: store.codeEditorReducer.codeEditor.code,
    }
}, null, null, { withRef: true })
class MyEditor extends React.Component {
    constructor(props) {
        super(props);
        this.state = {editorState: EditorState.createEmpty()};
        this.editor = null;
    }
    render() {
        return (
            <div className="richCodeRunner">
                <Editor ref={ref => this.editor = ref} editorState={this.state.editorState} onChange={this.onChange.bind(this)} />
            </div>
        );
    }

    onChange(editorState){
        CHANGE_CODE(this.props.dispatch, editorState.getCurrentContent().getPlainText());
        this.setState({editorState})
    }

    componentDidMount(){
        this.focus();
    }

    focus(){
        this.editor.focus();
    }
}

module.exports = MyEditor;