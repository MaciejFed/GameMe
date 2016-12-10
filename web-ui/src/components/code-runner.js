import React from 'react'
const WRONG_FUNCTION = 'red';
const CORRECT_FUNCTION = 'green';
const DICTIONARY = ["code"];

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
            text: this.format(text.target.value)
        });
    }

    format(text){
        return text.split(" ").map(this.style);
    }

    style(word){
        let wordStyle = CORRECT_FUNCTION;
        if(DICTIONARY.indexOf(word) === -1)
            wordStyle = WRONG_FUNCTION;

        return <span style={{color: wordStyle}}>{word} </span>
    };
}

