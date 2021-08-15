let page = 0;

$(window).scroll(function () {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
        appendGames()
    }
});

function appendGames() {
    page++
    let size = 9
    $.ajax({
        type: "POST",
        url: '/api/v1/appendGames',
        data: JSON.stringify({"page": page, "size": size}),
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            let html = ``
            for (let game of response) {
                let card = `<div class="col-xs-12 col-sm-6 col-md-4 slide-bottom" id="card">
                                        <img class="card-img-top" id="game-img" src="${game['imgUrl']}" alt="Card image cap" onclick="getGameVideo(${game['gameId']})" data-toggle="modal"
                     data-target="#videoModal">
                                        <div class="card-body">
                                            <i class="fa fa-bookmark-o fa-2x" aria-hidden="true" id="${game['gameId']}" onclick="bookMarkCheck(${game['gameId']})"></i>
                                            <h5 class="card-title">${game['gameName']}</h5>
                                            <p class="card-text">${game['gameInfo']}.</p>
                                            <div class="card-sub-body">
                                                <div class="summary column" id="launch-date">출시날짜:</div>
                                                <div class="summary column">
                                                    <span class="game_review_summary mixed" style="margin-left: 20px">${game['launchDate']}</span>
                                                </div>
                                                <div class="subtitle column all" id="review">모든 평가:</div>
                                                <div class="summary column">
                                                    <span class="game_review_summary mixed" style="margin-left: 20px">${game['evaluation']}</span>
                                                </div>
                                                <div class="dev_row">
                                                    <div class="subtitle column" id="dev-company">개발자:</div>
                                                    <span class="column" id="developers_list"
                                                          style="margin-left: 20px">${game['devCompany']}</span>
                                                    <div class="dev_row">
                                                        <div class="subtitle column" id="distributor">배급사:</div>
                                                        <span class="column" style="margin-left: 20px">${game['distributor']}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tag-box">`
                for (let tag of game['gameTags']) {
                    tag = `<a class="btn btn-secondary btn-sm" href="#" role="button" id="game-tag"
                                    itemid="${tag['tagId']}">${tag['tagName']}</a>`
                    card += tag
                }
                html = card + `<button type="button" class="btn btn-secondary btn-sm" data-toggle="modal"
                                                    data-target="#tagModal" onclick="getGameTag(${game['gameId']})">
                                                +
                                        </button>
                                    </div>
                                </div>`
                $('.row').append(html)
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            $('html').scrollTop(0);
        }
    });
}

function getGameTag(gameId) {
    $("#game-tag-list").empty()
    $.ajax({
        type: "GET",
        url: "/api/v1/gameTagResponse",
        data: {gameId: gameId},
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            let gameTags = response
            for (let i = 0; i < gameTags.length; i++) {
                let tagId = gameTags[i]['tag']['tagId']
                let tagName = gameTags[i]['tag']['tagName']

                let html = `<li class="list-group-item" itemid="${tagId}">${tagName}</li>`
                $("#game-tag-list").append(html);
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    })
}

function getGameVideo(gameId) {
    $('#videoModal .modal-body').empty();
    $.ajax({
        type: "GET",
        url: "api/v1/videoResponse",
        data: {gameId: gameId},
        success: function (response) {
            let videoTag = `<video controls width="600">
                                        <source src="${response}"
                                                    type="video/mp4">
                                    </video>`
            $('#videoModal .modal-body').append(videoTag);
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    })
}