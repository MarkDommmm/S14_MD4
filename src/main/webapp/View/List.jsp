<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/7/2023
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body    { background-color: black}
        a{text-decoration: none}
    </style>
</head>
<body>
<a href="<%=request.getContextPath()%>/ProductsServlet?action=CREATE">Thêm sản phẩm!!!</a>
<table class="table table-dark table-striped">
    <thead>
    <tr>

        <th scope="col">#</th>
        <th scope="col">Tên sản phẩm
            <a class="fas fa-sort"
               href="<%=request.getContextPath()%>/ProductsServlet?action=${check ? "GETALL": "SORT_NAME"}"></a>
        </th>
        <th scope="col">Mô tả</th>
        <th scope="col">Giá</th>
        <th scope="col">Số lượng kho</th>
        <th scope="col">Ảnh</th>
        <th scope="col">Trạng thái</th>
        <th scope="col" colspan="3">Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="p" varStatus="item">
        <tr>
            <td>${item.count}</td>
            <td>${p.name}</td>
            <td>${p.description}</td>
            <td>${p.price}$</td>
            <td>${p.stock}</td>
            <td width="150px"><a href="${p.img}" target="_blank">
                <img width="100%" height="auto" src="<%=request.getContextPath()%>/img/${p.img}">
            </a></td>
            <td>${p.status ? "Còn Hàng" : "Hết Hàng"}</td>
            <td><a href="<%=request.getContextPath()%>/ProductsServlet?action=ADDCART&id=${p.id}">Thêm giỏ hàng</a></td>
            <td><a href="<%=request.getContextPath()%>/ProductsServlet?action=DETAIL&id=${p.id}">Chi tiết</a></td>
            <td><a href="<%=request.getContextPath()%>/ProductsServlet?action=EDIT&id=${p.id}">Chỉnh sửa</a></td>
            <td><a href="<%=request.getContextPath()%>/ProductsServlet?action=DELETE&id=${p.id}">xóa</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
