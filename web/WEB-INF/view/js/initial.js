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
    loadNewAlbumsForIndex();
//    setNewAlbumLinksAction();

    //last thing
//    $('body').css("visibility","visible");

}

function indexSidebarInit() {
    var sidebar = $('#indexSidebar');
    var links = new Array();
    if (sidebar.length > 0) {

        $.getJSON('/photomash/photo/default/list', function(data) {

            $.each(data, function(index, value) {
//                console.log(value);
                var imgLink = value;
                var last_index = value.split('/').length - 1;
                var imgName = value.split('/')[last_index];
                var img = $('<img src=' + imgLink + ' alt=' + imgName + '/>');
                if (index < 5) {
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
//        console.log("... inja residim");
        var prevElementSrc = $('#indexSidebar img').first().attr('src');
//         console.log("... inja ham" + $('#indexSidebar img').length);
        $.each($('#indexSidebar img'), function() {
            $(this).hide(1000);
        });

        var sidebar = $('#indexSidebar img').each(function() {
            var randomIndex;
            var newSrc;
            do {
                randomIndex = Math.floor(Math.random() * links.length);
                newSrc = links[randomIndex];
            } while (newSrc === prevElementSrc || newSrc === $(this).attr('src'));

//            console.log("new src" + newSrc+" prev: " + prevElementSrc + " this" + $(this).attr('src'));
            $(this).attr('src', newSrc);
            prevElementSrc = $(this).attr('src');

//            console.log("... ",links[randomIndex]);

        });
        $.each($('#indexSidebar img'), function() {
            $(this).show(1000);
        });

    }, 10000);
//    setInterval(function(){$('#indexSidebar').hide(7000);
//    $('#indexSidebar').show(7000);},7000);

}

function loadDefaultListThubms() {
    var div = $('#create_album_thumbs');
    if (div.length > 0) {

        $.getJSON('/photomash/photo/default/list', function(data) {
            div.append($('<figure id="default_thumbs_figure"></figure>'));
            $.each(data, function(index, value) {
//                console.log(value);
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
function loadNewAlbumsForIndex() {
    var section = $('#newAlbumsSection');
    if (section.length > 0) {

        $.getJSON('/photomash/photo/albums/list', function(data) {
            $.each(data, function(index, album) {
                console.log(album.title);
                var div = $('<div class="newAlbum"></div>');
                var head = $('<div class="newAlbumHeader"> <h3>' + album.title
//                        + '</h3> <a href="/photomash/photo/albums/' + album.uniqueKey + '">view this album</a></div>');
                        + '</h3></div>');
                var anchor = $('<a href="#"> view this album</a>');
                anchor.click(function(event) {
                    event.preventDefault();
                    clickAlbum(album.uniqueKey);
                });
                head.append(anchor);
                div.append(head);
                var span = $('<span></span>');
                $.each(album.imagePathsAsArray, function(index, path) {
                    if (index >= 3) {
                        return false;
                    }
                    var last_index = path.split('/').length - 1;
                    var img = $('<img src="' + path + '" alt="' + path.split('/')[last_index] + '"/>');
                    span.append(img);

                });
                div.append(span);

                section.append(div);


            });
        });
    }

}

function clickAlbum(album_key) {
    var link = "/photomash/photo/albums/" + album_key;
    console.log("inside...link: " + link);
    $.getJSON(link, function(data) {
        var div = $('<div id="album_info_div"></div>');
        var h3 = $('<h3>Title: ' + data.title + '</h3>');
        var span = $('<span >Tags: ' + data.tags + '</span><br/>');
        var p = $('<p >Description: ' + data.description + '</p>');
        div.append(h3);
        div.append(span);
        div.append(p);

        $.each(data.imagePathsAsArray,function(index,path){
                var img = $('<img src='+path+' alt="kosesher"/>');
                div.append(img);
            
        });
        $('#newAlbumsSection').empty();
        $('#newAlbumsSection').append(div);

//            $.each(data, function(index, value) {
////                console.log(value);
//                var imgLink = value;
//                var last_index = value.split('/').length - 1;
//                var imgName = value.split('/')[last_index];
//
//                var checkbox_str = '<input type="checkbox" value="' + imgName + '" name="img' + index + '" />';
//                var span = $('<span>' + checkbox_str + '<img src=' + imgLink + ' alt=' + imgName + ' /> </span>');
//                $('#default_thumbs_figure').append(span);
//            });
    });
}
