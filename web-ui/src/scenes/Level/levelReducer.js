const EMPTY_GAME_MAP = {
    gameMap: {
    width: 0,
    height: 0,
    obstacles: []
    }
};

const EMPTY_LEVEL = {
    level: {
        number: 0,
        introductionText: [''],
        startCode: '',
        gameMap: EMPTY_GAME_MAP,
        functions: []
    },
    isLevelLoading: false
};

export default function (state=EMPTY_LEVEL, action) {
    switch (action.type){
        case "LOAD_LEVEL":
            return EMPTY_LEVEL;
        case "LOAD_LEVEL_FULFILLED":
            return {
                level: action.payload,
                isLevelLoading: false
            }

    }
    return state;
}