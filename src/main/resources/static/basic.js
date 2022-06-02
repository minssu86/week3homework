
$(document).ready(function () {


    if ($.cookie('token')) {
        $.ajaxSetup({
            headers: {
                'Authorization': $.cookie('token')
            }
        })
    }

    $.ajax({
        type: "POST",
        url: `/user/userinfo`,
        contentType: "application/json",
        success: function (response) {
            const username = response.username;
            $('.username').text(username);
            if(username == null){
                $('#header-title-login-user').append(`<br><a href="/user/loginView" style="font-size: 20px">로그인</a>`)
            }else {
                $('#header-title-login-user').append(`<form id="my_form" method="post" action="/user/logout">
        <a id="logout-text" href="javascript:{}" onclick="document.getElementById('my_form').submit();">로그아웃</a>
    </form>`)
            }
        }
    })
})