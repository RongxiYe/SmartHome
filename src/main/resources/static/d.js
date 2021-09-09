function option() {
    layer.open({
        type: 1,
        title: false,
        area: ['1270px', '370px'],
        closeBtn: 0,
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: '\<li style="padding:20px;" class="dropdown"\>' +
            '<a href=" " id="div1">< img border="0" src="photo/light.png" /></a >' +
            '<a href="addwindow.jsp" id="div1">< img border="0" src="photo/window.png"/></a >' +
            '<a href="adddoor.jsp" id="div1">< img border="0" src="photo/door.png" /></a >' +
            '<a href="addair.jsp" id="div1">< img border="0" src="photo/airconditioner.png" /></a >' +
            '\<\/li\>'
    });
}