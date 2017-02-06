import React from 'react';
import CodeRunner from './components/CodeRunner/CodeRunner';
import Board from './components/Board/Board'
import GameMap from './components/Board/game_map'
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
            <div className={styles.level}>
                <CodeRunner ref="codeRunner" showExample={this.showExample.bind(this)} startCode={this.props.level.startCode} introductionText={this.props.level.introductionText} levelNumber={this.props.level.number}/>
                <Board nextLevel={this.nextLevel.bind(this)} getDirectionValues={this.getDirectionValues.bind(this)} gameMap={new GameMap(this.props.level.gameMap)} levelNumber={this.props.level.number - 1}/>
            </div>
        )
    }

    componentWillMount(){
        this.nextLevel();
    }

    getDirectionValues(){
        return this.refs.codeRunner.getDirectionValues();
    }

    nextLevel(){
        LOAD_LEVEL(this.props.level.number)(this.props.dispatch.bind(this));
    }


    showExample(example){
        // this.setState({
        //     gameMap: new GameMap(example.gameMap),
        //     introductionText: example.introductionText,
        //     startCode: example.startCode.join(' ')
        // });
    }

}
