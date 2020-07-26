$(document).ready(function () {
    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        alert("abc");
        event.preventDefault();
        fire_ajax_submit();

    });

});

function fire_ajax_submit() {
    var search = {}
    search["username"] = $("#username").val();
    var token = $("input[name='_csrf']").val();
    search["_csrf"] = token;

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        headers: {"X-CSRF-TOKEN": token},
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.stringify(data);
            $('#feedback').html(json);
            console.log("SUCCESS : ", data.msg);
            console.log("result : ", data.result);
            $("#btn-search").prop("disabled", false);
        },
        error: function (e) {
            var json = e.responseText;
            $('#feedback').html(json);
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
        }
    });

}