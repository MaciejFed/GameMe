'use strict';
console.log("asd");

var Config = "http://localhost:8080";
//var Config = require('Config');;
console.log(Config + "/assets/bundle.js");

$.getScript(Config + "/assets/bundle.js", function(){

    alert("Script loaded but not necessarily executed.");

});