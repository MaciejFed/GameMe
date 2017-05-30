const webpack = require('webpack');
const path = require('path');
const webpackProfiles = require('webpack-profiles');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');


var config = {
    devtool: 'inline-source-map',
    entry: [
        'webpack-dev-server/client?http://0.0.0.0:8081',
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
        }),
        new UglifyJSPlugin(),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('production')
        })
    ]
};

module.exports = webpackProfiles(config, {profilesFilename: 'profiles.js'});
