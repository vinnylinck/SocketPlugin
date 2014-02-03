/*global require, console, setTimeout */

var net = require('net');
var port = 8124;

var server = net.createServer(function (c) {
    'use strict';
    
    console.log('server connected');
        
    c.on('end', function () {
        console.log('server disconnected');
    });
    
    c.write('You are connected to the Server: ' + port + '\r\n');
    c.pipe(c);
});

server.listen(port, function () {
    'use strict';
    
    console.log('server bound on: ' + port);
});

server.on('error', function (e) {
    'use strict';
    if (e.code === 'EADDRINUSE') {
        console.log('Address in use, retrying...');
        setTimeout(function () {
            server.close();
            server.listen(port, 'localhost');
        }, 1000);
    }
});