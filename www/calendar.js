/* JS Interface */
var calendar = {
    createEvent: function(title, location, notes, startDate, endDate, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Calendar', // mapped to our native Java class called "CalendarPlugin"
            'Testsadsadsa', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "title": title
            }]
        ); 
    }
}
module.exports = calendar;