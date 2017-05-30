import React from 'react';
import CodeRunner from './scenes/CodeRunner/CodeRunner';
import Board from './scenes/Board/Board'
import styles from './level.css'
import { connect } from 'react-redux'
import { LOAD_LEVEL } from './levelActions'

const mapStateToProps = (storeProps, ownProps) => {
    return {
        level: storeProps.levelReducer.level,
        isLevelLoading: storeProps.levelReducer.isLevelLoading,
        isAnimating: storeProps.boardReducer.isAnimating
    }
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        dispatch: dispatch
    }
};
@connect()
class Level extends React.Component{

    render(){
        return(
            <div className="level">
                <CodeRunner />
                <Board successCallback={this.nextLevel.bind(this)} />
            </div>
        )
    }

    componentDidMount(){
        LOAD_LEVEL(location.pathname.substr(1))(this.props.dispatch.bind(this));
    }

    nextLevel(){
        history.pushState({urlPath: this.props.level.name + 1 },"", this.props.level.name + 1 );
        window.location.reload();
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Level);