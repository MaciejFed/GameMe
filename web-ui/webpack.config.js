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
            loaders: ['react-hot', 'babel?presets[]=react,presets[]=es2015']
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
                loaders: [
                    'file?hash=sha512&digest=hex&name=[hash].[ext]',
                    'image-webpack?bypassOnDebug&optimizationLevel=7&interlaced=false'
                ]
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
