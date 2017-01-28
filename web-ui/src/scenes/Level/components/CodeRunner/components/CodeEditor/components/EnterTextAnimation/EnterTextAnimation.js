import React from 'react';
import * as PropTypes from "react/lib/ReactPropTypes";
import PointerSpan from '../PointerSpan/PointerSpan'
const ENTER_TEXT_COLOR = '#88994a';
export const APPEND_CHARACTER_DELAY = 100;
export const SUBTRACT_CHARACTER_DELAY = 75;
export const APPENDING_DONE_DELAY = 2000;

export default class EnterTextAnimation extends React.Component{
    constructor(props){
        super(props);
        this.timeout = null;
        this.state = {
            displayText: '',
            counter: 0,
            currentText: this.props.introductionText[0]
        };
        this.appendEnterText();
    }

    render(){
        let innerSpan = (
            <span key={this.props.introductionText[0]} style={{color: ENTER_TEXT_COLOR, fontSize: 22}}>
                {this.state.displayText}
            </span>);
        return(
            <PointerSpan text={innerSpan} />
        )
    }

    componentWillReceiveProps(nextProps){
        if(this.timeout)
            clearTimeout(this.timeout);
        this.text({
            displayText: '',
            counter: 0,
            currentText: nextProps.introductionText[0]
        });
        this.timeout = setTimeout(this.appendEnterText.bind(this), APPEND_CHARACTER_DELAY);
    }

    componentWillUnmount(){
        clearTimeout(this.timeout);
    }

    appendEnterText(){
        if(this.state.displayText.length < this.state.currentText.length){
            this.setState({
                displayText: this.state.currentText.substr(0, this.state.displayText.length + 1)
            });
            this.timeout = setTimeout(this.appendEnterText.bind(this), APPEND_CHARACTER_DELAY);
        }else{
            this.timeout = setTimeout(function(){
                this.subEnterText();
            }.bind(this), APPENDING_DONE_DELAY);
        }
    }

    subEnterText(){
        if(this.state.displayText.length > 0){
            this.setState({
                displayText: this.state.currentText.substr(0, this.state.displayText.length - 1)
            });
            this.timeout = setTimeout(this.subEnterText.bind(this), SUBTRACT_CHARACTER_DELAY);
        }else{
            if(this.state.counter + 1 === this.props.introductionText.length){
                this.props.endCallback();
            }else {
                this.setState({
                    counter: this.state.counter + 1,
                    currentText: this.props.introductionText[this.state.counter + 1]
                });
                this.appendEnterText();
            }
        }
    }
}

EnterTextAnimation.PropTypes = {
    introductionText: Array.of(String).isRequired,
    endCallback: Function.isRequired
};