import React from 'react';
import FunctionClient from  './../../../../../../service/api/dictionary_function_client'
import styles from './functionhelper.css'

export default class FunctionHelper extends React.Component{
    constructor(){
        super();
        this.functionClient = new FunctionClient();
        this.state = {functionButtons: []}
    }

    render(){
        return(
            <div className={styles.functionButtonsContainer}>
                {this.state.functionButtons}
            </div>
        )
    }

    componentDidMount(){
        this.loadFunctionButtons();
    }

    componentDidUpdate(){
        this.loadFunctionButtons();
    }

    shouldComponentUpdate(nextProps, nextState){
        return this.props.levelNumber !== nextProps.levelNumber;
    }

    loadFunctionButtons(){
        this.functionClient.loadFunction(this.props.levelNumber, function (result) {
            let buttons = result.functions.map(
                (functionName) => (<button key={result.name} className={styles.savedFunctionButton} onClick={this.onFunctionClicked.bind(this)}>{functionName}</button>)
            );
            this.setState({
                functionButtons: buttons
            });
        }.bind(this))
    }

    onFunctionClicked(event){
        //this.props.showExample(this.state.examples.find((example) => example.name === event.target.textContent));
        this.props.onFunctionClicked(event.target.textContent);
    }
}