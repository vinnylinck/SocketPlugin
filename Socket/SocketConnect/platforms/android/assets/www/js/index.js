var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
        document.getElementById("btnConnect").addEventListener('click', app.addToCall());
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicity call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
        app.addToCall();
    },
    calendar : function () {
        var startDate = new Date("September 24, 2013 8:00:00");
        var endDate = new Date("September 24, 2013 18:00:00");
        var title = "PhoneGap Day";
        var location = "Amsterdam";
        var notes = "Arrive on time, don't want to miss out!";
        var success = function() { alert("Success"); };
        var error = function(message) { alert("Oopsie! " + message); };
        
        calendar.createEvent(title, location, notes, startDate, endDate, success, error);
        //socket.connect(title, location, notes, startDate, endDate, success, error);
    }
};