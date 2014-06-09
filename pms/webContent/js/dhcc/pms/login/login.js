function setTab(i, j) {
	var bulletin_titles = document.getElementById("bulletin_title_" + i)
			.getElementsByTagName("li");
	var bulletin_contents = document
			.getElementById("bulletin_content_" + i).getElementsByTagName(
					"ul");
	for (var k = 0; k < bulletin_titles.length; k++) {
		bulletin_titles[k].className = k == j ? "hover" : "";
		bulletin_contents[k].style.display = k == j ? "block" : "none";
	}
}
function LanguageSelect_onChanged() {
	document.getElementById("languageForm").submit();
}
function SendAuthen(){
	// 用户输入的账户名
    var userName=document.getElementById('mp_userName').value;
	// 用户输入的密码
    var password=document.getElementById('mp_password').value;
	// 用户登录的方式
    var loginType="accountAlias";
	// 用户类型（从业人员为"Pra",公众账户为"Normal"）
	var userType="Normal";
	// 系统类型，业务系统在管理平台登记的系统类型名称
    var systemType="PMS";
	// 系统版本，业务系统当前使用的版本
    var version="1.0";
    
    // 返回结果为Authen对象，state为描述信息,state为状态信息（0:认证失败；1:认证成功；2:票据过期）
    // url为当前用户有权限访问的当前系统的url地址，可根据url组织显示菜单    
    var authen=Login.Authen.getTicket(userName,password,loginType,userType,systemType,version);
    //alert(authen.msg+authen.state);
    //location.assign("index.htm");
    if (authen.state!="1"){
    	alert(authen.msg);
    }else{
    	location.assign("index.htm");
    }
}
/*
// 1. 管理平台登录认证
function SendAuthen(){
	// 1、获取信息
	var userType="Pra";
	var loginType="accountAlias";
    var userName=document.getElementById('mp_userName').value;
    var password=document.getElementById('mp_password').value;
    var systemType="PMS";
    var version="1.0";

	// 2、获取身份认证信息
	$.post(
//		"/pms/login/loginCtrl!getTicket.htm",
		"/pms/authenTicket/authenTicketCtrl!getTicket.htm",	
		{
			'loginDto.loginVo.userType': userType,
			'loginDto.loginVo.loginType': loginType,
			'loginDto.loginVo.userName': userName,
			'loginDto.loginVo.systemType': systemType,
			'loginDto.loginVo.version':version
		},
		function(data,status){
			var idTicket=data.idTicket;
			var serviceTicket=data.serviceTicket;
			var authenMsg=data.authenMsg;
			if ((idTicket!="")&&(authenMsg!="")){
//				try{
					var decryptAuthenMsg=Security.enc.AES.cbcDecrypt(authenMsg,password);
					var authenJson=JSON.parse(decryptAuthenMsg);
					var sessionKey=authenJson.sessionKey;
					var timestamp=authenJson.timestamp;

					// 有无服务票据
					if(serviceTicket!=""){
						var cAuthenMsgEn=Security.enc.AES.cbcEncrypt(timestamp,sessionKey);
						$.post(
							"/pms/authenTicket/authenTicketCtrl!authenUser.htm",
							{
								'loginDto.loginVo.userName': userName,
								'loginDto.loginVo.authenMsg': cAuthenMsgEn,
								'loginDto.loginVo.serviceTicket': serviceTicket
							},
							function(data,status){
								var sAuthenMsgEn=data.authenMsg;
								if(sAuthenMsgEn!=""){
									var sAuthenMsg=Security.enc.AES.cbcDecrypt(sAuthenMsgEn,sessionKey);
									var sAuthenJson=JSON.parse(sAuthenMsg);
									var state=sAuthenJson.state;
									if (state=="1"){
										location.assign("pms.jsp");
									}else{
										alert(sAuthenJson.msg);
									}
								}else{
									alert("认证服务器失败！");
								}
							},
							"json"
						);
					}else{
						alert("请从要访问的应用系统登录！");
					}
					
//				}catch (e){
//					alert("账户密码错误");
//				}
			}else{
				alert("账户密码错误！");
			}
		},
		"json"
    );
}
*/