import React from 'react';
import CodeRunner from './scenes/CodeRunner/CodeRunner';
import Board from './scenes/Board/Board'
import styles from './level.css'
import { connect } from 'react-redux'
import { LOAD_LEVEL } from './levelActions'


@connect((stateProps) => {
    return {
        level: stateProps.levelReducer.level,
        isLevelLoading: stateProps.levelReducer.isLevelLoading
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
        LOAD_LEVEL(location.pathname.substr(1))(this.props.dispatch.bind(this));
    }

    nextLevel(){
        history.pushState({urlPath: this.props.level.number + 1 },"", this.props.level.number + 1 );
        window.location.reload();
    }
}