#!/usr/bin/env bash

if [ "$1"=="prod_mock" ]; then
    node mock_server/mock-server.js &
fi

webpack-dev-server --profiles=$1 --progress --colors --port 80 --host 0.0.0.0