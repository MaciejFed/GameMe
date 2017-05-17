import React from 'react';
import style from './pointerspan.css'
import * as PropTypes from "react/lib/ReactPropTypes";

export const INITIAL_DELAY = 1000;
export const ANIMATION_DELAY = 500;

export default class PointerSpan extends React.Component{
    constructor(){
        super();
        this.animationTimeout = null;
        this.state = {
            pointer: '|',
            initialState: true
        }
    }

    render(){
        return(
            <span key={this.props.text}>
                {this.props.text}<span className='pointerSpan'>{this.state.pointer}</span>
            </span>
        )
    }

    componentWillReceiveProps(nextProps){
        this.setState({
            pointer: '|',
            initialState: true
        });
    }

    componentDidMount(){
        this.animationTimeout = setTimeout(this.animatePointer.bind(this), INITIAL_DELAY);
    }

    componentWillUnmount() {
        if (this.animationTimeout) {
            clearTimeout(this.animationTimeout)
        }
    }

    animatePointer(){
        if(!this.state.initialState){
            this.setState({
                pointer: this.state.pointer === '' ? '|' : ''
            });
        }
        this.setState({
            initialState: false
        });
        this.animationTimeout = setTimeout(this.animatePointer.bind(this), ANIMATION_DELAY);
    }

}

PointerSpan.propTypes = {
    text: PropTypes.oneOfType([PropTypes.object, PropTypes.array]),
};