layui.define(["layer"], function (exports) {
    var index = {
        inner: function (msg) {
            layer.msg(msg)
        }
    };
    exports("index", index)
});
