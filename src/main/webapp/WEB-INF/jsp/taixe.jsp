<%-- 
    Document   : taixe
    Created on : Sep 2, 2022, 4:56:44 PM
    Author     : thu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<div class="container-fluid p-0" style="margin-top: 70px">
    <div class="row" id="body-row">
        <%@ include file="admin.jsp" %>


        <!-- MAIN -->
        <div class="col" style="margin-top: 10px">

            <h3 style="text-align: center" >
                QUẢN LÝ TÀI XẾ
            </h3>

            <div class="container">
                <table class="table table-hover" style = "margin-top: 10px">
                    <thead>
                        <tr>
                            <th>Id Chuyến Xe</th>
                            <th>Mã Tuyến</th>
                            <th>Mã Xe</th>
                            <th>Giờ Xuất Phát</th>
                       <c:if test="${user.taixe.loaitx.toString() =='0'}">
                       <th>Tài Xế Chính</th>
                            </c:if>

                        <c:if test="${user.taixe.loaitx.toString() =='1'}">
                       <th>Tài Xế Phụ</th>
                            </c:if>

                       <th></th>
                        </tr>
                    </thead>
                    <c:forEach var="p" items="${listCX}" >
                        <tbody>
                            <tr>
                                <td>${p.maChuyenXe}</td>
                                <td>${p.maTuyenXe.maTuyenXe}</td>
                                <td>${p.maXe.maXe}</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value ="${p.gioXuatPhat}"/></td>
                                <c:if test="${user.taixe.loaitx.toString() =='1'}">
                            <td>${p.maTaiXePhu.tenTaiXe}</td>


                        </c:if>

                        <c:if test="${user.taixe.loaitx.toString() =='0'}">
                            <td>${p.maTaiXeChinh.tenTaiXe}</td>

                        </c:if>

                            <td>
                                <a href="<c:url value="/taixe/chuyenxe/${p.maChuyenXe}"/>" type="button" style="background-color: #dc3545" class="btn btn-primary"><span class="bi bi-info-circle"> Chi tiết</span></a>

                            </td>
                       
                        </tr>

                        </tbody>
                    </c:forEach>
                </table>



            </div>



            <!--               
                           
            
            
            
                    </div><!-- Main Col END -->

        </div><!-- body-row END -->
    </div><!-- container -->
</div>

