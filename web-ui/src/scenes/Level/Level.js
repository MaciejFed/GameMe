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
        this.state = {};
    }

    render(){
        return(
            <div className={styles.level}>
                <CodeRunner />
                <Board gameMap={this.state.gameMap} levelNumber={this.getLastLevelPlayed()}/>
            </div>
        )
    }

    componentDidMount() {
        this.mapClient.loadMap(this.getLastLevelPlayed(), function (result, status) {
            this.setState({
                gameMap: new GameMap(result)
            });
        }.bind(this));
    }

    getLastLevelPlayed(){
        return 125;
    }
}
