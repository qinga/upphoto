layui.define(["layer", "jquery"], function (exports) {
    var login = {
        submit: function (url, data, targetUrl) {
            layui.jquery.ajax({
                url: url,
                data: data,
                type: "POST",
                success: function (res) {
                    console.log(res)
                    if (res.msg == "SUCCESS" && res.status == "200") {
                        window.location.href = targetUrl;
                    } else {
                        vertify = false;
                        layui.jquery(".error_msg")
                            .show({duration: 500})
                            .css({'transform': 'scale(1.1)', 'transform-origin': '0px 0px'})
                    }
                },
                beforeSend: function () {
                    layer.load(0, {shade: [0.5, '#393D49']})
                },
                complete: function () {
                    // 完成加载后关闭loading
                    layer.close();
                }
            })
        }
    };
    exports("login", login);
});
