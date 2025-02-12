const { defineConfig } = require('@vue/cli-service')

const port = process.env.port || process.env.npm_config_port || 8081

module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: false,
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/dev-api': '' // 将以/dev-api开头的请求路径重写为目标服务器的请求路径
        }
      }
    }
  }
};


