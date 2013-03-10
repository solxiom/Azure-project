/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = init;

function init() {
    console.log("Hello World ...");
    $('#head').load('/photomash/view/header.html');
    loadDefaultListThubms();

    //last thing
//    $('body').css("visibility","visible");

}
function loadDefaultListThubms() {
    var div = $('#create_album_thumbs');
    if (div.length > 0) {

        $.getJSON('/photomash/photo/default/list', function(data) {
            div.append($('<figure id="default_thumbs_figure"></figure>'));
            $.each(data, function(index, value) {
                console.log(value);
                var imgLink = value;
                 var last_index =value.split('/').length-1;
                var imgName = value.split('/')[last_index];
               
                var checkbox_str = '<input type="checkbox" value="'+imgName+'" name="img"'+index+' />';
                var span = $('<span>'+checkbox_str+'<img src='+imgLink+' alt='+imgName+' /> </span>');
                $('#default_thumbs_figure').append(span);
            });
        });
    }
}
