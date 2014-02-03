cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/org.devgirl.calendar/www/calendar.js",
        "id": "org.devgirl.calendar.Calendar",
        "clobbers": [
            "window.calendar"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "org.devgirl.calendar": "0.1.0"
}
// BOTTOM OF METADATA
});