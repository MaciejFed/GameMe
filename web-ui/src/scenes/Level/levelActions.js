import axios from 'axios';

export function LOAD_LEVEL(levelNumber) {
    return function(dispatch){
        dispatch({
            type: "LOAD_LEVEL"
        });
        axios.get(API_URL + "/level/" + levelNumber).then(
            (response) => {
                dispatch({
                    type: "LOAD_LEVEL_FULFILLED",
                    payload: response.data
                })
            })
            .catch((err) => console.log(err));
    }
}