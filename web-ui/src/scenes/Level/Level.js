import React from 'react';
import CodeRunner from './components/CodeRunner/CodeRunner';
import Board from './components/Board/Board'
import GameMap from './components/Board/game_map'
import MapClient from './../../service/api/map_client';
import styles from './level.css'

export default class Level extends React.Component{
    constructor(){
        super();
        this.mapClient = new MapClient();
        this.state = {
            introductionText: [''],
            startCode: '',
            currentLevel: 1
        };
    }

    render(){
        return(
            <div className={styles.level}>
                <CodeRunner ref="codeRunner" showExample={this.showExample.bind(this)} startCode={this.state.startCode} introductionText={this.state.introductionText} levelNumber={this.state.currentLevel}/>
                <Board nextLevel={this.nextLevel.bind(this)} getDirectionValues={this.getDirectionValues.bind(this)} gameMap={this.state.gameMap} levelNumber={this.state.currentLevel - 1}/>
            </div>
        )
    }

    componentDidMount() {
        this.nextLevel();
    }

    getDirectionValues(){
        return this.refs.codeRunner.getDirectionValues();
    }

    nextLevel(){
        this.mapClient.loadMap(this.state.currentLevel, function (result, status) {
            this.setState({
                gameMap: new GameMap(result.gameMap),
                introductionText: result.introductionText,
                currentLevel: this.state.currentLevel + 1
            });
        }.bind(this));
    }


    showExample(example){
        // this.setState({
        //     gameMap: new GameMap(example.gameMap),
        //     introductionText: example.introductionText,
        //     startCode: example.startCode.join(' ')
        // });
    }

}
