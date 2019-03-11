<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html: charset=utf-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Currency exchanger</title>
        <link href="${pageContext.request.contextPath}/resources/css/index_styles.css" type="text/css" rel="stylesheet">
	    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	    <script src="${pageContext.request.contextPath}/resources/scripts/scripts.js" type="text/javascript"></script>
	</head>
	<body>

    <spring:message code="currency_code_label" var="currencyCodeLabel"/>
    <spring:message code="currency_name_label" var="currencyNameLabel"/>
    <spring:message code="operation_label" var="operationLabel"/>
    <spring:message code="hrn_label" var="summHrnLabel"/>
    <spring:message code="curr_label" var="sumCurrLabel"/>
    <spring:message code="date_label" var="dateLabel"/>
    <spring:message code="operator_label" var="operatorLabel"/>
    <spring:message code="operation_list_label" var="operationListLabel"/>

    <script type="text/javascript">
        $(function(){
            $("#list").jqGrid({
                url:'exchanger/operation',
                datatype: 'json',
                mtype: 'GET',
                colNames:['${currencyCodeLabel}', '${currencyNameLabel}','${operationLabel}'
                        ,'${summHrnLabel}','${sumCurrLabel}','${dateLabel}','${operatorLabel}'],
                colModel :[
                    {name:'currencyCode', index:'rateViewDto.currencyViewDto.code', width:50},
                    {name:'currencyName', index:'rateViewDto.currencyViewDto.name', width:100},
                    {name:'operationName', index:'rateViewDto.currencyViewDto.code', width:70},
                    {name:'summHrn', index:'birthDate', width:50},
                    {name:'summCurr', index:'birthDate', width:50},
                    {name:'date', index:'birthDate', width:100},
                    {name:'operator', index:'birthDate', width:100}
                ],
                jsonReader : {
                    root:"catalogItemViewDto",
                    page: "currentPage",
                    total: "totalPages",
                    records: "totalRecords",
                    repeatitems: false,
                    id: "id"
                },
                pager: '#pager',
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'date',
                sortorder: 'asc',
                viewrecords: true,
                gridview: true,
                height: 250,
                width: 500,
                caption: '${operationListLabel}',
                onSelectRow: function(id){
                    document.location.href ="exchanger/operation" + id;
                }
            });
        });














        function getCatalog(){
            var catalogView = $('div[id="catalogView"]');
            catalogView.html('');

            var divRow = document.createElement('div');
            divRow.className = "row";
            divNoGutters = document.createElement('div');
            divNoGutters.className = "row no-gutters";

            var divCol1 = document.createElement('div');
            divCol1.className = "col";
            var divAlert1 = document.createElement('div');
            divAlert1.className = "alert alert-secondary";
            var title1 = document.createTextNode('<spring:message code="currency_code_label"/>');
            divAlert1.appendChild(title1);
            divCol1.appendChild(divAlert1);
            divNoGutters.appendChild(divCol1);

            var divCol2 = document.createElement('div');
            divCol2.className = "col-5";
            var divAlert2 = document.createElement('div');
            divAlert2.className = "alert alert-secondary";
            var title2 = document.createTextNode('<spring:message code="currency_name_label"/>');
            divAlert2.appendChild(title2);
            divCol2.appendChild(divAlert2);
            divNoGutters.appendChild(divCol2);

            var divCol3 = document.createElement('div');
            divCol3.className = "col";
            var divAlert3 = document.createElement('div');
            divAlert3.className = "alert alert-secondary";
            var title3 = document.createTextNode('<spring:message code="buy_label"/>');
            divAlert3.appendChild(title3);
            divCol3.appendChild(divAlert3);
            divNoGutters.appendChild(divCol3);

            var divCol4 = document.createElement('div');
            divCol4.className = "col";
            var divAlert4 = document.createElement('div');
            divAlert4.className = "alert alert-secondary";
            var title4 = document.createTextNode('<spring:message code="sale_label"/>');
            divAlert4.appendChild(title4);
            divCol4.appendChild(divAlert4);
            divNoGutters.appendChild(divCol4);

            var divCol5 = document.createElement('div');
            divCol5.className = "col";
            var divAlert5 = document.createElement('div');
            divAlert5.className = "alert alert-secondary";
            var title5 = document.createTextNode('<spring:message code="nbu_label"/>');
            divAlert5.appendChild(title5);
            divCol5.appendChild(divAlert5);
            divNoGutters.appendChild(divCol5);

            jQuery.ajax({
                type: 'GET',
                url: '/exchanger/catalog',
                accepts: 'application/json',
                contentType: 'application/json',
                headers:{'Accept':'application/json', 'Content-Type':'application/json'},
                dataType: 'json',
                statusCode: {
                    406: function() {
                        alert( "406" );
                    }
                },
                statusCode: {
                    422: function() {
                        alert( "422" );
                    }
                },
                statusCode: {
                    400: function() {
                        alert( "400" );
                    }
                },
                statusCode: {
                    404: function() {
                        alert( "page not found" );
                    }
                },
                statusCode: {
                    200: function() {
                        alert( "IT IS OK" );
                    }
                },
                statusCode: {
                    415: function() {
                        alert( "HTTP STATUS 415" );
                    }
                },
                statusCode: {
                    500: function() {
                        alert( "HTTP STATUS 500" );
                    }
                },
                success: function(catalogItems){
                    $.each(catalogItems, function(){

            var divW = document.createElement('div');
            divW.className = "w-100";
            divNoGutters.appendChild(divW);

            var divCol1 = document.createElement('div');
            divCol1.className = "col";
            var divAlert1 = document.createElement('div');
            divAlert1.className = "alert alert-secondary";
            var title1 = document.createTextNode(this.rateViewDto.currencyViewDto.code);
            divAlert1.appendChild(title1);
            divCol1.appendChild(divAlert1);
            divNoGutters.appendChild(divCol1);

            var divCol2 = document.createElement('div');
            divCol2.className = "col-5";
            var divAlert2 = document.createElement('div');
            divAlert2.className = "alert alert-secondary";
            var title2 = document.createTextNode(this.rateViewDto.currencyViewDto.name);
            divAlert2.appendChild(title2);
            divCol2.appendChild(divAlert2);
            divNoGutters.appendChild(divCol2);

            var divCol3 = document.createElement('div');
            divCol3.className = "col";
            var divAlert3 = document.createElement('div');
            divAlert3.className = "alert alert-secondary";
            var title3 = document.createTextNode(this.rateViewDto.buy);
            divAlert3.appendChild(title3);
            divCol3.appendChild(divAlert3);
            divNoGutters.appendChild(divCol3);

            var divCol4 = document.createElement('div');
            divCol4.className = "col";
            var divAlert4 = document.createElement('div');
            divAlert4.className = "alert alert-secondary";
            var title4 = document.createTextNode(this.rateViewDto.sale);
            divAlert4.appendChild(title4);
            divCol4.appendChild(divAlert4);
            divNoGutters.appendChild(divCol4);

            var divCol5 = document.createElement('div');
            divCol5.className = "col";
            var divAlert5 = document.createElement('div');
            divAlert5.className = "alert alert-secondary";
            var title5 = document.createTextNode(this.nbuRateViewDto.price);
            divAlert5.appendChild(title5);
            divCol5.appendChild(divAlert5);
            divNoGutters.appendChild(divCol5);

                    });
                },
                error: function(xhr) {
                    alert(xhr.statusText + xhr.responseText);
                },
                complete: function(){
                    alert( "complete" );
                }
            });

            divRow.appendChild(divNoGutters);
            var element = document.getElementById("catalogView");
            element.appendChild(divRow);
            element.className = "catalogWrapper";

        };
    </script>

        <!--TOP SIDE BAR-->
	    <nav class="navbar navbar-light bg-light">
	        <a class="navbar-brand">
	            <spring:message code="currency_exchanger_label"/>
	        </a>
            <div class="btn-group" role="group" aria-label="Basic example">

                <!--AVAILABLE FOR NOT AUTHENTICATED USERS-->
                <security:authorize access="!isAuthenticated()">

                    <!--LOGIN BUTTON-->
                    <form action="${pageContext.request.contextPath}/login" method="GET">
                        <button type="submit" class="btn btn-outline-success" data-toggle="modal">
                            <spring:message code="login_label"/>
                        </button>
                    </form>

                </security:authorize>

                <!--AVAILABLE FOR AUTHENTICATED USERS-->
                <security:authorize access="isAuthenticated()">

                    <!--LOGOUT BUTTON-->
                    <form action="${pageContext.request.contextPath}/logout" method="POST">
                        <button type="submit" class="btn btn-outline-success">
                            <spring:message code="logout_label"/>
                        </button>
                    </form>

                </security:authorize>

     	        <!--LANGUAGE BUTTONS-->
     	        <div id="localizationFrame">
     	            <span style="float: right">
                        <a href="?lang=en">en</a>
                        |
                        <a href="?lang=ua">ua</a>
                        |
                        <a href="?lang=ru">ru</a>
                    </span>
                </div>
            </div>
        </nav>

        <!--LEFT SIDE BAR-->
    	<div class="left-side-bar">

            <!--OPERATOR BUTTON BAR. Available for user with operator state-->
            <security:authorize access="hasRole('OPERATOR')">

                <!--JOURNAL BUTTON-->
                <form action="${pageContext.request.contextPath}/operator/operation" method="GET">
                    <div class="input-group mb-3">
                        <button type="submit" class="btn btn-outline-primary btn-block">
                            <spring:message code="journal_label"/>
                        </button>
                    </div>
                </form>

                <!--CATALOG BUTTON-->
                <div class="input-group mb-3">
                    <button type="submit" class="btn btn-outline-primary btn-block" onclick="javascript:getCatalog();">
                        <spring:message code="catalog_label"/>
                    </button>
                </div>

            </security:authorize>

            <!--ADMINISTRATOR BUTTON BAR. Available for administrators-->
            <security:authorize access="hasRole('ADMIN')">

                <!--CATALOG BUTTON-->
                <form action="${pageContext.request.contextPath}/catalog" method="GET">
                    <div class="input-group mb-3">
                        <button type="submit" class="btn btn-outline-primary btn-block">
                            <spring:message code="catalog_label"/>
                        </button>
                    </div>
                </form>

                <!--JOURNAL BUTTON-->
                <form action="${pageContext.request.contextPath}/journal" method="GET">
                    <div class="input-group mb-3">
                        <button type="submit" class="btn btn-outline-primary btn-block">
                            <spring:message code="journal_label"/>
                        </button>
                    </div>
                </form>

                <!--USERS BUTTON-->
                <form action="${pageContext.request.contextPath}/users" method="GET">
                    <div class="input-group mb-3">
                        <button type="submit" class="btn btn-outline-primary btn-block">
                            <spring:message code="users_label"/>
                        </button>
                    </div>
                </form>

                <!--CREATE NEW USER BUTTON-->
                <div class="input-group mb-3">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#modalCenter-Register">
                        <spring:message code="new_user_label"/>
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="modalCenter-Register" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">
                                        <spring:message code="register_form"/>
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="${pageContext.request.contextPath}/register" modelAttribute="userRegisterCreateDto" method="POST">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="registerUsername">
                                                <spring:message code="username_label"/>
                                            </label>
                                            <input type="text" name="username" class="form-control" id="registerUsername" placeholder="Enter user name">
                                        </div>
                                        <div class="form-group">
                                            <label for="registerPassword">
                                                <spring:message code="password_label"/>
                                            </label>
                                            <input type="password" name="password" class="form-control" id="registerPassword" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <label>
                                                <spring:message code="user_state_label"/>
                                            </label>
    				                        <div class="input-group mb-3">
    					                        <select id="registerUserState" name="state" class="custom-select">
    						                        <option selected value="OPERATOR"><spring:message code="operator_label"/></option>
    						                        <option value="ADMIN"><spring:message code="admin_label"/></option>
    					                        </select>
    				                        </div>
    				                    </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary">
                                            <spring:message code="register_label"/>
                                        </button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            <spring:message code="close_label"/>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </security:authorize>

    	</div>

        <!--MAIN BAR-->
        <div class="main-bar">
                <!--AVAILABE FOR ADMINISTRATORS-->
                <security:authorize access="hasRole('ADMIN')">

                    <!--CATALOG-->
                    <div id="adminCatalogView">
                    </div>

                    <!--JOURNAL-->
                    <div id="adminJournalView">
                    </div>


                    <!--USERS LIST-->
                    <c:choose>
                        <c:when test="${empty userList}">
                            <div class="input-group mb-3">
                                <h4><spring:message code="empty_users_list"/></h4>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="input-group mb-3">
                                <h4><spring:message code="users_list_label"/></h4>
                            </div>
                            <div class="accordion" id="orderListWrapper">
                                <c:forEach var="user" items="${userList}">
                                    <form action="${pageContext.request.contextPath}/user/${user.id}" modelAttribute="userCreateDto" method="POST">
                                        <div class="alert alert-success" role="alert">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5 class="mb-0">
                                                        <div class="input-group mb-3">
                                                            <span class="input-group-text"><spring:message code="username_label"/>: ${user.username}</span>
                                                            <input type="hidden" name="username" type="text" value="${user.username}">
                                                        </div>
                                                    </h5>
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><spring:message code="user_state_label"/>:</span>
                                                        </div>
    					                                <select id="updateUserState${user.id}" name="state" class="custom-select">
    						                                <option selected value="OPERATOR"><spring:message code="operator_label"/></option>
    						                                <option value="ADMIN"><spring:message code="admin_label"/></option>
    					                                </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <button type="submit" class="btn btn-primary">
                                                    <spring:message code="save_changes_label"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </security:authorize>

                <!--AVAILABE FOR OPERATORS-->
                <security:authorize access="hasRole('OPERATOR')">

                    <!--CATALOG-->
                    <div id="catalogView" class="catalogWrapper">
                    </div>

                    <!--JOURNAL-->
                    <div id="journalView">
                        <div class="row">
                            <div class="row no-gutters">
                                <div class="col">
                                    <div class="alert alert-secondary">
                                        <spring:message code="currency_code_label"/>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div class="alert alert-secondary">
                                        <spring:message code="currency_name_label"/>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="alert alert-secondary">
                                        <spring:message code="operation_label"/>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div class="alert alert-secondary">
                                        <spring:message code="hrn_label"/>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div class="alert alert-secondary">
                                        <spring:message code="curr_label"/>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="alert alert-secondary">
                                        <spring:message code="date_label"/>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div class="alert alert-secondary">
                                        <spring:message code="operator_label"/>
                                    </div>
                                </div>

                                <!--<c:forEach var="catalogItem" items="${catalogItems}">-->
                                    <!--<div class="w-100"></div>-->
                                    <!--<div class="col"><input readonly type="text" value="${catalogItem.rateViewDto.currencyViewDto.code}" class="form-control"></div>-->
                                    <!--<div class="col-5"><input readonly type="text" value="${catalogItem.rateViewDto.currencyViewDto.name}" class="form-control"></div>-->
                                    <!--<div class="col"><input readonly type="text" value="${catalogItem.rateViewDto.buy}" class="form-control"></div>-->
                                    <!--<div class="col"><input readonly type="text" value="${catalogItem.rateViewDto.sale}" class="form-control"></div>-->
                                    <!--<div class="col"><input readonly type="text" value="${catalogItem.nbuRateViewDto.price}" class="form-control"></div>-->
                                <!--</c:forEach>-->
                            </div>
                        </div>


                    </div>

                </security:authorize>

        </div>
	</body>
</html>