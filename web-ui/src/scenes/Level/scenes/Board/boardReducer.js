const DEFAULT_STATE={
    road:[],
    isAnimating: false,
    isRendered: false
};

export default function (state=DEFAULT_STATE, action) {
    switch (action.type){
        case "LOAD_ROAD":
            return Object.assign({}, state);
        case "LOAD_ROAD_FULFILLED":
            return Object.assign({}, state, { road: action.payload.road });
        case "MAP_RENDERED":
            return Object.assign({}, state, { isRendered: true });
        case "CODE_EXECUTION_STARTED":
            return Object.assign({}, state, { isAnimating: true });
        case "CODE_EXECUTION_ENDED":
            return Object.assign({}, state, { isRendered: false, isAnimating: false });
    }
    return state;
}