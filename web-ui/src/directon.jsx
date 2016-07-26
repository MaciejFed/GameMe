'use strict';

var DIRECTIONS = ["Up", "Right", "Down", "Left"];
var React = require('react');

var DirectionButtonWrapper = React.createClass({
    getInitialState: function () {
        return({
            directionButtons: []
        })
    },
    render: function () {
        return(
            <div>
                {this.state.directionButtons}
                <button onClick={this.addButton}>Add</button>
            </div>
        )
    },
    addButton: function () {
        var index = this.state.directionButtons.length;
        this.props.onChange(index, "Up");
        this.setState({
            directionButtons: this.state.directionButtons.concat([<DirectionButton index ={index} onChange={this.props.onChange}/>])
        })
     }
});

var DirectionButton = React.createClass({
    getInitialState: function() {
        return({
            directionCounter: 0,
            currentDirection: DIRECTIONS[0]
        })
    },
    render: function() {
        this.props.onChange(this.props.index, this.state.currentDirection)

        return(
            <button onClick={this.nextDirection}>{this.state.currentDirection}</button>
        );
    },
    nextDirection() {
        console.log(this.props.onChange);
        if(this.state.directionCounter < DIRECTIONS.length - 1){
            this.setState({
                directionCounter : this.state.directionCounter + 1,
                currentDirection: DIRECTIONS[this.state.directionCounter + 1]
            })
        }else {
            this.setState(
                this.getInitialState()
            )
        }
    },
    state: function () {
        return this.state.currentDirection;
    }
});

module.exports = DirectionButtonWrapper;