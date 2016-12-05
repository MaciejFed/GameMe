if %1==dev_mock (
    start node mock_server/mock-server.js
)

webpack-dev-server --profiles=%1 --progress --colors --port 80 --host 0.0.0.0