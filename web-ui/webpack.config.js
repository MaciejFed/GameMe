var webpack = require('webpack');
var path = require('path');
var webpackProfiles = require('webpack-profiles');

var config = {
    devtool: 'inline-source-map',
    entry: [
        'webpack-dev-server/client?http://localhost:80/',
        'webpack/hot/only-dev-server',
        './src'
    ],
    output: {
        path: path.join(__dirname, 'public'),
        filename: 'bundle.js'
    },
    resolve: {
        modulesDirectories: ['node_modules', 'src'],
        extensions: ['', '.js']
    },
    module: {
        loaders: [
        {
            test: /\.jsx?$/,
            exclude: /node_modules/,
            loaders: ['react-hot', 'babel?presets[]=react,presets[]=es2015']
        }
        ]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin()
    ]
};

module.exports = webpackProfiles(config, {profilesFilename: 'profiles.js'});