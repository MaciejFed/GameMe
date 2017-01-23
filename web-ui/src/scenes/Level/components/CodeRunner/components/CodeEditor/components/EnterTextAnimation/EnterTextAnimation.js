import React from 'react';
import PointerSpan from '../PointerSpan/PointerSpan'
const ENTER_TEXT_COLOR = '#88994a';
const ENTER_TEXT = "Write Your Code Here!";

export default class EnterTextAnimation extends React.Component{
    constructor(){
        super();
        this.state = {
            enterText: ''
        }
    }

    render(){
        let innerSpan = (
            <span style={{color: ENTER_TEXT_COLOR, fontSize: 22}}>
                {this.state.enterText}
            </span>);
        return(
            <PointerSpan text={innerSpan} />
        )
    }

    componentDidMount() {
        setTimeout(this.appendEnterText.bind(this), 100);
    }

    appendEnterText(){
        if(this.state.enterText.length < ENTER_TEXT.length){
            this.setState({
                enterText: ENTER_TEXT.substr(0, this.state.enterText.length + 1)
            });
            setTimeout(this.appendEnterText.bind(this), 100);
        }else{
            setTimeout(function(){
                this.subEnterText();
            }.bind(this), 2000);
        }
    }

    subEnterText(){
        if(this.state.enterText.length > 0){
            this.setState({
                enterText: ENTER_TEXT.substr(0, this.state.enterText.length - 1)
            });
            setTimeout(this.subEnterText.bind(this), 75);
        }else{
            this.props.endCallback();
        }
    }

}