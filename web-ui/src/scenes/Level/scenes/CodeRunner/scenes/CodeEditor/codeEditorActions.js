export function START_ANIMATION (dispatch) {
    dispatch({
        type: "START_ANIMATION",
        payload: []
    })
}

export function END_ANIMATION (dispatch) {
    dispatch({
        type: "END_ANIMATION",
        payload: []
    })
}

export function CHANGE_CODE(dispatch, code) {
    dispatch({
        type: "CHANGE_CODE",
        payload: code
    })
}

export function APPEND_CODE(dispatch, code) {
    dispatch({
        type: "APPEND_CODE",
        payload: code
    })
}

export function CODE_EXECUTION_STARTED(dispatch) {
    dispatch({
        type: "CODE_EXECUTION_STARTED"
    });
}


