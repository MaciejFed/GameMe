var webpack = require('webpack');

module.exports = {
    prod: {
        activeByDefault: true,
        vars: {
            API_URL: "'http://35.187.40.205:8080'"
        }
    },
    prod_mock: {
        vars: {
            API_URL: "'http://35.187.40.205:3000'"
        }
    },
    dev: {
        vars: {
            API_URL: "'http://localhost:8080'"
        }
    },
    dev_mock: {
        vars: {
            API_URL: "'http://localhost:3000'"
        }
    }
};
