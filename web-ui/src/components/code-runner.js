import React from 'react'
const WRONG_FUNCTION = 'red';
const CORRECT_FUNCTION = 'green';
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
            <div onClick={this.focusOnInput.bind(this)} style={{position: 'absolute', backgroundColor: '#0d010a', width: 300, height: '100%', marginRight: 25}}>
                <div style={{wordWrap: 'break-word', margin: 25}} >{this.state.text}</div>
                <button className="item-button" ></button>
                <input ref={(input) => this.inputArea = input} onChange={(text) => this.changeText(text)} style={{opacity: 0}}/>
            </div>
        )
    }

    focusOnInput(){
        console.log('asd');
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

        return <span style={{color: wordStyle, fontSize: 22}}>{word} </span>
    };
}

