import React from 'react';
import PointerSpan from '../PointerSpan/PointerSpan'
const ENTER_TEXT_COLOR = '#88994a';

export default class EnterTextAnimation extends React.Component{
    constructor(props){
        super(props);
        this.timeout = null;
        this.state = {
            displayText: '',
            counter: 0,
            currentText: ''
        };
    }

    render(){
        let innerSpan = (
            <span style={{color: ENTER_TEXT_COLOR, fontSize: 22}}>
                {this.state.displayText}
            </span>);
        return(
            <PointerSpan text={innerSpan} />
        )
    }

    componentWillReceiveProps(nextProps){
        if(this.timeout)
            clearTimeout(this.timeout);
        this.setState({
            displayText: '',
            counter: 0,
            currentText: nextProps.introductionText[0]
        });
        this.timeout = setTimeout(this.appendEnterText.bind(this), 100);
    }

    appendEnterText(){
        if(this.state.displayText.length < this.state.currentText.length){
            this.setState({
                displayText: this.state.currentText.substr(0, this.state.displayText.length + 1)
            });
            this.timeout = setTimeout(this.appendEnterText.bind(this), 100);
        }else{
            this.timeout = setTimeout(function(){
                this.subEnterText();
            }.bind(this), 2000);
        }
    }

    subEnterText(){
        if(this.state.displayText.length > 0){
            this.setState({
                displayText: this.state.currentText.substr(0, this.state.displayText.length - 1)
            });
            this.timeout = setTimeout(this.subEnterText.bind(this), 75);
        }else{
            if(this.state.counter + 1 === this.props.introductionText.length){
                this.props.endCallback();
            }
            else {
                this.setState({
                    counter: this.state.counter + 1,
                    currentText: this.props.introductionText[this.state.counter + 1]
                });
                this.appendEnterText();
            }
        }
    }

}