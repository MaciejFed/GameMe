const DIRECTIONS = ["Up", "Right", "Down", "Left"];
import React from 'react';

export default class DirectionButtonWrapper extends React.Component{
    constructor(props){

        super(props);
        this.state = {
            directionButtons: []
        }
    }

    render() {
        return(
            <div>
                {this.state.directionButtons}
                <button onClick={this.addButton.bind(this)}>Add</button>
            </div>
        );
    }
    addButton() {
        var index = this.state.directionButtons.length;
        this.props.onChange(index, DIRECTIONS[0]);
        this.setState({
            directionButtons: this.state.directionButtons.concat([<DirectionButton index ={index} onChange={this.props.onChange.bind(this)}/>])
        });
     }
};

class DirectionButton extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            directionCounter: 0,
            currentDirection: DIRECTIONS[0]
        }
    }
    render() {
        this.props.onChange(this.props.index, this.state.currentDirection);
        return(
            <button onClick={this.nextDirection.bind(this)}>{this.state.currentDirection}</button>
        )
    }
    nextDirection() {
        if(this.state.directionCounter < DIRECTIONS.length - 1){
            this.setState({
                directionCounter : this.state.directionCounter + 1,
                currentDirection: DIRECTIONS[this.state.directionCounter + 1]
            })
        }else {
            this.setState({
                directionCounter: 0,
                currentDirection: DIRECTIONS[0]
                }
            )
        }

    }
    state() {
        return this.state.currentDirection;
    }
}
