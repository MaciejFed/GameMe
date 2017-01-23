import * as $ from "jquery";

export default class FunctionClient{
    loadAllFunctions(callback) {
        this.levelUrl = API_URL + "/functions";
        $.get(this.levelUrl, callback);
    }

    loadFunction(levelNumber, callback) {
        this.levelUrl = API_URL + "/functions/" + levelNumber;
        $.get(this.levelUrl, callback);
    }
}