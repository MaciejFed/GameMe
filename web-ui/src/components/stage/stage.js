const RECTANGLE_SIZE = window.innerWidth / 20;
const STAGE_NAME = "gameStage";

import React from 'react';
import CodeRunner from '../code_runner/code-runner'
import * as $ from "jquery";
import stageStyles from './stage-style.css'
const GameMap = require('./../map_wrapper/map');
const OPEN_FIELD_COLOR = "#999";
const CLOSED_FIELD_COLOR = "#222";


export default class Stage extends React.Component {

    constructor(props) {
        super(props);
        this.stage = null;
        this.levelUrl = null;
        this.ball = new GameObject(new createjs.Shape());
        this.directionValues = [];
        this.state = {};
    }

    componentDidMount() {
        this.levelUrl = API_URL + "/level/" + this.getLastLevelPlayed();
        $.get(this.levelUrl, function (result, status) {
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
                <div className={stageStyles.stage}>
                    <CodeRunner />
                    <div id="board" className={stageStyles.board} >
                        <canvas id="gameStage"
                                width={RECTANGLE_SIZE * this.state.gameMap.width}
                                height={RECTANGLE_SIZE * this.state.gameMap.height}>
                        </canvas>
                        <button onClick={() => this.animateBall(this.directionValues).bind(this)} className={stageStyles.animateButton}>Animate</button>
                    </div>
                </div>
            );
        else
            return (<div></div>)
    }

    refreshStage() {
        if (this.state.gameMap == undefined)
            return;
        this.initStage();
        this.renderRectangles();
        this.stage.update();
    }

    initStage(){
        this.stage = new createjs.Stage(STAGE_NAME);
        this.ball.circle.graphics.beginFill("#BCEE68").drawCircle(0, 0, RECTANGLE_SIZE / 4);
        this.ball.circle.x = this.ball.x;
        this.ball.circle.y = this.ball.y;
        this.stage.addChild(this.ball.circle);
    }

    renderRectangles(){
        for (let h = 0; h < this.state.gameMap.height; h++) {
            for (let w = 0; w < this.state.gameMap.width; w++) {
                let border = new createjs.Shape();
                border.graphics.beginStroke(OPEN_FIELD_COLOR);
                border.graphics.setStrokeStyle(2);
                console.log(stageStyles.closeField);
                if (this.state.gameMap.openField({x: w, y: h}))
                    border.graphics.beginFill(CLOSED_FIELD_COLOR);
                border.graphics.drawRect(0, 0, RECTANGLE_SIZE, RECTANGLE_SIZE);
                border.x = w * RECTANGLE_SIZE;
                border.y = h * RECTANGLE_SIZE;
                this.stage.addChild(border);
            }
        }
    }

    loadRoad(levelNumber, path, startPoint) {
        console.log(startPoint);
        const body = {"path": path, "startPoint": startPoint};
        let result = [];
        $.ajax({
            url : API_URL + "/level/" + levelNumber,
            type: "POST",
            data : JSON.stringify(body),
            contentType: 'application/json',
            async: false,
            success: function(data, textStatus, jqXHR) {
                result = data;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown)
            }
        });

        return result;
    }

    animateBall(directionsValues) {
    const directions = this.loadRoad(this.props.params.levelNumber, directionsValues, this.ball.getPoint()).map(mapToDirection);
    const circleTween = createjs.Tween.get(this.ball.circle);
    for (let i = 0; i < directions.length; i++) {
            this.ball.applyDirection(directions[i]);
            circleTween.to({x: this.ball.x, y: this.ball.y}, 1000, createjs.Ease.getPowInOut(4));
        }
        createjs.Ticker.setFPS(60);
        createjs.Ticker.addEventListener("tick", this.stage);
    }

    handleDirectionsValueChange(key, newValue) {
        this.directionValues[key] = newValue;
    }

    getLastLevelPlayed(){
        return 125;
    }

};

function GameObject(circle) {
    this.gameX = 0;
    this.gameY = 0;
    this.x = RECTANGLE_SIZE / 2;
    this.y = RECTANGLE_SIZE / 2;
    this.circle = circle;
}

GameObject.prototype.applyDirection = function (direction) {
    this.x += direction.dX;
    this.y += direction.dY;
    this.gameX += direction.dX / RECTANGLE_SIZE;
    this.gameY += direction.dY / RECTANGLE_SIZE;
};

GameObject.prototype.getPoint = function () {
    return{
        "x" : this.gameX,
        "y" : this.gameY
    };
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