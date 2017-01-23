import * as $ from "jquery";

export default class FunctionClient{
    loadAllFunctions(callback) {
        this.levelUrl = API_URL + "/examples";
        $.get(this.levelUrl, callback);
    }
}