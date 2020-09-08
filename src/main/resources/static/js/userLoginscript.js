document.querySelector('.img__btn').addEventListener('click', function() {
    document.querySelector('.dowebok').classList.toggle('s--signup')
})

const login = new Vue({
    el: '#userLog',
    data:{
        le: '使用账号登录',isShow: false,account:'',userpassword:'',email:'',ePassword:'',
        dialogTableVisible: true, dialogFormVisible: false,e_password:'',getRegVal:"点击获取",
        form: {}, formLabelWidth: '220px',reemail:'',regCode:'',isDaojiShi:false,miao:60,daoji:'',returnRegCode:'',
    },
    methods:{
        switchLog(){//切换登录方式
            if(this.isShow){
                this.isShow=false;
                this.le='使用账号登录';
            }
            else{
                this.isShow=true;
                this.le='使用QQ邮箱登录';
            }
        },
        getLogin(){
            if(this.le=='使用账号登录'){//邮箱登录
                if(this.email == ''){
                    this.$message({type: 'error',message: '不能为空！',duration: 2000});
                }else{
                let params = new URLSearchParams();
                params.append("email",this.email);
                params.append("e_password",this.ePassword);
                axios
                    .post('/universal/dealLoginEmail',params)
                    .then((res)=>{
                        console.log(res.data)
                        if(res.data == 'ok'){
                            window.location.href = "/universal/getIndex";
                        }else if(res.data == 'ok1'){
                            window.location.href = "/admin/toAdminManage";
                        }else{
                            alert('登录失败！请重新登录。')
                        }
                    })
                    .catch((re)=> {
                        console.log("error:"+re.data);
                        this.$message({type: 'error',message: '邮箱密码错误！',duration: 2000});
                    });
                }
            }else {//账号登录
                if(this.account == ''){
                    this.$message({type: 'error',message: '不能为空！',duration: 2000});
                }else{
                    let params = new URLSearchParams();
                    params.append("account",this.account);
                    params.append("userpassword",this.userpassword);
                    axios
                        .post('/universal/dealLogin',params)
                        .then((res)=>{
                            console.log(res.data)
                            if(res.data == 'ok'){
                                window.location.href = "/universal/getIndex";
                            }else if(res.data == 'ok1'){
                                window.location.href = "/admin/toAdminManage";
                            }else{
                                alert('登录失败！请重新登录。')
                            }
                        })
                        .catch((re)=> {
                            console.log("error:"+re.data);
                            this.$message({type: 'error',message: '账号密码错误！',duration: 2000});
                        });
                }

            }
        },
        getRegCodes(){//获取注册验证码
            if(this.reemail != ''){
                let params = new URLSearchParams();
                params.append("email",this.reemail);
                axios
                    .post('/universal/getResetCode',params)
                    .then((res)=>{
                        if(res.data=="请输入正确邮箱！"){
                            alert(res.data)
                        }else {
                            this.returnRegCode = res.data;
                            console.log(this.returnRegCode )
                            this.isDaojiShi = true;//倒计时
                            let i = 60;
                            let _this = this;
                            clearInterval(this.daoji);
                            this.daoji = setInterval( ()=>{
                                i-=1;
                                console.log(i)
                                _this.miao = i;
                                if(i == 0){
                                    _this.getRegVal = "重新获取";
                                    _this.returnRegCode = undefined;
                                    clearInterval(_this.daoji);
                                    _this.isDaojiShi = false;
                                }
                            },1000);
                            alert("验证码已发送至邮箱请查收！")
                        }
                    })
                    .catch(function (error) {
                        console.log("error:"+error);
                    });
            }else {
                alert("请输入邮箱！");
            }
        },
        getMyPssd(){
            if(this.regCode == this.returnRegCode && this.regCode!='' && this.e_password!=''){
                // alert("密码已重置成功，请注意查收邮件！")
                let params = new URLSearchParams();
                params.append("email",this.reemail);
                params.append("e_password",this.e_password);
                axios
                    .post('/universal/resetEmailPssd',params)
                    .then((res)=>{
                        if(res.data=="修改成功！"){
                            alert(res.data);
                            this.dialogFormVisible = false;
                        }else{
                            alert(res.data);
                        }
                    })
                    .catch(function (error) {
                        console.log("error:"+error);
                    });
            }else {
                alert("密码重置失败，请重新操作!")
            }
        }
    },
})

const register = new Vue({
    el: '#userReg',
    data:{
        isDaojiShi:false,miao:60,returnRegCode:'',email:'',e_password:'',username:'',regCode: '',
        daoji:'',isDisabled:true,getRegVal:"点击获取",
    },
    methods:{
        getRegCodes(){//获取注册验证码
            if(this.email != ''){
                let params = new URLSearchParams();
                params.append("email",this.email);
                axios
                    .post('/universal/getRegCode',params)
                    .then((res)=>{
                        if(res.data=="该邮箱已注册！"){
                            alert(res.data)
                        }else {
                            this.returnRegCode = res.data;
                            console.log(this.returnRegCode )
                            this.isDaojiShi = true;//倒计时
                            let i = 60;
                            let _this = this;
                            clearInterval(this.daoji);
                            this.daoji = setInterval( ()=>{
                                i-=1;
                                console.log(i)
                                _this.miao = i;
                                if(i == 0){
                                    _this.getRegVal = "重新获取";
                                    _this.returnRegCode = undefined;
                                    clearInterval(_this.daoji);
                                    _this.isDaojiShi = false;
                                }
                            },1000);
                            alert("验证码已发送至邮箱请查收！")
                        }
                    })
                    .catch(function (error) {
                        console.log("error:"+error);
                    });
            }else {
                alert("请输入邮箱！");
            }
        },
        getReg(){
            let params = new URLSearchParams();
            params.append("username",this.username);
            params.append("email",this.email);
            params.append("e_password",this.e_password);
            axios
                .post('/universal/dealRegEmail',params)
                .then((res)=>{
                    alert(res.data)
                    if(res.data=="注册成功！"){
                        window.location.href = "/universal/getUserLogin";
                    }
                })
                .catch(function (error) {
                    console.log("error:"+error);
                });
        }
    },
    watch:{
        regCode(newVal,oldVal){
            mounted:{
                if(newVal==this.returnRegCode && this.returnRegCode!=''){
                  this.isDisabled = false;
                }else {
                    this.$message({
                        type:'error',
                        message:'验证码错误！',
                        duration: 2000
                    })
                }
            }
        }
    },
})

