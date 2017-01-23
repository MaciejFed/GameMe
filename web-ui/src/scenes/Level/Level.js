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
            startCode: ''
        };
    }

    render(){
        return(
            <div className={styles.level}>
                <CodeRunner showExample={this.showExample.bind(this)} startCode={this.state.startCode} introductionText={this.state.introductionText}/>
                <Board gameMap={this.state.gameMap} levelNumber={this.getLastLevelPlayed()}/>
            </div>
        )
    }

    componentDidMount() {
        this.mapClient.loadMap(this.getLastLevelPlayed(), function (result, status) {
            this.setState({
                gameMap: new GameMap(result.gameMap),
                introductionText: result.introductionText
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

    getLastLevelPlayed(){
        return 1;
    }
}
