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
    <style>
        form{
            display: flex;
            flex-direction: column;
            width: 20rem;
            text-align: center;
            margin:  0 auto;
            background-color: whitesmoke;
            box-shadow: 1px 1px 1px  1px black;
            padding: 5rem 5rem;
        }

    </style>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/ProductsServlet">
    <label for="id">ID</label>
    <input type="text" name="id" id="id" disabled value="${productEdit.id}">
    <input type="hidden" name="id" value="${productEdit.id}">
    <br>
    <label for="name">Nhập tên sản phẩm</label>
    <input type="text" id="name" name="name" value="${productEdit.name}">
    <br>
    <label for="description">Mô tả</label>
    <input type="text" id="description" name="description" value="${productEdit.description}">
    <br>
    <label for="price">Giá</label>
    <input type="text" id="price" name="price" value="${productEdit.price}">
    <br>
    <label for="stock">Số lượng kho</label>
    <input type="text" id="stock" name="stock" value="${productEdit.stock}">
    <br>
    <label for="img">Hình ảnh</label>
    <textarea name="img" id="img" cols="30" rows="5">${productEdit.img}</textarea>
    <br>
    <label>Trạng thái</label>
     ${productEdit.status ?"Còn Hàng" :"Hết Hàng"}



    <input type="submit" name="action" value="UPDATE">
</form>
</body>
</html>
