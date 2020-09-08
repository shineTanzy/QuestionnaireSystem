const app = new Vue({
    el:'#app',
    data:{
        qnId:'',questions:[],paperName:'',uId:''
    },
    beforeCreate() {
        this.$nextTick(()=>{
            axios
                .get('/admin/getSessionQnId')
                .then((res)=>{
                    this.qnId = res.data;
                    var par = new URLSearchParams();
                    par.append("qnId",res.data);
                    axios
                        .post('/user/getNaireQuestions',par)
                        .then((res)=>{
                            this.paperName = res.data[0].paperName;
                            this.questions = res.data;
                        })
                        .catch((re)=>{console.log(re.data);})
                })
                .catch((re)=>{console.log(re.data);})
                axios
                    .get('/user/getSessionUid')
                    .then((res)=>{
                        this.uId = res.data;
                    })
                    .catch((re)=>{})
        })
    },
    methods:{
        getMyNaire(){
            var $div = $(".myDiv");
            var myAnswers = new Array();
            var quIdes = new Array();
            for(var i=0;i<this.questions.length;i++){
                if(this.questions[i].classification =="单选题"){
                    for(var n=0;n<$div.eq(i).children('label').length;n++){
                        if($div.eq(i).children('label').eq(n).children('input:checked').val()){
                            quIdes.push(this.questions[i].quId);
                            myAnswers.push({
                                qnId:this.qnId,uId:this.uId,
                                qtsName:this.questions[i].qtsName.split(".")[1],
                                classification:"单选题",
                                answer:$div.eq(i).children('label').eq(n).children('input:checked').val()
                            })
                        }
                    }
                }else if(this.questions[i].classification =="多选题"){
                   var $duxuan =  $div.eq(i).children('label');
                    for(var n=0;n<$duxuan.length;n++){
                        if($duxuan.eq(n).children('input:checked').val()){
                            quIdes.push(this.questions[i].quId);
                            myAnswers.push({
                                qnId:this.qnId,uId:this.uId,
                                qtsName:this.questions[i].qtsName.split(".")[1],
                                classification:"多选题",
                                answer:$duxuan.eq(n).children('input:checked').val()
                            })
                        }
                    }
                }else{
                    var val = $div.eq(i).children('textarea').val();
                    if(val){
                        quIdes.push(this.questions[i].quId);
                        myAnswers.push({
                            qnId:this.qnId,uId:this.uId,
                            qtsName:this.questions[i].qtsName.split(".")[1],
                            classification:"简答题",
                            answer:val
                        })
                    }
                }
            }
            console.log("myanswers",myAnswers)
              function unique(array) {
                  return Array.from(new Set(array));
              }
            if(unique(quIdes).length == this.questions.length){
                axios
                    .request({
                        url: "/user/addMyAnswers",
                        data: myAnswers,
                        method: 'post'
                    })
                    .then((re)=>{
                        if(re.data){
                            this.$message({type:'success',message:'提交成功!',duration:1000});
                            setTimeout(function () {
                                window.location.href = "/user/toUserIndex";
                            },350)
                        }else {this.$message({type:'info',message:'提交失败!',duration:1000});}
                    })
                    .catch((re)=>{console.log(re.data)})
            }else {
                this.$message({type:'info',message:'不能有空题！',duration:1000});
            }
        }
    }

})