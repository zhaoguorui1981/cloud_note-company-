	 $(function(){	//页面载入完毕
		 //登录按钮绑定单击事件-调用登录方法
		 $('#login').click(login);
	 	 //注册按钮绑定单击事件-调用注册方法
	 	 $('#regist_button').click(regist)
	 })

//登录方法
	function login(){
		var acc=$('#count').val().trim();
		var pwd=$('#password').val().trim();
		//校验参数不能为空
		$('#count_span').empty();
		$('#password_span').empty();
		//设置校验开关
		var eswitch=true;
		if(!acc){
			$('#count_span').html("用户名为空").css({"color":"red","font-size":"15px"})
			eswitch=false;
		}
		if(!pwd){
			$('#password_span').html("密码为空").css({"color":"red","font-size":"15px"})
			eswitch=false;
		}
		//校验结束,发送ajax请求
		
		if(eswitch){
			$.ajax({
				//请求连接
				url:base_path+"/user/login.do",
				//请求方式
				type:"post",
				//请求参数
				data:{"account":acc,"password":pwd},
				//返回参数形式
				dataType:"json",
				//调用返回正常函数
				success:function(result){
					var status=result.status;
					switch(status){
						case 0:
							//TODO 将用户信息写入Cookie
							var user=result.data;
							addCookie("uid",user.cn_user_id,2);
							addCookie("name",user.cn_user_name,2);
							window.location.href="edit.html"; //成功
							break;
						case 1:
							$('#count_span').html(result.msg).css({"color":"red","font-size":"15px"})
							break;
						case 2:
							$('#password_span').html(result.msg).css({"color":"red","font-size":"15px"})
							break;
					}
				},
				//调用返回异常函数
				error:function(){alert("服务器繁忙")},
			})
		}
		
	}
	//注册方法
	 function regist(){
			var name = $('#regist_username').val().trim();
			var nick = $('#nickname').val().trim();
			var password= $('#regist_password').val().trim();
			var f_password=$('#final_password').val().trim();
			//清空消息
			$("#warning_1 span").empty();
			$("#warning_2 span").empty();
			$("#warning_3 span").empty();
			//格式检查
			//验证开关
			var ok=true;
			if(!name){
				ok=false;
				$("#warning_1").show();
				$("#warning_1 span").html("用户名为空");
			}
			if(!password){
				ok=false;
				$("#warning_2").show();
				$("#warning_2 span").html("密码为空");
			}else if(password.length<6){
				ok=false;
				$("#warning_2").show();
				$("#warning_2 span").html("密码过短");
			}
			if(!f_password){
				ok=false;
				$("#warning_3").show();
				$("#warning_3 span").html("确认密码为空");
			}else if(password!=f_password){
				ok=false;
				$("#warning_3").show();
				$("#warning_3 span").html("密码输入不一致");
			}
			if(ok){
				$.ajax({
					url:base_path+"/user/regist.do",
					type:"post",
					data:{"name":name,"password":password,"nick":nick},
					dataType:"json",
					success:function(result){
						if(result.status==0){//注册成功
							alert(result.msg);
							$('#back').click();//触发返回按钮单击转回登录页面
						}else if(result.status==1){//用户名被占
							$("#warning_1").show();
							$("#warning_1 span").html(result.msg);
						}
					},
					error:function(){alert("服务器繁忙")},
				})
			}
		 }