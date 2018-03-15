<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<s:eval expression="@environment.getProperty('unitforum.domain.static')" var="staticDomain" />
<meta id="viewport" name="viewport" content="width=980,user-scalable=no" />
<link rel="stylesheet" href="${staticDomain}/css/style.css" />
<script src="${staticDomain}/js/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="${staticDomain}/js/jquery.actual.min.js" type="text/javascript"></script>