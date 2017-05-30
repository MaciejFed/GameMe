import React, { PropTypes } from 'react'
import stageStyles from './board.css'
import buttonStyles from './animatebutton.css'
const RECTANGLE_SIZE = window.innerWidth / 20;
const STAGE_NAME = "gameStage";

export default class BoardMap extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            code: 'eh',
            rode: [],
            isAnimating: false
        }
    }

    render(){
        return(
            <div key="const" id="board" className="board" >
                <h2 className="levelTitle">{ this.props.levelNumber } Level</h2>
                <div className="map">
                    <canvas id={STAGE_NAME}
                            width={RECTANGLE_SIZE * this.props.gameMap.width}
                            height={RECTANGLE_SIZE * this.props.gameMap.height}>
                    </canvas>
                    <button onClick={() => this.props.onAnimateClick()} className="animateButton">Animate</button>
                </div>
            </div>
        );
    }

    componentDidUpdate(){
        if(this.stage === undefined ){
            this.stage = new createjs.Stage(STAGE_NAME);
        }
        this.props.renderInitialStateOfLevel(this.stage);
        this.stage.update();
        this.props.animateRobot(this.stage);
    }

    shouldComponentUpdate(nextProps, nextState){
        this.setState({
            code: nextProps.code,
            road: nextProps.road,
            isAnimating: nextProps.isAnimating
        });
        return !nextProps.isRendered || nextProps.isAnimating;
    }

}