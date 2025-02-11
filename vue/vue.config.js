const { defineConfig } = require('@vue/cli-service')

module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': '' // 将以/api开头的请求路径重写为目标服务器的请求路径
        }
      }
    }
  }
};


