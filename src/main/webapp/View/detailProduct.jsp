<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết sp</title>
</head>
<body>

<c:forEach items="${products.imageUrls}" var="url">
    <img width="150px" height="150px" style="object-fit: cover" src="<%=request.getContextPath()%>/img/${url}" alt="url">

</c:forEach>
<img width="150px" height="150px" style="object-fit: cover" src="<%=request.getContextPath()%>/img/${products.img}" alt="url">

</body>
</html>