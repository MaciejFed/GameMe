import React from 'react';
import style from './pointerspan.css'

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
            <span>
                {this.props.text}<span className={style.pointerSpan}>{this.state.pointer}</span>
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
        this.animationTimeout = setTimeout(this.animatePointer.bind(this), 1000);
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
        this.animationTimeout = setTimeout(this.animatePointer.bind(this), 500);
    }

}