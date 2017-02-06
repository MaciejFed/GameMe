const EMPTY_GAME_MAP = {
    gameMap: {
    width: 0,
    height: 0,
    obstacles: []
    }
};
export default function (state={
    level: {
        number: 1,
        introductionText: [''],
        startCode: '',
        gameMap: EMPTY_GAME_MAP,
        functions: []
    },
    isLevelLoading: false
}, action) {
    switch (action.type){
        case "LOAD_LEVEL":
            return{
                isLevelLoading: true,
                level: {
                    number: action.payload,
                    gameMap: undefined,
                    functions: []
                }
            };
        case "LOAD_LEVEL_FULFILLED":
            return {
                level: action.payload,
                isLevelLoading: false
            }

    }
    return state;
}