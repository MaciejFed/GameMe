#!/usr/bin/env bash

if ["$1"== "prod_mock"]; then
    json-server --watch mock_server/gameMe.json &
fi

webpack-dev-server --profiles=$1 --progress --colors --port 80 --host 0.0.0.0