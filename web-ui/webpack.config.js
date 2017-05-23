var webpack = require('webpack');
var path = require('path');
var webpackProfiles = require('webpack-profiles');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');

var config = {
    devtool: 'inline-source-map',
    entry: [
        'webpack-dev-server/client?http://0.0.0.0',
        'webpack/hot/only-dev-server',
        './src'
    ],
    output: {
        path: path.join(__dirname, 'public'),
        filename: 'bundle.js'
    },
    module: {
        loaders: [
        {
            test: /\.jsx?$/,
            exclude: /node_modules/,
            loader: 'babel-loader',
            query: {
                presets: ['react', 'es2015',  'stage-2'],
                plugins: ['transform-class-properties', 'transform-decorators-legacy']
            }
        },
        {
            test: /\.css$/,
            use: [ 'style-loader', 'css-loader' ]
        },{
            test: /\.(jpe?g|png|gif|svg)$/i,
            loader: 'file-loader',
            options: {
                name: '[path][name].[hash].[ext]'
            }
        }]
    },
    node: {
        fs: "empty"
    },
    devServer: {
        publicPath: '/',
        historyApiFallback: true,
        disableHostCheck: true
    },
    plugins: [
        new ExtractTextPlugin('styles.css'),
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({
            template: 'index.ejs',
            inject: 'body',
        })
    ]
};

module.exports = webpackProfiles(config, {profilesFilename: 'profiles.js'});
