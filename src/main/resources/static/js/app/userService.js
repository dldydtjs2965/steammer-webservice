$( document ).ready(function() {
    bookMarkView();
});

function bookMarkView(){
    if ($("#user").length){
        $.ajax({
            type: "GET",
            url:"api/v2/userGameList",
            contentType: "application/json",
            success: function (response) {
                for (let game of response) {
                    $("#"+game['gameId']).removeClass("fa-bookmark-o").addClass("fa-bookmark")
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                $('html').scrollTop(0);
            }
        })
    }

}

function bookMarkCheck(gameId){
    let element = $("#"+gameId.toString())
    if(element.hasClass("fa-bookmark-o")){
        addBookMark(gameId)
    }else{
        cancelBookMark(gameId)
    }
}

function cancelBookMark(gameId) {
    if ($("#user").length) {
        $.ajax({
            type: "DELETE",
            url: "api/v2/userGameCancel",
            data:  JSON.stringify(gameId),
            contentType: "application/json",
            success: function (response) {
                $("#"+gameId.toString()).removeClass("fa-bookmark").addClass("fa-bookmark-o")
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                $('html').scrollTop(0);
            }
        })
    }
}

function addBookMark(gameId) {
    if ($("#user").length) {
        $.ajax({
            type: "POST",
            url: "api/v2/userGameSave",
            data:  JSON.stringify(gameId),
            contentType: "application/json",
            success: function (response) {
                $("#"+gameId.toString()).removeClass("fa-bookmark-o").addClass("fa-bookmark")
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                $('html').scrollTop(0);
            }
        });
    } else {
        alert("로그인 후 이용가능한 서비스 입니다.")
    }
}

