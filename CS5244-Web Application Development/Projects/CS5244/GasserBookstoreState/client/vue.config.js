module.exports = {
  publicPath: "/GasserBookstoreState",
  chainWebpack: (config) => {
    config.plugin("html").tap((args) => {
      args[0].title = "Kitab Lab";
      return args;
    });
  },
};
