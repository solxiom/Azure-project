/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = init;

function init() {
    console.log("Hello World ...");
    $('#head').load('/photomash/view/header.html');
    indexSidebarInit();
    loadDefaultListThubms();

    //last thing
//    $('body').css("visibility","visible");

}
function indexSidebarInit() {
    var sidebar = $('#indexSidebar');
    var links = new Array();
    if (sidebar.length > 0) {

        $.getJSON('/photomash/photo/default/list', function(data) {

            $.each(data, function(index, value) {
                console.log(value);
                var imgLink = value;
                var last_index = value.split('/').length - 1;
                var imgName = value.split('/')[last_index];
                var img = $('<img src=' + imgLink + ' alt=' + imgName + '/>');
                if(index <5){
                sidebar.append(img);
                sidebar.append($('<br />'));
                }
                links.push(imgLink);
            });
            sidebarSlide(links);
        });
    }
}
function sidebarSlide(links) {

    setInterval(function() {
        var prevElementSrc = $('#indexSidebar img').first().attr('src');
        $('#indexSidebar').hide(1000);
        var sidebar = $('#indexSidebar img').each(function() {
            var randomIndex;
            var newSrc;
            do {
                randomIndex = Math.floor(Math.random() * links.length);
                newSrc = links[randomIndex];
            } while (newSrc === prevElementSrc || newSrc === $(this).attr('src') );
            
            console.log("new src" + newSrc+" prev: " + prevElementSrc + " this" + $(this).attr('src'));
            $(this).attr('src', newSrc);
            prevElementSrc = $(this).attr('src');
            
//            console.log("... ",links[randomIndex]);

        });
        $('#indexSidebar').show(1000);
        
    }, 15000);
//    setInterval(function(){$('#indexSidebar').hide(7000);
//    $('#indexSidebar').show(7000);},7000);
    
}

function loadDefaultListThubms() {
    var div = $('#create_album_thumbs');
    if (div.length > 0) {

        $.getJSON('/photomash/photo/default/list', function(data) {
            div.append($('<figure id="default_thumbs_figure"></figure>'));
            $.each(data, function(index, value) {
                console.log(value);
                var imgLink = value;
                var last_index = value.split('/').length - 1;
                var imgName = value.split('/')[last_index];

                var checkbox_str = '<input type="checkbox" value="' + imgName + '" name="img' + index + '" />';
                var span = $('<span>' + checkbox_str + '<img src=' + imgLink + ' alt=' + imgName + ' /> </span>');
                $('#default_thumbs_figure').append(span);
            });
        });
    }
}
