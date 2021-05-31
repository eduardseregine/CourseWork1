<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Language selection</title>
</head>

<h1><spring:message code="greeting" text="default"/></h1>

<span th:text="#{lang.change}"></span>:
<select id="locales">
    <option value=""></option>
    <option value="en" th:text="#{lang.eng}"></option>
    <option value="fr" th:text="#{lang.fr}"></option>
</select>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('internationalisation?lang=' + selectedOption);
        }
    });
});

</script>


<body>

</body>
</html>