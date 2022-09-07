<%-- 
    Document   : chiTietVe
    Created on : Aug 25, 2022, 11:26:49 AM
    Author     : thu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="<c:url value='/resources/assets/js/timveKH.js' />"></script>
<script src="<c:url value='/resources/assets/js/master.js' />"></script>



<div class ="container border" style ="margin-top: 100px; margin-bottom: 50px; width: 100%;" >

    <div class="container border" style="height:300px; width:1000px ; margin-bottom: 50px" >
        <div class="container-i" >
            <img class="card-img-top card-i" style="width:100%;height:93%;padding-left: 10px" src="https://res.cloudinary.com/dcpbdkeam/image/upload/v1661351111/ChuyenXe/AnhXe/LESO3679-HDR-min-scaled_xwjqhv.jpg"  alt="Card image">
            <h3 style="padding-left: 40px; text-align: center"> </h3>
        </div>
        <div class="container border" style="width:100%;height:100%;padding-top: 10px">
            <h3>Mã chuyến xe: </h3>
            <h4>Giường nằm  chỗ</h4>
            <h4>Giờ khởi hành: </h4>
        </div>

    </div>
    <input value="${message}" id='messchitiet' hidden>

    <div class="container " style="height:400px; width:1000px ; margin-bottom: 50px" >
        <div class="container " style="float: left;width: 30%; height: 100%">
            <div style="text-align: center;margin-top: 30px">
                <i class="bi bi-file-check-fill" style="color: #333333; font-size: 2em"><h5>Đang chọn</h5></i>
                <i class="bi bi-file" style="color: #333333; font-size: 2em"><h5>Còn trống</h5></i>
                <i class="bi bi-file-break-fill" style="color: #333333; font-size: 2em"><h5>Không đặt được</h5></i>

            </div>

        </div>
        <div class="container " style="float: right;width: 70%;height: 100%">
            <div class = "container" style="float: left;width: 50%" >
                <h4 style="text-align: center">Tầng dưới</h4>
                <div class="container"style="margin-left:20px">
                    <c:forEach begin="0" end="${Listcxcn1.size()-1}" var="i">
                        <c:choose>
                            <c:when test="${Listcxcn1[i].trangThai == true}">
                                <a data-toggle="tooltip"  data-placement="top" title="Ghế ${Listcxcn1[i].maGhe}" id="${Listcxcn1[i].maGhe}" style="color: #333333; font-size: 2em" class="bi bi-file-break-fill"></a> 
                            </c:when>    
                            <c:otherwise>
                                <a onclick="chonGhe(${maCX},${Listcxcn1[i].idCXMG});setpicGhe('${Listcxcn1[i].maGhe}');" data-toggle="tooltip"  data-placement="top" title="Ghế ${Listcxcn1[i].maGhe}" href="javascript:;" id="${Listcxcn1[i].maGhe}" style="color: #333333; font-size: 2em" class="bi bi-file"></a> 
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" value = "1" id="ip${Listcxcn1[i].maGhe}">

                        <c:if test="${(i+1)%count == 0}">
                            <br>
                        </c:if>     
                    </c:forEach>
                </div>


            </div>

            <div class = "container  " style="float: right;width: 50%" >
                <h4 style="text-align: center">Tầng trên</h4>
                <div class="container;" style="margin-left:30px">
                    <c:forEach begin="0" end="${Listcxcn2.size()-1}" var="i">
                        <c:choose>
                            <c:when test="${Listcxcn2[i].trangThai == true}">
                                <a data-toggle="tooltip"  data-placement="top" title="Ghế ${Listcxcn2[i].maGhe}" id="${Listcxcn2[i].maGhe}" style="color: #333333; font-size: 2em" class="bi bi-file-break-fill"></a> 
                            </c:when>    
                            <c:otherwise>
                                <a onclick="chonGhe(${maCX},${Listcxcn2[i].idCXMG});setpicGhe('${Listcxcn2[i].maGhe}');"  data-toggle="tooltip"  data-placement="top" title="Ghế ${Listcxcn2[i].maGhe}" 
                       href="javascript:;" id="${Listcxcn2[i].maGhe}"   style="color: #333333; font-size: 2em" class="bi bi-file"></a> 
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" value = "1" id="ip${Listcxcn1[i].maGhe}">

                        <c:if test="${(i+1)%count == 0}">
                            <br>
                        </c:if>     
              
                    <input type="hidden" value = "1" id="ip${Listcxcn2[i].maGhe}">
      
                </c:forEach>

            </div>


        </div>


    </div>  
    <div  class = "container" style="float: bottom;"> 
        <div " class="container " style="float: left">
            <h5>Ghế đang chọn:<span id="soghe">Chưa chọn</span></h5>
        </div>

        <div class="container " >
            <h5>Tổng tiền: <span id="tongTien">0</span></h5>
        </div>
        <div style="" class ="container" >
            <form method="post" action="<c:url value='/timve/chitiet/${Listcxcn1[0].maChuyenXe.maChuyenXe}' />">
                <button  id="btChiTiet"  style="border: 20px" class="book-a-table-btn" disabled>Đặt vé</button>
           </form>
            
        </div>
    </div>

</div>
                    
    

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    


</script> 


</div>

