<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/8/2023
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/ProductsServlet" enctype="multipart/form-data">
    <label for="name">Nhập tên sản phẩm</label>
    <input type="text" id="name" name="name" >

    <label for="description">Mô tả</label>
    <input type="text" id="description" name="description">

    <label for="price">Giá</label>
    <input type="text" id="price" name="price">

    <label for="stock">Số lượng kho</label>
    <input type="text" id="stock" name="stock">

    <label for="img">Hình ảnh</label>
    <input type="file" name="img" id="img"   >

    <label for="imageUrls" >Hình ảnh</label>
    <input type="file" name="imageUrls" id="imageUrls" multiple>

    <input type="submit" name="action" value="ADD">
</form>
</body>
</html>
