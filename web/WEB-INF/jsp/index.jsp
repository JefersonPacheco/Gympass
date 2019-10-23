<%-- 
    Document   : index
    Created on : 23/10/2019, 13:13:02
    Author     : Jeferson Pacheco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
        <title>Teste - Gympass</title>
        <link rel="stylesheet" href="<c:url value="assets/css/bootstrap.css"/>" type="text/css">
        <link rel="stylesheet" href="<c:url value="assets/css/style.css" />" type="text/css">
    </head>

    <body color="#fff">
            <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        &nbsp;&nbsp;<img src="http://drty4o3baw9rh.cloudfront.net/assets/logo/default-90ad3c34361f65527aa42f7b10e2afc9.svg" width="100px" height="50px">
  <div style="color: gray;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;">Seja bem vindo(a) ! 
        </nav>   
        <div class="wrapper">
            <form class="form-signin" action="Upload" method="post" enctype="multipart/form-data">       
                <h3 class="form-signin-heading">Finalizar Corrida</h3>
                <label>Por favor, insira o log .txt da corrida:</label>
                <input type="file" name="file"/>
                <br><br>
                <button class="btn btn-danger btn-block" style="background: #fa5639" type="submit">Enviar</button><br><br>
                <div class="${mensagem}" role="alert">
                    ${mensagem2}     <!-- Chama a mensagem do servlet em caso de erro -->
                </div>
            </form>
            <hr>
            <div class="panel-body">
                <c:if test="${listaVencedores != null}">
                <div class="table-responsive">
                    <table class="table table-hover" id="dataTables-example" style="background-color: #fff">
                        <thead>
                            <tr>
                                <th>Posição Chegada</th> 
                                <th>Cod. Piloto</th>
                                <th>Piloto</th>
                                <th>Qtde. de Voltas</th>
                                <th>Tempo Total de Prova</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${listaVencedores}">
                                <tr class="odd gradeX">
                                    <td>${u.colocacao}</td>  
                                    <td>${u.codPiloto}</td>
                                    <td>${u.piloto}</td>
                                    <td>${u.numVolta}</td>
                                    <td>${u.tempoVolta}</td>
                                </tr>
                            </c:forEach> <!-- Não esquecer de importar a biblioteca JSTL -->
                        </tbody>
                    </table>
                    
                    <table class="table table-striped table-hover" id="dataTables-example" style="background-color: #fff">
                        <thead>
                            <tr>
                                <th>Bônus</th>
                                <th>Cod. Piloto</th>
                                <th>Piloto</th>
                                <th>Num. da Volta</th>
                                <th>Tempo da Volta</th>
                            </tr>
                        </thead>
                        <tbody>
                                <tr class="odd gradeX">
                                    <td>Melhor Volta</td>
                                    <td>${melhorVolta.codPiloto}</td>
                                    <td>${melhorVolta.piloto}</td>
                                    <td>${melhorVolta.numVolta}</td>
                                    <td>${melhorVolta.tempoVolta}</td>
                                </tr>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>
        </div>
    </body>
</html>