
if %1==mock (
    start json-server mock_server/gameMe.json
)

webpack-dev-server --profiles=%1 --progress --colors --port 80 --host 0.0.0.0