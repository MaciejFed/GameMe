import axios from 'axios';

export function LOAD_ROAD(levelNumber, code) {
    return function (dispatch) {
        dispatch({
            type: "LOAD_ROAD"
        });
        axios.post(API_URL + "/level/" + levelNumber, code).then((response) => {
            dispatch({
                type: "LOAD_ROAD_FULFILLED",
                payload: response.data
            });
            dispatch({
                type: "CODE_EXECUTION_STARTED"
            })
        })
    }

}

export function MAP_RENDERED(dispatch) {
    dispatch({
        type: "MAP_RENDERED"
    })
}

export function CODE_EXECUTION_ENDED(dispatch) {
    dispatch({
        type: "CODE_EXECUTION_ENDED"
    })
}