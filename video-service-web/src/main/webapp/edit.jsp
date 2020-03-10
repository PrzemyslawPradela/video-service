<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edycja filmu</title>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/journal/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="index.jsp">Filmy</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp"><i class="fa fa-home fa-lg"></i> Strona główna <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="add.jsp"><i class="fa fa-plus-square fa-lg"></i> Dodaj film</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container mt-5" style="width: 30%">
            <form action="UpdateVideoServlet" enctype="multipart/form-data" method="post">
                <input type="text" name="id" value="${video.id}" style="display: none">
                <label for="titleInput">Tytuł filmu</label>
                <input class="form-control mb-4" id="titleInput" maxlength="1024" name="title" type="text" value="${video.title}">
                <div class="custom-control custom-switch mb-4">
                    <input type="checkbox" class="custom-control-input" id="customSwitch1" onclick="check()">
                    <label class="custom-control-label" for="customSwitch1">Zmień film</label>
                </div>
                <div class="input-group mb-4">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-film fa-lg"></i></span>
                    </div>
                    <div class="custom-file">
                        <input accept="video/mp4" aria-describedby="inputGroupFileAddon01" class="custom-file-input"
                               id="inputGroupFile01" name="file" type="file" disabled>
                        <label class="custom-file-label" for="inputGroupFile01">Wybierz film</label>
                    </div>
                </div>
                <div class="mb-5" style="text-align: center">
                    <button class="btn btn-primary" id="submit" type="submit"><i class="fa fa-check-circle fa-lg"></i> Zatwierdź</button>
                </div>
            </form>
            <video style="height: 400px; width: 100%; display: block;" src="data:video/mp4;base64,${video.videoBase64String}" controls></video>
        </div>

        <script>
            function check() {
                var checkBox = document.getElementById("customSwitch1");
                var inputFile = document.getElementById("inputGroupFile01");
                if (checkBox.checked === true) {
                    inputFile.disabled = false;
                } else {
                    inputFile.disabled = true;
                }
            }
        </script>
    </body>
</html>
