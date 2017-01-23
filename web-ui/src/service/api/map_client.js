import * as $ from "jquery";

export default class MapClient{
    loadMap(mapNumber, callback) {
        setTimeout(function () {
            this.levelUrl = API_URL + "/level/" + mapNumber;
            $.get(this.levelUrl, callback);
        }.bind(this), 1000);

    }
}