#!/usr/bin/env bash

if [ "$1"=="prod_mock" ]; then
    node mock_server/mock-server.js &
fi

webpack-dev-server $1 --port 3001 --host 0.0.0.0