import React from 'react'

const WRONG_FUNCTION = '#770100';
const CORRECT_FUNCTION = '#09770b';


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
            <div>
                <div onClick={() => this.focusOnInput()}>{this.state.text}</div>
                <input ref={(input) => this.inputArea = input} onChange={(text) => this.changeText(text)} style={{opacity: 0}}/>
            </div>
        )
    }

    focusOnInput(){
        this.inputArea.focus();
    }

    changeText(text){
        this.setState({
            text: text.target.value
        });
    }
}
