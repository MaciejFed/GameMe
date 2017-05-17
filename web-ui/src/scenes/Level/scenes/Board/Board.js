const RECTANGLE_SIZE = window.innerWidth / 20;
const STAGE_NAME = "gameStage";

import React from 'react';
import stageStyles from './board.css'
import GameObject from './game_object';
import GameMap from './game_map'
import {connect} from "react-redux";
import {LOAD_ROAD, CODE_EXECUTION_ENDED} from "./boardActions";
const OPEN_FIELD_COLOR = "#999";
const CLOSED_FIELD_COLOR = "#222";
let robot = require("./robot.png");
let arrow = require("./arrow-right-black.png");

@connect((store) => {
    return({
        levelNumber: store.levelReducer.level.number,
        isLevelLoading: store.levelReducer.isLevelLoading,
        gameMap: new GameMap(store.levelReducer.level.gameMap),
        isExecutingCode: store.codeEditorReducer.codeEditor.isExecutingCode,
        executionCode: store.codeEditorReducer.codeEditor.executionCode,
        roadExecution: store.boardReducer.board.roadExecution,
        isAnimating: store.boardReducer.isAnimating
    })
})
export default class Board extends React.Component {

    constructor(props) {
        super(props);
        this.stage = null;
        this.robot = new GameObject(new createjs.Shape(), RECTANGLE_SIZE);
    }

    componentDidUpdate() {
        this.refreshStage();
        if(this.props.isExecutingCode && !this.props.isAnimating)
            LOAD_ROAD(this.props.levelNumber, {code: this.props.executionCode.split(";").map(s =>( s + ";").replace(/^\s\s*/, '').replace(/\s\s*$/, ''))})(this.props.dispatch);
    }

    render() {
        if (!this.props.isLevelLoading)
            return (
                <div key={this.props.levelNumber} id="board" className="board" >
                        <canvas id="gameStage"
                                width={RECTANGLE_SIZE * this.props.gameMap.width}
                                height={RECTANGLE_SIZE * this.props.gameMap.height}>
                        </canvas>
                        <button onClick={() => this.animateBall()} className="animateButton">Animate</button>
                </div>

            );
        else
            return (<div className={stageStyles.board}>Loading...</div>)
    }

    refreshStage() {
        let robotBitmap = new createjs.Bitmap(robot);
        let arrowBitmap = new createjs.Bitmap(arrow);
        this.robot = new GameObject(robotBitmap, arrowBitmap, RECTANGLE_SIZE);

        robotBitmap.image.onload = function() {
            this.initStage(robotBitmap);
            this.renderRectangles();
            this.stage.update();
        }.bind(this);

        arrowBitmap.image.onload = function () {
            this.initArrow(arrowBitmap);
            this.stage.update();
        }.bind(this);
    }

    initStage(robot){
        this.stage = new createjs.Stage(STAGE_NAME);
        createjs.Ticker.setFPS(60);
        createjs.Ticker.addEventListener("tick", this.stage);
        let scale = RECTANGLE_SIZE * 0.8 / robot.getBounds().width;
        robot.x = RECTANGLE_SIZE * 0.1;
        robot.y = RECTANGLE_SIZE * 0.1;
        robot.scaleX = scale;
        robot.scaleY = scale;
        this.stage.addChild(robot);
    }

    initArrow(arrow){
        let scale = RECTANGLE_SIZE * 0.8 / arrow.getBounds().width * 0.25;
        arrow.x = RECTANGLE_SIZE * 0.15;
        arrow.y = RECTANGLE_SIZE * 0.15;
        arrow.regX = arrow.getBounds().width * 0.5;
        arrow.regY = arrow.getBounds().height * 0.5;
        arrow.scaleX = scale;
        arrow.scaleY = scale;
        this.stage.addChild(arrow);
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
        this.stage.update();
    }

    animateBall() {
        const road = this.props.roadExecution.road;
        const circleTween = createjs.Tween.get(this.robot.bitmapObject);
        const arrowTween = createjs.Tween.get(this.robot.bitmapArrow);
        let animationTimeout = 0;
        for (let i = 0; i < road.length; i++) {
                this.robot.applyDirection(road[i]);
                circleTween.to({x: this.robot.x, y: this.robot.y}, 1000, createjs.Ease.getPowInOut(4));
                arrowTween.to({x: this.robot.x, y: this.robot.y, rotation: (this.robot.rotation - 1) * 90}, 1000, createjs.Ease.getPowInOut(4));
                animationTimeout += 1000;
        }
        let callback = this.props.roadExecution.success ?
            function () {
                CODE_EXECUTION_ENDED(this.props.dispatch)
                this.props.successCallback();
            }.bind(this)
            :
            function(){
                CODE_EXECUTION_ENDED(this.props.dispatch)
            }.bind(this);

        setTimeout(callback, animationTimeout + 500);

    }

};

