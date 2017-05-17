import React from 'react';
import CodeRunner from './scenes/CodeRunner/CodeRunner';
import Board from './scenes/Board/Board'
import styles from './level.css'
import { connect } from 'react-redux'
import { LOAD_LEVEL } from './levelActions'

@connect((store) => {
    return {
        level: store.levelReducer.level,
        isLevelLoading: store.levelReducer.isLevelLoading
    }
})
export default class Level extends React.Component{

    render(){
        return(
            <div className="level">
                <CodeRunner />
                <Board successCallback={this.nextLevel.bind(this)} />
            </div>
        )
    }

    componentWillMount(){
        this.nextLevel();
    }

    nextLevel(){
        LOAD_LEVEL(this.props.level.number + 1)(this.props.dispatch.bind(this));
    }
}
