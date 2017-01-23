const RECTANGLE_SIZE = window.innerWidth / 20;
const STAGE_NAME = "gameStage";

import React from 'react';
import stageStyles from './board.css'
import GameObject from './game_object';
import Direction from './direction';
const OPEN_FIELD_COLOR = "#999";
const CLOSED_FIELD_COLOR = "#222";
let robot = require("./robot.png");


export default class Board extends React.Component {

    constructor(props) {
        super(props);
        this.stage = null;
        this.robot = new GameObject(new createjs.Shape(), RECTANGLE_SIZE);
    }

    componentDidUpdate() {
        this.refreshStage();
    }

    render() {
        if (this.props.gameMap != undefined)
            return (
                <div key={this.props.levelNumber} id="board" className={stageStyles.board} >
                        <canvas id="gameStage"
                                width={RECTANGLE_SIZE * this.props.gameMap.width}
                                height={RECTANGLE_SIZE * this.props.gameMap.height}>
                        </canvas>
                        <button onClick={() => this.animateBall(this.props.getDirectionValues())} className={stageStyles.animateButton}>Animate</button>
                </div>

            );
        else
            return (<div className={stageStyles.board}>Loading...</div>)
    }

    refreshStage() {
        let robotBitmap = new createjs.Bitmap(robot);
        this.robot = new GameObject(robotBitmap, RECTANGLE_SIZE);

        robotBitmap.image.onload = function() {
            this.initStage(robotBitmap);
            this.renderRectangles();
            this.stage.update();
        }.bind(this);
    }

    initStage(robot){
        this.stage = new createjs.Stage(STAGE_NAME);
        let scale = RECTANGLE_SIZE * 0.8 / robot.getBounds().width;
        robot.x = RECTANGLE_SIZE * 0.1;
        robot.y = RECTANGLE_SIZE * 0.1;
        robot.scaleX = scale;
        robot.scaleY = scale;
        this.stage.addChild(robot);
    }

    renderRectangles(){
        for (let h = 0; h < this.props.gameMap.height; h++) {
            for (let w = 0; w < this.props.gameMap.width; w++) {
                let border = new createjs.Shape();
                border.graphics.beginStroke(OPEN_FIELD_COLOR);
                border.graphics.setStrokeStyle(2);

                if (this.props.gameMap.openField({x: w, y: h}))
                    border.graphics.beginFill(CLOSED_FIELD_COLOR);
                
                border.graphics.drawRect(0, 0, RECTANGLE_SIZE, RECTANGLE_SIZE);
                border.x = w * RECTANGLE_SIZE;
                border.y = h * RECTANGLE_SIZE;
                this.stage.addChild(border);
            }
        }
    }

    animateBall(directionsValues) {
        const directions = directionsValues.map(Direction.mapToDirection);
        const circleTween = createjs.Tween.get(this.robot.bitmapObject);
        for (let i = 0; i < directions.length; i++) {
                this.robot.applyDirection(directions[i]);
                circleTween.to({x: this.robot.x, y: this.robot.y}, 1000, createjs.Ease.getPowInOut(4));

        }
        createjs.Ticker.setFPS(60);
        createjs.Ticker.addEventListener("tick", this.stage);
        setTimeout(this.props.nextLevel, 1000 * directions.length + 1);
    }

};

