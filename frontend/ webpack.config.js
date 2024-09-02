// webpack.config.js
var path = require('path');

module.exports = {
    mode: 'development',
    entry: './src/index.js',
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js'
    },

    node: {
        console: false,
        fs: 'empty',
        net: 'empty',
        tls: 'empty'
    },

    devServer: {
        headers: {
            'Access-Control-Allow-Origin': '*'
        }
    },

};