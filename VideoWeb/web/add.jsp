<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dodaj film</title>
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
                        <a class="nav-link" href="index.jsp"><i class="fa fa-home"></i> Strona główna <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="add.jsp"><i class="fa fa-plus-square"></i> Dodaj film</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container mt-5" style="width: 30%">
            <form action="UploadServlet" enctype="multipart/form-data" method="post">
                <label for="titleInput">Tytuł filmu</label>
                <input class="form-control mb-4" id="titleInput" maxlength="1024" name="title" onkeyup="success()" type="text">
                <div class="input-group mb-4">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-film"></i></span>
                    </div>
                    <div class="custom-file">
                        <input accept="video/mp4" aria-describedby="inputGroupFileAddon01" class="custom-file-input"
                               id="inputGroupFile01" name="file" onchange="success()" type="file">
                        <label class="custom-file-label" for="inputGroupFile01">Wybierz film</label>
                    </div>
                </div>
                <div style="text-align: center">
                    <button class="btn btn-primary" id="submit" type="submit" disabled><i class="fa fa-upload"></i> Dodaj</button>
                </div>
            </form>
        </div>

        <script>
            function success() {
                document.getElementById('submit').disabled = document.getElementById('titleInput').value === '' || document.getElementById('inputGroupFile01').value === '';
            }

            $('#inputGroupFile01').on('change', function () {
                const fileName = $(this).val();
                $(this).next('.custom-file-label').html(fileName);
            })
        </script>
    </body>
</html>
