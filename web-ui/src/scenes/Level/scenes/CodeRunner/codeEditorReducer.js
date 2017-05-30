let DEFAULT_STATE = {
    codeEditor: {
        isAnimating: true,
        isExecutingCode: false,
        textVisibility: 'hidden',
        code: '',
        executionCode: ''
    }
};

export default function (state=DEFAULT_STATE, action) {
    switch (action.type){
        case "START_ANIMATION":
            return({
                codeEditor:{
                    isAnimating: true,
                    textVisibility: 'hidden'
                }
            });
        case "END_INTRODUCTION":
            return({
                codeEditor:{
                    isAnimating: false,
                    textVisibility: 'visible'
                }
            });
        case "CHANGE_CODE":
            return({
                codeEditor:{
                    code: action.payload !== undefined ? action.payload : state.codeEditor.code
                }
            });
        case "APPEND_CODE":
            return({
                codeEditor:{
                    code: state.codeEditor.isAnimating || state.codeEditor.isExecutingCode ? state.codeEditor.code : state.codeEditor.code + action.payload
                }
            });
        case "CODE_EXECUTION_STARTED":
            return({
                codeEditor: Object.assign({}, state.codeEditor, {
                    isExecutingCode: true,
                    executionCode: state.codeEditor.code
                })
            });
        case "CODE_EXECUTION_ENDED":
            return({
                codeEditor: Object.assign({}, state.codeEditor, {
                    isExecutingCode: false,
                })
            });

    }
    return state;
}