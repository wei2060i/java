layui.use(['form', 'layer'], function () {
    var form = layui.form;
    var layer = layui.layer;
    // 添加验证规则
    form.verify({
        categoryname: function (value, item) {
            value = value.trim();
            if (value.length < 0) {
                return "请输入类别名称";
            }
        }
    });

    form.on('submit(*)', function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        var context = data.field;
        var url = "/category_update";

        $.ajax({
            url: url,
            data: context,
            dataType: "text",
            success: function (data) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);  // 关闭layer

                if (data == 0) {
                    parent.layer.msg("操作成功", {icon: 6});
                    parent.table.reload('category', {
                        page: {
                            curr: 1 // 重新从第1页开始
                        },
                        where: {
                            'keyword': $("#keyword").val()
                        }
                    });
                } else {
                    parent.layer.msg("操作失败", {icon: 5});
                }
            }, error: function () {
                parent.layer.msg("操作失败", {icon: 5});
            }
        });
        return false;
    });
});