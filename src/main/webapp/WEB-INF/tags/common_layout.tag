<!DOCTYPE html>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<%@attribute name="title"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="body" fragment="true" %>
<html>
<head>
<ftt:common_header />
<title>${title}</title>
<jsp:invoke fragment="header"/>
</head>
<body>
<jsp:invoke fragment="body"/>
</body>
</html>