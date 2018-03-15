<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<ftt:common_layout title="UnitForum">
	<jsp:attribute name="body">

<c:if test="${masterInfo.viewType == 1}">
<div id="fForum" class="type_white">
</c:if>
<c:if test="${masterInfo.viewType == 2}">
<div id="fForum" class="">
</c:if>
<c:if test="${masterInfo.viewType == 3}">
<div id="fForum" class="type_brown">
</c:if>
  <div id="header">
    <div class="wrap_header">
      <div class="sort">
        <a class="${forumReq.sort != 1 ? "active" : ""}" href="#"><s:message code="resource.list.type.recommend"/></a>
        <a class="${forumReq.sort == 1 ? "active" : ""}" href="#"><s:message code="resource.list.type.recent"/></a>
      </div>
      <div class="write"><a class="btn_write" href="#"><s:message code="resource.list.btn.write"/></a></div>
    </div>
  </div>
  <!-- // #header -->

  <div id="content">
    <div class="forum_list">
      <c:if test="${fn:length(articleList) > 0}">
		<ul>
      		<c:forEach items="${articleList}" var="r" varStatus="s">
		        <li class="best${r.alreadyLike ? "" : " recom" }">
		          <c:if test="${forumReq.sort != 1 && s.index < 3 && r.likeCnt > 1}">
		          	<span class="icon_best"></span>
		          </c:if>
		          <span class="user_info">
		            <span class="lv">${fn:escapeXml(r.charGrade)}</span>
		            <span class="name">${fn:escapeXml(r.nickname)}</span>
		          </span>
		          <span class="text">
		            <span class="wrap_text">${fn:escapeXml(r.content)}</span>
		            <c:if test="${forumReq.gusn > 0 && forumReq.gusn == r.accountIdx}">
		            	<span class="btn_del" title="삭제하기" ftt-data-articleIdx="${r.articleIdx}"></span>
		            </c:if>
		          </span>
		          <span class="recom" ftt-data-articleIdx="${r.articleIdx}" ftt-data-accountIdx="${r.accountIdx}">${r.likeCnt > 9999 ? '9999+' : r.likeCnt}</span>
		        </li>
        	</c:forEach>
      	</ul>
      </c:if>
      
      <c:if test="${fn:length(articleList) <= 0}">
	      <div class="empty" style="display:block;">
	        <div class="wrap_empty">
	          <div>
	            <s:message code="resource.list.placeholder.${masterInfo.unitType}.write"/>
	          </div>
	        </div>
	      </div>
      </c:if>
    </div>
  </div>
  
  <!-- // #content -->

  <div id="fLayer">
    <!-- 의견 입력 -->
    <div class="layer_write">
      <div class="wrap_layer">
        <input id=write_box type="text" placeholder="<s:message code="resource.list.placeholder.${masterInfo.unitType}.write"/>" />
        <div>
          <button class="btn_comm btn_submit" type="button"><s:message code="resource.alert.btn.write"/></button>
          <button class="btn_comm btn_cancel" type="button"><s:message code="resource.alert.btn.cancel"/></button>
        </div>
      </div>
    </div>

    <!-- 의견(게시물) 삭제 -->
    <div class="layer_del">
      <div class="wrap_layer type_confirm">
        <div class="msg">
          <div class="wrap_msg">
          	<s:message code="resource.alert.message.delconfirm"/>
          </div>
        </div>
        <div class="btn_area">
          <button class="btn_confirm" type="button" href="#"><s:message code="resource.alert.btn.confirm"/></button>
          <button class="btn_cancel" type="button" href="#"><s:message code="resource.alert.btn.cancel"/></button>
        </div>
      </div>
    </div>

    <!-- error layer -->
    <div class="layer_error1">
      <div class="wrap_layer type_alert">
        <div class="msg">
          <div class="wrap_msg">
            <span class="custom_text"><s:message code="resource.alert.message.${masterInfo.unitType}.no"/></span> 
          </div>
        </div>
        <div class="btn_area">
          <button class="btn_cancel" type="button" href="#"><s:message code="resource.alert.btn.confirm"/></button>
        </div>
      </div>
    </div>
    
    <!-- layer BG -->
    <div class="bg_layer"></div>

  </div>
  <!-- // #fLayer -->
  
</div>

<script type="text/javascript">
/* layout */
(function(){
	var docHeight = $(window).height() - $("#header").height();
	// ios check
	if( /iPhone|iPad|iPod/i.test(navigator.userAgent) ) {
		$("#fForum").addClass("ios");
	}
	// doc min-height
	$("#content").css("min-height",docHeight);
})();

//layer - close
$("#fLayer .btn_cancel").bind("click",function(e){
  layerClose(e);
});


var nCurPage = 1;
var moreBtn = '<li class="btn_more"><button type="button"><s:message code="resource.alert.btn.more"/></button></li>';
var contentLength = '';
var flagCancel = '';

// more button
function listMore(){
	
	$("#content .forum_list ul").append(moreBtn);
	
	$(".btn_more").click(function(){
		var pu = new ParamUtil();
		pu.setParam('page', nCurPage+1);
		$(".btn_more").remove();
		
		$.ajax({
			  type: 'POST',
			  url: '/forum/ajax/article/list',
			  data: pu.getAllParam(1),
			  success: function(ret, status, xhr){
					if (typeof(ret) == 'object') {
						if (ret.code == 0) { // success
				  			console.log(ret.data);
				  			showMoreList(ret.data);
							nCurPage++;
						}
				  		else {
				  			return showLayerMessage(ret.message); // alert(ret.message);
				  		}
					}
					else {
						$("#content .forum_list ul").append(moreBtn);
						console.log(ret);
					}
			  },
			  dataType: 'json'
			});
	});
}
// 더보기 버튼 출력 조건
if( ${hasMore ? 'true' : 'false'} ){
	listMore();
}

// display article list
function showMoreList(aList){
	var pu = new ParamUtil();
	var sHtml = '';
	var hasMore = false;
	
	for (var i=0, j=0; i<aList.length; i++) {
		var aOldList = $("span.recom");
		var bExistArticle = false;
		
		$("span.recom").each(function( index ) {
			//console.log($(this).attr('ftt-data-articleIdx') + ' vs ' + aList[i].articleIdx);
			if ($(this).attr('ftt-data-articleIdx') == aList[i].articleIdx) { // exist
				bExistArticle = true;
				return false;//break;
			}
		});
		
		if (bExistArticle)
			continue;
		
		if (j++ >= 10) { // has more
			hasMore = true;
			break;
		}
		
		sHtml += '<li class="best$' + (aList[i].alreadyLike ? '' : ' recom') + '">';
		sHtml += '<span class="user_info">';
		sHtml += '  <span class="lv">'+aList[i].charGrade+'</span>';
		sHtml += '  <span class="name">'+aList[i].nickname+'</span>';
		sHtml += '</span>';
		sHtml += '<span class="text">';
		sHtml += '  <span class="wrap_text">'+aList[i].content+'</span>';
		
        if (aList[i].accountIdx == pu.getParam('gusn'))
			sHtml += '  	<span class="btn_del" title="삭제하기" ftt-data-articleIdx="'+aList[i].articleIdx+'"></span>';

		sHtml += '</span>';
		sHtml += '<span class="recom" ftt-data-articleIdx="'+aList[i].articleIdx+'" ftt-data-accountIdx="'+aList[i].accountIdx+'">'+(aList[i].likeCnt > 9999 ? '9999+' : aList[i].likeCnt) +'</span>';
		sHtml += '</li>';
	}
	
	$("#content .forum_list ul").append(sHtml);
	$("#content .forum_list ul .btn_del").unbind().click(doDeleteArticle);
	$("span.recom").unbind();
	$("span.recom").click(doYouLike);
	
	if (hasMore)
		listMore();
}

// write button
$("#header .btn_write").click(function(){
	var pu = new ParamUtil();
	
	if (pu.getParam('unitstat') == 1) {
		layerOpen("layer_write");
	}
	else {
		var unitType = "${masterInfo.unitType}";
		console.log(unitType);
		return showLayerMessage("<s:message code="resource.alert.message.${masterInfo.unitType}.no"/>");
	}
});

// delete confirm
$("#content .btn_del").click(doDeleteArticle);

// delete
$(".layer_del .btn_area .btn_confirm").click(function() {
	var nChoicedArticleIdx = $(this).attr("ftt-data-articleIdx");
	var pu = new ParamUtil();
	pu.setParam('aidx', nChoicedArticleIdx);
	
	console.log(nChoicedArticleIdx);
	console.log(pu);
	$.ajax({
		type: 'POST',
		url: '/forum/ajax/article/delete',
		data: pu.getAllParam(1),
		success: function(ret, status, xhr){
			if (typeof(ret) == 'object') {
				if (ret.code == 0) // success
		  			document.location.reload();
		  		else
		  			return showLayerMessage(ret.message); // alert(ret.message);
			}
			else {
				console.log(ret);
			}
		},
		dataType: 'json'
	});
});

function doDeleteArticle() {
	var nChoicedArticleIdx = $(this).attr("ftt-data-articleIdx");
	$(".layer_del .btn_area .btn_confirm").attr("ftt-data-articleIdx", nChoicedArticleIdx);
	layerOpen("layer_del");
}

var oCurLikeBtn;

// like & unlike
$("span.recom").click(doYouLike);

function doYouLike(){
	var nChoicedArticleIdx = $(this).attr("ftt-data-articleIdx");
	var sApiType;
	var pu = new ParamUtil();
	
	if (pu.getParam('gusn') == $(this).attr("ftt-data-accountIdx")) {
		return showLayerMessage("<s:message code="resource.alert.message.recommend"/>");
	}
	
	pu.setParam('aidx', nChoicedArticleIdx);
	
	if ($(this).parent().attr("class").indexOf("recom") >= 0) { // enable to like
		sApiType = 'like';
	}
	else { // enable to cancel
		sApiType = 'unlike';
	}
	
	oCurLikeBtn = $(this);
	
	$.ajax({
		type: 'POST',
		url: '/forum/ajax/article/' + sApiType,
		data: pu.getAllParam(1),
		success: function(ret, status, xhr){
			if (typeof(ret) == 'object') {
				if (ret.code == 0) { // success
					oCurLikeBtn.text(ret.data);//document.location.reload();
					if (oCurLikeBtn.parent().attr("class").indexOf("recom") < 0)
						oCurLikeBtn.parent().addClass("recom");
					else
						oCurLikeBtn.parent().removeClass("recom");
				}
		  		else {
		  			return showLayerMessage(ret.message); // alert(ret.message);
	  			}
			}
			else {
				console.log(ret);
			}
		},
		dataType: 'json'
	});
}

// sort radio box
$( "div.sort > a:nth-child(1)" ).bind("click",function(e){
	var pu = new ParamUtil();
	pu.setParam('sort', 0);
	$(location).attr('href', pu.getUrl());
});
$( "div.sort > a:nth-child(2)" ).bind("click",function(e){
	var pu = new ParamUtil();
	pu.setParam('sort', 1);
	$(location).attr('href', pu.getUrl());
});

$( ".btn_submit" ).bind("click",doWrite);

function doWrite() {
	var content = $.trim($("#write_box").val());
	var pu = new ParamUtil();
	
	if (content =='') {
		$("#write_box").val('');
		//$("#write_box").focus();
		flagCancel = '1';
		return showLayerMessage("<s:message code="resource.alert.message.${masterInfo.unitType}.write"/>");
	}
	
	if (content.length > 300) {
		contentLength = content.length;
		return showLayerMessage("<s:message code="resource.alert.message.maxlength300"/>");
	}
	
	pu.setParam('content', content);
	//console.log(pu.getAllParam(1));
		
	$.ajax({
		type: 'POST',
		url: '/forum/ajax/article/write',
		data: pu.getAllParam(1),
		success: function(ret, status, xhr){
			if (typeof(ret) == 'object') {
				if (ret.code == 0) // success
		  			//document.location.reload();
		  			$( "div.sort > a:nth-child(2)" ).click();
		  		else
		  			return showLayerMessage(ret.message); // alert(ret.message);
			}
			else {
				console.log(ret);
			}
		},
		dataType: 'json'
	});
}


function showLayerMessage(message) {
	$(".custom_text").html(message);
	layerOpen("layer_error1");
	$("#content").focus();
	return false;
}

////////////////////////////////////////////////////////////////////

// parameter util
function ParamUtil(){

	this.status = false;
	this.aParamData = [];
	
	this.init = function(){
		var sQueryStr = decodeURIComponent(window.location.href.slice(window.location.href.indexOf('?') + 1));
		if (sQueryStr.substr(sQueryStr.length-1, 1) == '#')
			sQueryStr = sQueryStr.slice(0, -1);
		
		var aQueryStrField = sQueryStr.split('&');
		for (var i = 0; i < aQueryStrField.length; i++) {
	        var aQueryStrCol = aQueryStrField[i].split('=');
	        this.aParamData[this.aParamData.length] = { name:aQueryStrCol[0], value:aQueryStrCol[1] }
	    }
		this.status = true;
	}
	
	this.getAllParam = function(nType) {
		if (!this.status)
			this.init();
		
		if (typeof(nType) == 'undefined' || nType == null || nType == 0) {
			return this.aParamData;
		}
		else if (nType == 1){
			var result = {};
			for (var i = 0; i < this.aParamData.length; i++)
				result[this.aParamData[i].name] = this.aParamData[i].value;
			
			return result;
		}
	}
	
	this.getParam = function (name) {
		if (!this.status)
			this.init();
		
		for (var i = 0; i < this.aParamData.length; i++)
			if (this.aParamData[i].name == name)
				return this.aParamData[i].value;
		
		return '';
	}

	this.setParam = function (name, value){
		if (!this.status)
			this.init();
		
		var found = false;
		for (var i = 0; i < this.aParamData.length; i++) {
			if (this.aParamData[i].name == name) {
				this.aParamData[i].value = value;
				found = true;
				break;
			}
	    }
		
		if (!found) // add new param
			this.aParamData[this.aParamData.length] = { 'name':name, 'value':value }
		
		//console.log(this.aParamData);
	}
	
	this.getUrl = function(){
		if (!this.status)
			this.init();
		
		return window.location.pathname + '?' + jQuery.param(this.getAllParam());
	}
}


//func openLayer
function layerOpen(target,customText){
  var docHeight = $(document).height(),
  target = "."+target,
  layerText = $(target).find(".custom_text"),
  layerPos;
  
  $("#fLayer").show();
  $("#fLayer .bg_layer").height(docHeight).show();
  if( $(layerText).length > 0 ){
    $(layerText).text(customText);
  }
  if( target == ".layer_write"){
    layerPos = $(window).height() * 0.03;
    $(target).css({
      "top":layerPos
    }).show();
    $("#fLayer .bg_layer").addClass("bg_write");
	$("body").css({
		"height": $(window).height(),
		"overflow-y": "hidden"
	});
  }else{
    layerPos = ($(window).height() - $(target).children("div").actual("height")) / 2 + $(window).scrollTop();
    $(target).css("top",layerPos).show();
  }
  $(document).bind("touchmove",function(event){
    event.preventDefault();
  });
}

//func closeLayer
function layerClose(e){
  if(e.target.id != 'fLayer') {
	if(contentLength > 300 || flagCancel == '1'){
    	$("#fLayer .layer_error1").hide();
    	contentLength = '';
    	flagCancel = '';
    	return false;
    }
	var target = $("#fLayer > div:visible");
    $("#fLayer").hide();
    $(target).hide();
    $("#fLayer .bg_layer").removeClass("bg_write");
    $("body").css({
		"height":"auto",
		"overflow-y":"auto"
	});
    $(document).unbind("touchmove");
  }
}
</script>
	</jsp:attribute>
</ftt:common_layout>