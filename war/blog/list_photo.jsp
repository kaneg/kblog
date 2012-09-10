<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="gz.aws.blog.PhotoManager" %>
<%@ page import="gz.aws.blog.entity.Photo" %>
<%@ page import="gz.aws.blog.ioc.WebBlogUtil" %>

<head>
  <style type="text/css">
   #outer img{
        width:inherit;
    }
    #outer {
      background: #ffffff;
      margin: auto;
      text-align: center;
    }

   .inner {
       width: 108px;
       float: left;
       list-style-type: none;
       border: 1px solid #FFFFFF;
   }

   .inner p.image {
       float: left;
       font-size: 0;
       height: 100px;
       line-height: 0;
       margin: 1px;
       overflow: hidden;
       padding: 2px;
       width: 100px;
       border:1px solid  #ECECEC;
   }

   .inner p.name {
       clear: both;
       line-height: 130%;
       overflow: hidden;
       padding-top: 4px;
       text-align: center;
       white-space: nowrap;
       width: 98%;
       word-wrap: normal;
       color: #ADADAD;
   }
    * html .inner {
      display: inline
    }
    .photo {
      /*width: 75px;*/
      /*height: 100px;*/
    }
  </style>
    <link rel="stylesheet" href="/js/tinybox2/style.css"/>
    <script type="text/javascript" src="/js/tinybox2/tinybox.js"></script>
    <script type="text/javascript">
      function f(url) {
          var w = $(window).width();
          var h = $(window).height() - 60;
          var imgInput = "<img src='" + url + "' onclick='TINY.box.hide()' height='" + h + "'>";
          TINY.box.show(imgInput, 0, 0, h, 0);
      }
       function showImage(url) {
          var w = $(window).width();
          var h = $(window).height() - 60;
          var imgInput = "<img src='" + url + "' onclick='TINY.box.hide()' height='" + h + "'>";
          TINY.box.show({image:url,boxid:'',animate:true});
      }
      var pId;
      function show_rename_dialog(pId,pName) {
          this.pId=pId;
          var content = $("#show_rename_dialog").text()
          TINY.box.show({html:content,boxid:'',animate:false,close:false});
      }
      function rename_photo() {
          var newPhotoName = $("#newPhotoName").val();
          if(!newPhotoName.trim()){
              alert("Empty photo name!")
              return false;
          }
          newPhotoName=newPhotoName.trim()
          var callback = function(txt) {
              document.location.reload();
          }
          $.post("admin/update_photo.action?pId="+this.pId, {"photoName":newPhotoName}, callback)
          return false;
      }
      function confirmDelete() {
          return  window.confirm("Do you really want to delete it?")
      }
  </script>
</head>
<body>
<p>
    <a href="admin/add_photo_view.action">Add photo</a>
    <a href="main.action?pageType=photo_slider">Photo Slider</a>
    <a href="main.action?pageType=list_album">List Album</a>
    <a href="admin/add_album_view.action">Add Album</a>
</p>
<%
  PhotoManager photoManager = WebBlogUtil.getPhotoManager();
  List<Photo> photos = photoManager.listAll();
%>
<c:set var="photos" value="<%=photos%>"/>
<ul id="outer">
  <c:forEach var="photo" items="${photos}">
      <li class="inner">
          <p class="image">
              <a href="javascript:void(0)" href1="javascript:f($('#href${photo.id}').find('img').attr('src'))" id="href${photo.id}">
                  <img class="photo" style="width: 100px" src="admin/view_photo.action?pId=${photo.id}" onclick="showImage(this.src)" alt="${photo.name}"/>
              </a>
          </p>
          <p class="name">
             <span title="${photo.name}">${photo.name}</span>
          </p>
          <p class="name">
              <a href="admin/delete_photo.action?pId=${photo.id}" onclick="return confirmDelete()">删除</a>|
              <a href="#" onclick="show_rename_dialog('${photo.id}','${photo.name}')">重命名</a>
          </p>
      </li>
      <%--<div>--%>
    <%--</div>--%>
  </c:forEach>
</ul>
<textarea style="display:none" id="show_rename_dialog">
  <form action="" onsubmit="return rename_photo()">
    <table>
      <tr>
        <td>新名字：</td>
        <td><input type="text" name="newPhotoName" id="newPhotoName"></td>
      </tr>
      <tr>
        <td><input type="submit" value="OK"></td>
        <td><input type="reset" value="Cancel" onclick="TINY.box.hide();"></td>
      </tr>
    </table>
  </form>
</textarea>
</body>
