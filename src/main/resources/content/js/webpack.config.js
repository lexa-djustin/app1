'use strict';

module.exports = {
    entry: './bundles/index',
    output: {
        path: __dirname + '/bundles',
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,         // Match both .js and .jsx files
                exclude: /node_modules/,
                loader: "babel-loader",
                query:
                    {
                        presets:['react']
                    }
            }
        ]
    },
    resolve: {
        extensions: ['*', '.js', '.jsx']
    },
    watch: true,
    watchOptions: {
        aggregateTimeout: 100
    }
};