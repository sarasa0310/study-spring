$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/greeting-global-config"
    }).then(function(data, status, jqxhr) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
        console.log(jqxhr);
    });
});