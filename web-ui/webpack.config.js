var webpack = require('webpack');
var path = require('path');
var webpackProfiles = require('webpack-profiles');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var combineLoaders = require('webpack-combine-loaders');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var config = {
    devtool: 'inline-source-map',
    entry: [
        'webpack-dev-server/client?http://0.0.0.0:80/',
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
            loader: 'babel-loader',
            query: {
                presets: ['react', 'es2015'],
                plugins: ['transform-class-properties', 'transform-decorators-legacy']
            }
        },
        {
            test: /\.css$/,
            loader: ExtractTextPlugin.extract(
                'style-loader',
                combineLoaders([{
                    loader: 'css-loader',
                    query: {
                        modules: true,
                        localIdentName: '[name]__[local]___[hash:base64:5]'
                    }
                }])
         )}
         ,{
                test: /\.(jpe?g|png|gif|svg)$/i,
                loader: 'file-loader',
                options: {
                    name: '[path][name].[hash].[ext]'
                }
            }

        ]
    },
    plugins: [
        new ExtractTextPlugin('styles.css'),
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin(),
        new HtmlWebpackPlugin({
            template: 'index.ejs',
            inject: 'body',
        })
    ]
};

module.exports = webpackProfiles(config, {profilesFilename: 'profiles.js'});
