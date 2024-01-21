<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 100%;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
   <div class="row g-3">
    <div class="col-auto">
     <input type="text"
      v-model="fd"
      @keyup.enter="find()"
     >
    </div>
    <div class="col-auto">
    <input type=button value="검색" 
    class="btn-sm btn-danger" @click="find()">
    </div> 
    
   </div>
   <div style="height: 20px"></div>
   <div class="row">
    <div class="col-md-3" v-for="vo in find_data" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="detail(vo.fno)">
	    <div class="card border-success mb-3" style="max-width: 18rem;">
		  <div class="card-header">{{vo.name}}</div>
		  <div class="card-body">
		    <img :src="'https://www.menupan.com'+vo.poster" style="width: 100%" class="img-rounded">
		  </div>
		</div>
   </div>
   <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">{{food_detail.name}}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table">
        <tr>
          <td width=30% class="text-center" rowspan="5">
            <img :src="'https://www.menupan.com'+food_detail.poster" 
                style="width: 100%">
          </td>
          <td colspan="2"><h3>{{food_detail.name}}</h3></td>
        </tr>
        <tr>
          <th width=10%>주소</th>
          <td width=60%>{{food_detail.address}}</td>
        </tr>
        <tr>
          <th width=10%>시간</th>
          <td width=60%>{{food_detail.time}}</td>
        </tr>
        <tr>
          <th width=10%>가격대</th>
          <td width=60%>{{food_detail.price}}</td>
        </tr>
        <tr>
          <th width=10%>종류</th>
          <td width=60%>{{food_detail.type}}</td>
        </tr>
        <tr>
          <td colspan="3">{{food_detail.content}}</td>
        </tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
   </div>
 </div>
  </div>
  
  <script src="food.js"></script>
</body>
</html>