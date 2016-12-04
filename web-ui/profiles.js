var webpack = require('webpack');

module.exports = {
    prod: {
        vars: {
            API_URL: "'http://104.199.61.138:8080'"
        }
    },
    prod_mock: {
        vars: {
                API_URL: "'http://104.199.61.138:3000'"
        }
    },
    dev: {
        vars: {
            API_URL: "'http://localhost:8080'"
        }
    },
    dev_mock: {
        activeByDefault: true,
        vars: {
            API_URL: "'http://localhost:3000'"
        }
    }
};