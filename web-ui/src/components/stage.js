var RECTANGLE_SIZE = 100;
var STAGE_NAME = "gameStage";

import React from 'react';
import DirectionButtonWrapper from './directon';
var GameMap = require('./map');

export default class Stage extends React.Component {

    constructor(props) {
        super(props);
        this.stage = null;
        this.levelUrl = null;
        this.ball = new GameObject(new createjs.Shape());
        this.directionValues = [];
        this.state = {}
    }

    componentDidMount() {
        this.levelUrl = "http://104.155.41.175:8080/level/" + this.props.params.levelNumber;
        this.serverRequest = $.get(this.levelUrl, function (result, status) {
            this.setState({
                gameMap: new GameMap(result)
            });
        }.bind(this));

    }

    componentDidUpdate() {
        this.refreshStage();
    }

    render() {
        if (this.state.gameMap != undefined)
            return (
                <div id="board">
                    <canvas id="gameStage"
                            width={RECTANGLE_SIZE * this.state.gameMap.width}
                            height={RECTANGLE_SIZE * this.state.gameMap.height}>
                    </canvas>
                    <button onClick={() => this.animateBall(this.directionValues).bind(this)}>Animate</button>
                    <DirectionButtonWrapper onChange={this.handleDirectionsValueChange.bind(this)}/>
                </div>
            );
        else
            return (<div></div>)
    }

    refreshStage() {
        if (this.state.gameMap == undefined)
            return;
        this.stage = new createjs.Stage(STAGE_NAME);
        this.ball.circle.graphics.beginFill("DeepSkyBlue").drawCircle(0, 0, RECTANGLE_SIZE / 6);
        this.ball.circle.x = this.ball.x;
        this.ball.circle.y = this.ball.y;
        this.stage.addChild(this.ball.circle);
        for (var h = 0; h < this.state.gameMap.height; h++) {
            for (var w = 0; w < this.state.gameMap.width; w++) {
            var border = new createjs.Shape();
            border.graphics.beginStroke("#999");
            border.graphics.setStrokeStyle(1);
            if (this.state.gameMap.openField({x: w, y: h}))
                border.graphics.beginFill("DeepSkyBlue");
            border.graphics.drawRect(0, 0, RECTANGLE_SIZE, RECTANGLE_SIZE);
            border.x = w * RECTANGLE_SIZE;
            border.y = h * RECTANGLE_SIZE;
            this.stage.addChild(border);
        }
    }
    this.stage.update();
}

animateBall(directionsValues) {
    var directions = directionsValues.map(mapToDirection);
    var circleTween = createjs.Tween.get(this.ball.circle);
    for (var i = 0; i < directions.length; i++) {
            this.ball.applyDirection(directions[i]);
            circleTween.to({x: this.ball.x, y: this.ball.y}, 1000, createjs.Ease.getPowInOut(4));
        }
        createjs.Ticker.setFPS(60);
        createjs.Ticker.addEventListener("tick", this.stage);
    }

    handleDirectionsValueChange(key, newValue) {
        this.directionValues[key] = newValue;
    }

};

function GameObject(circle) {
    this.x = RECTANGLE_SIZE / 2;
    this.y = RECTANGLE_SIZE / 2;
    this.circle = circle;
}

GameObject.prototype.applyDirection = function (direction) {
    this.x += direction.dX;
    this.y += direction.dY;
};

function Direction(dX, dY) {
    this.dX = dX;
    this.dY = dY;
}

function mapToDirection(stringDirection) {
    switch (stringDirection) {
        case "Up":
            return new Direction(0, -RECTANGLE_SIZE);
        case "Right":
            return new Direction(RECTANGLE_SIZE, 0);
        case "Down":
            return new Direction(0, RECTANGLE_SIZE);
        case "Left":
            return new Direction(-RECTANGLE_SIZE, 0);
    }
}
