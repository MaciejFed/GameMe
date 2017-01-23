import React from 'react';
import styles from './savedcodelist.css';

export default class SavedCodeList extends React.Component{
    constructor(){
       super();
    }

    render(){
        return(
            <div className={styles.codeButtons}>
                <button className={styles.savedFunctionButton}/>
            </div>
        )
    }
}