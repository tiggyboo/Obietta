'use strict'
 
const Hapi = require('hapi');
const Request = require('request');
const Vision = require('vision');
const Handlebars = require('handlebars');
const LodashFilter = require('lodash.filter');
const LodashTake = require('lodash.take');
 
const server = new Hapi.Server();

//ignore SSL errors
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
 
server.connection({
    host: '127.0.0.1',
    port: 3000
});
 
// Register vision for our views
server.register(Vision, (err) => {
    server.views({
        engines: {
            html: Handlebars
        },
        relativeTo: __dirname,
        path: './views',
    });
});
/*server.route({
    method: 'GET',
    path: '/',
    handler: function (request, reply) {
        Request.get('http://api.football-data.org/v1/competitions/438/leagueTable', function (error, response, body) {
            if (error) {
                throw error;
            }

            const data = JSON.parse(body);
            reply.view('index', { result: data });
        });
    }
}); */
// Committee route
server.route({
    method: 'GET',
    path: '/committee',
    handler: function (request, reply) {
        Request.get('http://mds-committee-ws.tcdev.mayo.edu/rest/committee', function (error, response, body) {
            if (error) {
                throw error;
            }
		    console.log("committee route");

            const data = JSON.parse(body);
            reply.view('committees', { result: data });
        });
    }
});
server.route({
    method: 'GET',
    path: '/committee/id/{ai}',
    handler: function (request, reply) {
        Request.get('http://mds-committee-ws.tcdev.mayo.edu/rest/committee/id/${ai}', function (error, response, body) {
            if (error) {
                throw error;
            }
                    console.log("committee detail route");

            const data = JSON.parse(body);
            reply.view('committee', { result: data });
        });
    }
});
/*server.route({
    method: 'GET',
    path: '/teams/{id}',
    handler: function (request, reply) {
        const teamID = encodeURIComponent(request.params.id);

        Request.get(`http://api.football-data.org/v1/teams/${teamID}`, function (error, response, body) {
            if (error) {
                throw error;
            }

            const result = JSON.parse(body);

            Request.get(`http://api.football-data.org/v1/teams/${teamID}/fixtures`, function (error, response, body) {
                if (error) {
                    throw error;
                }

                const fixtures = LodashTake(LodashFilter(JSON.parse(body).fixtures, function (match) {
                    return match.status === 'SCHEDULED';
                }), 5);

                reply.view('team', { result: result, fixtures: fixtures });
            });
        });
    } 
}); */


// A simple helper function that extracts team ID from team URL
Handlebars.registerHelper('teamID', function (teamUrl) {
    //return teamUrl.slice(38);
	return 25;
}); 
 
server.start((err) => {
    if (err) {
        throw err;
    }
 
    console.log(`Server running at: ${server.info.uri}`);
});
