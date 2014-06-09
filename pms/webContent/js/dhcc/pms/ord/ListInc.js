// zxx 2014-04-19
$(function (){
	
	
		
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/hop/hopIncCtrl!list.htm',
	    method:'post',
	    loadMsg:'加载数据中.....',
	    fit:true,
	    columns:[[
	        {field:'incId',title:'incId',width:100,hidden:true},      
			{field:'incCode',title:'药品代码',width:100},  
			{field:'incName',title:'药品描述',width:100},
			{field:'incUomname',title:'单位描述',width:100}
	    ]]	 
	});
	var cardview =  $.extend({}, $.fn.datagrid.defaults.view,
			{
				renderRow : function(target, fields, frozen,rowIndex, rowData) {
					var cc = [];
					cc.push('<td colspan=3 style="padding:10px 5px;border:#9CC8F7 1px solid;">');
					if (!frozen) {
						img = rowData[1];
						cc.push('<img src='+$WEB_ROOT_PATH+'/uploads/u24_normal.png style="width:90px;float:left">');
						cc.push('<div style="float:left;margin-left:20px;">');
			
						cc.push('<p><span class="c-label">代码: '+ rowData.incCode + '</span></p>');
						cc.push('<p><span class="c-label">名称: '+ rowData.incName + '</span></p>');
						cc.push('<p><span class="c-label">单位: '+ rowData.incUomname + '</span></p>');
					
						cc.push('</div>');
						cc.push('<div style="float:left;margin-left:20px;">');
						
						cc.push('<p><span class="c-label">产地: '+ rowData.manfName + '</span></p>');
						cc.push('<p><span class="c-label">价格: '+ rowData.incRp + '</span></p>');
						cc.push('<p><span class="c-label">基本单位: '+ rowData.incBuomname + '</span></p>');
					
						cc.push('</div>');

						cc.push('<div class="shoppingQty" ><span class="add_btn" id="minus_btn" onclick=minus('+rowIndex+')>-</span><u><input  type="text" class="txt" value=1 id="tableQty'+rowIndex+'"/></u><span class="add_btn" onclick=plus('+rowIndex+')>+</span></div>');
						cc.push('<div class=shoppingBtn onclick=addShopping('+rowIndex+')>');
						cc.push('<i class=icon-shopping-cart></i>  加入购物车');
						cc.push('</div>');
						
					}
					cc.push('</td>');
					return cc.join('');
				}
			});
	$(function() {
		$('#datagrid').datagrid({
			view : cardview
		});
	});
	
	

	
	
	// 点击查询的提交按钮
	$("#selectBt").on('click', function() {
		 var json=$CommonUI.loopBlock('#selectWin');
		 $("#datagrid").datagrid('load', json);
		 $CommonUI.getDialog('#selectWin').dialog('close');
	});
	
	
	$('#title').css({width:500,height:60});
	
	$('#title1').css({width:500,height:60});
	
	
	//点击增数量
	$("#plug_24_mytable_page .table_box .shopping_titlebar .shoping_list ").on('click',"table tr td .add_btn",function(){
		
	    var txtobj = $(this).siblings("u").children(".txt");
		var txtval = parseInt(txtobj.val());
		var qty=1;flag=1;
		if($(this).attr("id")=="minus_btn"){if(txtval<2){txtval=2;flag=0;};txtval -= 1;qty=-1;};
		if($(this).attr("id")=="plus_btn"){txtval += 1;};
		txtobj.val(txtval);
		if (flag==1){
			$.post(
					$WEB_ROOT_PATH+'/ord/orderCtrl!saveShopCart.htm',
		            {
		           	 "dto.ordShopping.shopIncid":$(this).parents("tr").attr("dataid"),
		           	 "dto.ordShopping.shopQty":qty,
		            }
		    );
		}
	});
	//删除一个药
	$("#plug_24_mytable_page .table_box .shopping_titlebar .shoping_list ").on('click',"table tr td .fork",function(){
		deleteObj=$(this).parents("tr");
		$.post(
				$WEB_ROOT_PATH+'/ord/orderCtrl!deleteShopCart.htm',
	            {
	           	 "dto.ordShopping.shopIncid":$(this).parents("tr").attr("dataid"),
	            },
	            function(data){
	               if(data.dto.opFlg=="1"){
	           			deleteObj.remove();	
	               }
	            },
	            "json"
	    );

	});
	$(".shoppingQty").on('click',".add_btn",function(){
		
	    var txtobj = $(this).siblings("u").children(".txt");
		var txtval = parseInt(txtobj.val());
		if($(this).attr("id")=="minus_btn"){if(txtval<2){txtval=2;};txtval -= 1;};
		if($(this).attr("id")=="plus_btn"){txtval += 1;};
		txtobj.val(txtval);
	});
	
	

    $.getJSON($WEB_ROOT_PATH+"/ord/orderCtrl!listShopCart.htm", function (json) {
        $.each(json.dto.shopCartVoList, function (i) {
        	addmenu([json.dto.shopCartVoList[i].inc,json.dto.shopCartVoList[i].name,json.dto.shopCartVoList[i].rp,json.dto.shopCartVoList[i].qty]);
        });
    });
});


function plus(index){
	txtval=parseInt($("#tableQty"+index).val());
	txtval += 1;
	$("#tableQty"+index).val(txtval);
}
function minus(index){
	txtval=parseInt($("#tableQty"+index).val());
	if(txtval<2){txtval=2;};txtval -= 1;
	$("#tableQty"+index).val(txtval);
}
function addmenu(ary){
			
	var litmenu = "<tr dataid=\""+ ary[0]+"\" height=\"35px\" valign=\"middle\" align=\"center\" bgcolor=\"#FFFFFF\">"; 
	litmenu += "<td width=\"94px\" align=\"left\" style=\"color:#6c6c6c;text-indent:5px\">"+ ary[1]+"</td>";
	litmenu += "<td width=\"99px\">";
	litmenu += "<span class=\"add_btn\" id=\"minus_btn\">-</span><u><input type=\"text\" class=\"txt\" value=\""+ ary[3]+"\"/></u><span class=\"add_btn\" id=\"plus_btn\">+</span>";
	litmenu += "</td>";
	litmenu += "<td width=\"52px\" style=\"color:#6c6c6c\">"+ ary[2]+"</td>";
	litmenu += "<td width=\"44px\"><u class=\"fork\">×</u></td>";
	litmenu += "</tr>";

	var all_dishid = $("#plug_24_mytable_page .table_box .shopping_titlebar .shoping_list table tr");
	for(var j=0;j<all_dishid.length;j++){//循环小元素；
		if(all_dishid.eq(j).attr("dataid") == ary[0]){//如果要插入的小元素已有；
			//该小元素的值加1,同时展开该小元素所在的大元素，并推出函数;
			
			var txtval = parseInt(all_dishid.eq(j).children("td").children("u").children(".txt").val());
			txtval += parseInt(ary[3]);
			all_dishid.eq(j).children("td").children("u").children(".txt").val(txtval).parents('.shopping_titlebar').addClass('shopping_titlebar_opened').siblings(".shopping_titlebar").removeClass('shopping_titlebar_opened');
			return false;
		}
  }
	//如果该大元素不存在于餐桌上，则新插入一个打开的大元素；
	$("#plug_24_mytable_page .table_box .shopping_titlebar .shop_cnt .shoping_list #table_list").prepend(litmenu);
}

//添加购物车
function goToShopp(){
	window.location.href=$WEB_ROOT_PATH+'/ord/orderCtrl!shoppingCart.htm';
	
}
function addShopping(rowIndex){
	
	incName=$('#datagrid').datagrid('getRows')[rowIndex]['incName'];
	incId=$('#datagrid').datagrid('getRows')[rowIndex]['incId'];
	incRp=$('#datagrid').datagrid('getRows')[rowIndex]['incRp'];
	incUomname=$('#datagrid').datagrid('getRows')[rowIndex]['incUomname'];
	incQty=parseInt($("#tableQty"+rowIndex).val());
	$.post(
			$WEB_ROOT_PATH+'/ord/orderCtrl!saveShopCart.htm',
            {
           	 "dto.ordShopping.shopIncid":incId,
           	 "dto.ordShopping.shopQty":incQty,
           	 "dto.ordShopping.shopUom":incUomname,
            },
            function(data){
               //$CommonUI.alert(data.dto.msg,"","","",null);
               if(data.dto.opFlg=="1"){
            	   addmenu([incId,incName,incRp,incQty]);
               }
            },
            "json"
    );
	
	
}

