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