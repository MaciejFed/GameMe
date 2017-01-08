import * as $ from "jquery";

export default class MapClient{
    loadMap(mapNumber, callback) {
        this.levelUrl = API_URL + "/level/" + mapNumber;
        $.get(this.levelUrl, callback);
    }
}