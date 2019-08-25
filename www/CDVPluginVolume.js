var exec = require('cordova/exec');

exports.getVolume = function (success, error) {
    exec(success, error, 'CDVPluginVolume', 'getVolume', []);
};

exports.setVolume = function (arg0, success, error) {
    exec(success, error, 'CDVPluginVolume', 'setVolume', [arg0]);
};