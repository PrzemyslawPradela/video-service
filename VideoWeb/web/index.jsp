<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filmy</title>
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

        <jsp:include page="IndexServlet"/>
        <div class="row mt-5 mb-5 mr-auto ml-auto">
            <c:forEach var="video" items="${videos}">
                <div class="col-lg-2 col-md-3 col-sm-6">
                    <div class="card mb-3">
                        <h3 class="card-header" style="text-align: center">${video.title}</h3>
                        <video style="height: 200px; width: 100%; display: block;" src="data:video/mp4;base64,${video.videoBase64String}" controls></video>
                        <div class="card-body" style="text-align: center">
                            <a href="#" class="card-link" style="float: left">
                                <i class="fa fa-edit fa-3x"></i>
                            </a>
                            <a href="DownloadServlet?id=${video.id}" class="card-link">
                                <i class="fa fa-download fa-3x"></i>
                            </a>
                            <a href="RemoveServlet?id=${video.id}" class="card-link" style="float: right">
                                <i class="fa fa-trash fa-3x"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
