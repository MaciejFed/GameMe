import React from 'react';
import styles from './functionhelper.css'


export default class FunctionHelper extends React.Component{
    constructor(){
        super();
    }

    render(){
        let buttons = this.props.functions.map(
            (functionName) => (<button key={functionName} className={styles.savedFunctionButton} onClick={this.onFunctionClicked.bind(this)}>{functionName}</button>)
        );
        return(
            <div className={styles.functionButtonsContainer}>
                {buttons}
            </div>
        );

    }

    onFunctionClicked(event){
        //this.props.showExample(this.state.examples.find((example) => example.name === event.target.textContent));
        this.props.onFunctionClicked(event.target.textContent);
    }
}