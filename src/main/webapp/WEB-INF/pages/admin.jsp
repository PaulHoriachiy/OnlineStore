<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 23.06.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Admin</title>
<jsp:include page="header.jsp" flush="true" />

<div class="container-fluid" style="min-height: 100vh; position: relative; padding-top: 25px;">
    <div class="row">
        <div class="col-3 pt-3">
            <div class="dropdown dropright">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <i class="fa fa-cogs"></i>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu">
                    <button class="dropdown-item" type="button">Action</button>
                    <button class="dropdown-item" type="button">Another action</button>
                    <button class="dropdown-item" type="button">Something else here</button>
                </div>
            </div>
        </div>
        <div class="col-9">
            <div class="row">

            </div>
        </div>
    </div>
    <div class="row align-content-center justify-content-center bg-light p-2 text-primary bg-secondary rounded"
         style="position: absolute;bottom: 0px; width: 100%;">
        <jsp:include page="footer.jsp"/>
    </div>
</div>
