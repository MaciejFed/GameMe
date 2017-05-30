import React from 'react';

import GameMap from './game_map'
import { connect } from 'react-redux';
import { LOAD_ROAD, MAP_RENDERED, CODE_EXECUTION_ENDED} from './boardActions';
import BoardMap from './components/BoardMap/BoardMap';
import { renderInitialMap, animateRoad } from './graphics/renderUtils';

const DELAY = 2000;

const mapStateToProps = (state, ownProps) => {
    return {
        code: state.codeEditorReducer.codeEditor.code,
        levelName: state.levelReducer.level.name,
        gameMap: new GameMap(state.levelReducer.level.gameMap),
        isLevelLoading: state.levelReducer.isLevelLoading,
        isRendered: state.boardReducer.isRendered,
        road: state.boardReducer.road,
        isAnimating: state.boardReducer.isAnimating
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        dispatch: dispatch
    }
};

function mergeProps(stateProps, dispatchProps, ownProps) {
    return Object.assign({}, ownProps, stateProps, dispatchProps, {
        renderInitialStateOfLevel: (stage) => {
            if(!stateProps.isRendered) {
                renderInitialMap(stage, stateProps.gameMap);
                MAP_RENDERED(dispatchProps.dispatch);
            }
        },
        onAnimateClick: () => {
            LOAD_ROAD(stateProps.levelName, {code: stateProps.code})(dispatchProps.dispatch);
        },
        animateRobot: (stage) => {
            if(stateProps.isAnimating){
                const animationTimeout = animateRoad(stage, stateProps.road);
                setTimeout(() => CODE_EXECUTION_ENDED(dispatchProps.dispatch), animationTimeout + DELAY);
            }
        }
    })
}

const Board = connect(
    mapStateToProps,
    mapDispatchToProps,
    mergeProps
)(BoardMap);

export default Board
