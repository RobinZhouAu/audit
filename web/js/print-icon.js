/**
 * Created by zhouhaibin on 2016/9/19.
 */
var ObjectManager = function(){
    var f;
    var materials = [
        "24px-Average_Materials_Icon.png",
        "24px-Excellent_Materials_Icon.png",
        "24px-Extraordinary_Materials_Icon.png",
        "24px-Fair_Materials_Icon.png",
        "24px-Good_Materials_Icon.png",
        "24px-Enchanted_Trinket_Icon.png"
    ];

    var elements = [
        "24px-Earth_(Element)_Icon.png","24px-Energy_(Element)_Icon.png",
        "24px-Fire_(Element)_Icon.png","24px-Legendary_(Element)_Icon.png",
        "24px-Light_(Element)_Icon.png","24px-Metal_(Element)_Icon.png",
        "24px-Plant_(Element)_Icon.png","24px-Shadow_(Element)_Icon.png",
        "24px-Void_(Element)_Icon.png","24px-Water_(Element)_Icon.png",
        "24px-Wind_(Element)_Icon.png"
    ];
    return{
        init: function() {
            f = this;
            var $container = $("#object-container");
            var html = [];
            for (var k = 0; k < materials.length; k ++) {
                for (var i = 0; i < elements.length; i++) {
                    html.push('<tr>');
                    for (var j = 0; j < 10; j++) {
                        html.push('<td>');
                        html.push('<img src="img/' + elements[i] + '" width="24" height="24">');
                        html.push('<img src="img/' + materials[k] + '" width="24" height="24">');
                        html.push('</td>');
                    }
                    html.push('</tr>');
                    html.push('<tr>');
                    for (var j = 0; j < 10; j++) {
                        html.push('<td>');
                        html.push('<img src="img/' + elements[i] + '" width="24" height="24">');
                        html.push('<img src="img/' + materials[k] + '" width="24" height="24">');
                        html.push('</td>');
                    }
                    html.push('</tr>');
                }
            }
            $container.html(html.join(''));
        },


        empty: null
    }
}();

$(document).ready(function() {
    ObjectManager.init();
});

