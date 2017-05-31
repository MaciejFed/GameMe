import React from 'react';
import { Editor, EditorState, ContentState, CompositeDecorator } from 'draft-js';
import styles from './coderunner.css';
import { CHANGE_CODE } from '../../codeEditorActions';
import { connect } from "react-redux";
const CORRECT_FUNCTION_REGEX = /(go\(\d*\);)|(rotateRight\(\d*\);)|(while\((not)?FacingWall\(\)\))|(if\((not)?FacingWall\(\)\))|(\{)|(\})|(while\(true\(\)\))|(else)/g;

const CorrectFunctionSpan = (props) => {
    return <span {...props} className="correctFunction">{props.children}</span>;
};

function correctFunctionStrategy(contentBlock, callback, contentState) {
    findWithRegex(CORRECT_FUNCTION_REGEX, contentBlock, callback);
}

const compositeDecorator = new CompositeDecorator([
    {
        strategy: correctFunctionStrategy,
        component: CorrectFunctionSpan
    }
]);

function findWithRegex(regex, contentBlock, callback) {
    const text = contentBlock.getText();
    let matchArr, start;
    while ((matchArr = regex.exec(text)) !== null) {
        start = matchArr.index;
        callback(start, start + matchArr[0].length);
    }
}

@connect((store) => {
    return {
        code: store.codeEditorReducer.codeEditor.code,
        startCode: store.levelReducer.level.startCode
    }
}, null, null, { withRef: true })
class MyEditor extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {editorState: EditorState.createWithContent(ContentState.createFromText(props.startCode), compositeDecorator)};
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