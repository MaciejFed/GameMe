import React from 'react';
const WRONG_FUNCTION = '#882104';
const CORRECT_FUNCTION = '#88994a';
const DICTIONARY = ["go();", "rotateRight();"];


export default class Formater{

    formatCode (codeStr){
       return codeStr.replace(";", ";.").split(".").filter(s => s.length > 0).map(this.style);
    }

    style(word, index){
        let wordStyle = WRONG_FUNCTION;
            if(new RegExp("(go\\(.?\\);)").test(word) || new RegExp("(rotateRight\\(.?e\\);)").test(word) || new RegExp("(def\\D.+\\s*=\\s*\\d+;)").test(word))
            wordStyle = CORRECT_FUNCTION;
        let codeSpan = (<span key={index} style={{color: wordStyle, fontSize: 22}}> {word}</span>);

        return wordStyle === CORRECT_FUNCTION ? (<div>{codeSpan} </div>) : codeSpan;
    };
}