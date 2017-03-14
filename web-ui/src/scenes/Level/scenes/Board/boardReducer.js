const DEFAULT_STATE={
    board:{
        road:[]
    }
};

export default function (state=DEFAULT_STATE, action) {
    switch (action.type){
        case "LOAD_ROAD":
            return state;
        case "LOAD_ROAD_FULFILLED":
            return Object.assign({}, state.board,  {board: {roadExecution: action.payload}, isAnimating: true});
        case "CODE_EXECUTION_ENDED":
            return Object.assign({}, state.board,  {board: {isAnimating: false}});
    }
    return state;
}