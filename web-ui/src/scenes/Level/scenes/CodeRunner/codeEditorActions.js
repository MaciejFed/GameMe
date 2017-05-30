export function START_ANIMATION (dispatch) {
    dispatch({
        type: "START_ANIMATION",
        payload: []
    })
}

export function END_INTRODUCTION (dispatch) {
    dispatch({
        type: "END_INTRODUCTION",
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