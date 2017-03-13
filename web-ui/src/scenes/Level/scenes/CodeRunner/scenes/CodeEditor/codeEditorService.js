import React from 'react';
const WRONG_FUNCTION = '#882104';
const CORRECT_FUNCTION = '#88994a';
const DICTIONARY = ["go();", "rotateRight();"];


export default class Formater{

    formatCode (code){
        return code.split(" ").map(this.style);
    }

    style(word, index){
        let wordStyle = CORRECT_FUNCTION;
        if(DICTIONARY.indexOf(word) === -1)
            wordStyle = WRONG_FUNCTION;
        let codeSpan = (<span key={index} style={{color: wordStyle, fontSize: 22}}> {word}</span>);

        return wordStyle === CORRECT_FUNCTION ? (<div>{codeSpan} </div>) : codeSpan;
    };
}