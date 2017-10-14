$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null) {
        return null;
    }
    else {
        return results[1] || 0;
    }
};

$(function () {
    if ($.urlParam('loginerror') == "true") {
        $('#custom-modal').modal('hide');
        $('#login-modal').modal('show');
    }
});

function showModal(text) {
    $('#login-modal').modal('hide');
    $('#custom-modal-paragraph').html(text);
    $('#custom-modal').modal('show');
}

function supports_html5_storage() {
    try {
        return 'localStorage' in window && window['localStorage'] !== null;
    } catch (e) {
        return false;
    }
}