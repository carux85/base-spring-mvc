<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="lang.change" text="default"/></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<span><spring:message code="lang.change" text="default"/></span>:
<select id="locales">
    <option value=""></option>
    <option value="en"><spring:message code="lang.eng" text="default"/></option>
    <option value="fr"><spring:message code="lang.fr" text="default"/></option>
</select>

<script type="text/javascript">
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('international?lang=' + selectedOption);
        }
    });
});
</script>
</body>
</html>