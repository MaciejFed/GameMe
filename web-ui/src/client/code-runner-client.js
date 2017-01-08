import * as $ from "jquery";


export default class CodeRunnerClient {
    loadRoad(levelNumber, path, startPoint) {
        const body = {"startPoint": "[0,  0]"};
        let result = [];
        $.ajax({
            url : API_URL + "/level/" + levelNumber,
            type: "POST",
            data : JSON.stringify(body),
            contentType: 'application/json',
            async: false,
            success: function(data, textStatus, jqXHR) {
                result = data;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown)
            }
        });

        return result;
    }
}

