Vue.component("myprompts",{//提示
    template: "#prompts"
})
const provinces = ["北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏",
    "浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南",
    "陕西","甘肃","青海","内蒙古","广西","西藏","宁夏","新疆","香港","澳门","台湾"];
const app1 = new Vue({
        el:'#app1',
        data:{
            city: "", inType: "", year: "", tableTitle: "", monQua: "",
            citys: provinces, industryTypes: [], years: [], tableTitles: [], monQuas: [],
            conTabInfoes: [],isShow: false,isMyDefFooter:true,isMyOverFooter:false,
            isDefTables:false, searchText: "",defTableListes:'',allDefTableListes:'',
            pagesTotal:0,currentPage:1,pagesize:2,
        },
        beforeCreate(){
            this.$nextTick(()=>{//this.$nextTick才可以获取dom
                axios
                    .get('/users/getDefaultDatasList')
                    .then((res)=>{
                        if(res.data.length>2){
                            let dataList = new Array();
                            dataList.push(res.data[0]);
                            dataList.push(res.data[1]);
                            this.defTableListes = dataList;
                            this.allDefTableListes = res.data;
                            this.pagesTotal = this.allDefTableListes.length;
                        }else{
                            this.defTableListes = res.data;
                            this.allDefTableListes = res.data;
                        }
                        if (this.defTableListes[0].city == null){
                            this.isDefTables=false;
                            this.isMyOverFooter=false;
                            this.isMyDefFooter=true;
                        }else {
                            this.isDefTables=true;
                            this.isMyOverFooter=true;
                            this.isMyDefFooter=false;}
                    })
                    .catch(function (err) {
                        console.log(err)
                    });
                axios//地图
                    .get('/universal/getCityNums')
                    .then((res)=>{
                        //1创建
                        let myChart = echarts.init(document.getElementById("getMyMap"));
                        var mapName = 'china'
                        var data = [
                            {name:"北京",value:199},
                            {name:"天津",value:42},
                            {name:"河北",value:102},
                            {name:"山西",value:81},
                            {name:"内蒙古",value:47},
                            {name:"辽宁",value:67},
                            {name:"吉林",value:82},
                            {name:"黑龙江",value:123},
                            {name:"上海",value:24},
                            {name:"江苏",value:92},
                            {name:"浙江",value:114},
                            {name:"安徽",value:109},
                            {name:"福建",value:116},
                            {name:"江西",value:91},
                            {name:"山东",value:119},
                            {name:"河南",value:137},
                            {name:"湖北",value:116},
                            {name:"湖南",value:114},
                            {name:"重庆",value:91},
                            {name:"四川",value:125},
                            {name:"贵州",value:62},
                            {name:"云南",value:83},
                            {name:"西藏",value:9},
                            {name:"陕西",value:80},
                            {name:"甘肃",value:56},
                            {name:"青海",value:10},
                            {name:"宁夏",value:18},
                            {name:"新疆",value:180},
                            {name:"广东",value:123},
                            {name:"广西",value:59},
                            {name:"海南",value:14},
                        ];

                        var geoCoordMap = {};
                        /*获取地图数据*/
                        myChart.showLoading();
                        var mapFeatures = echarts.getMap(mapName).geoJson.features;
                        myChart.hideLoading();
                        mapFeatures.forEach(function(v) {
                            // 地区名称
                            var name = v.properties.name;
                            // console.log(name)
                            // 地区经纬度
                            geoCoordMap[name] = v.properties.cp;
                        });

                        var convertData = function(data) {
                            var res = [];
                            for (var i = 0; i < data.length; i++) {
                                var geoCoord = geoCoordMap[data[i].name];
                                if (geoCoord) {
                                    res.push({
                                        name: data[i].name,
                                        value: geoCoord.concat(data[i].value),
                                    });
                                }
                            }
                            return res;
                        };
                        option = {
                            title: {text: 'Line Chart'},
                            tooltip: {//鼠标hover提示
                                padding: 0,
                                enterable: true,
                                transitionDuration: 1,
                                textStyle: {
                                    color: '#000',
                                    decoration: 'none',
                                },
                                formatter: function(params) {
                                    // console.log('par:',params)
                                    var tipHtml = '';
                                    tipHtml = '<div style="width:150px;height:80px;background:rgba(22,80,158,0.8);' +
                                        'border:1px solid rgba(7,166,255,0.7)">'

                                        +'<div style="width:100%;height:40px;line-height:40px;' +
                                        'border-bottom:2px solid rgba(7,166,255,0.7);padding:0 20px">'
                                        +'<i style="display:inline-block;width:10px;height:10px;' +
                                        'background:#16d6ff;border-radius:40px;">'+'</i>'
                                        +'<span style="margin-left:10px;color:#fff;font-size:16px;">'+params.name+'</span>'+'</div>'

                                        +'<div style="width:73%;height:40px;line-height:40px;">'
                                        +'<p style="position:absolute;color:#fff;font-size:12px;margin:-5px 0px 0px 22px;">'+
                                        '<i style="display:inline-block;width:8px;height:8px;background:#16d6ff;border-radius:40px;">'+'</i>'
                                        +'<span style="margin-left: 10px;">'+'总数：'+'</span>'+'<span style="color:#11ee7d;">'+params.value+'</span>'+'  条'+'</p>'

                                        +'</div>';
                                    return tipHtml;
                                }

                            },
                            visualMap: {//柱子
                                show: true,
                                min: 0,
                                max: 500,
                                left: '10%',
                                top: 'bottom',
                                calculable: true,
                                seriesIndex: [1],
                                inRange: {
                                    color: ['#234d89', '#675e8d'] // 蓝绿
                                }
                            },
                            geo: {//地图
                                show: true,
                                map: mapName,
                                left: '20%',
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: false,
                                    }
                                },
                                roam: true,//地图平移放大缩小
                                itemStyle: {
                                    normal: {//地图版块样式
                                        areaColor: '#023677',
                                        borderColor: '#c769c5',
                                    },
                                    emphasis: {//鼠标hover样式
                                        areaColor: '#4499d0',
                                    }
                                }
                            },
                            series: [
                                {
                                    name: '散点',
                                    type: 'scatter',
                                    coordinateSystem: 'geo',
                                    data: convertData(data),
                                    symbolSize: function(val) {
                                        return val[2] / 10;
                                    },
                                    label: {
                                        normal: {
                                            formatter: '{b}',
                                            position: 'right',
                                            show: true
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    itemStyle: {
                                        normal: {
                                            color: '#dfc511'
                                        }
                                    },
                                    tooltip: {//鼠标hover提示
                                        trigger: 'item',
                                        padding: 0,
                                        enterable: true,
                                        transitionDuration: 1,
                                        textStyle: {
                                            color: '#000',
                                            decoration: 'none',
                                        },
                                        formatter: function(params) {
                                            // console.log('par:',params)
                                            var tipHtml = '';
                                            tipHtml = '<div style="width:150px;height:80px;background:rgba(22,80,158,0.8);' +
                                                'border:1px solid rgba(7,166,255,0.7)">'

                                                +'<div style="width:100%;height:40px;line-height:40px;' +
                                                'border-bottom:2px solid rgba(7,166,255,0.7);padding:0 20px">'
                                                +'<i style="display:inline-block;width:10px;height:10px;' +
                                                'background:#16d6ff;border-radius:40px;">'+'</i>'
                                                +'<span style="margin-left:10px;color:#fff;font-size:16px;">'+params.name+'</span>'+'</div>'

                                                +'<div style="width:73%;height:40px;line-height:40px;">'
                                                +'<p style="position:absolute;color:#fff;font-size:12px;margin:-5px 0px 0px 22px;">'+
                                                '<i style="display:inline-block;width:8px;height:8px;background:#16d6ff;border-radius:40px;">'+'</i>'
                                                +'<span style="margin-left: 10px;">'+'总数：'+'</span>'+'<span style="color:#11ee7d;">'+params.value+'</span>'+'  条'+'</p>'

                                                +'</div>';
                                            return tipHtml;
                                        }

                                    },
                                },
                                {
                                    type: 'map',
                                    map: mapName,
                                    geoIndex: 0,
                                    aspectScale: 0.75, //长宽比
                                    showLegendSymbol: false, // 存在legend时显示
                                    label: {
                                        normal: {
                                            formatter: '{b}',
                                            position: 'right',
                                            show: true
                                        },
                                        emphasis: {
                                            show: false,
                                            textStyle: {
                                                color: '#d6ccca'
                                            }
                                        }
                                    },
                                    roam: true,
                                    itemStyle: {
                                        normal: {
                                            areaColor: '#031525',
                                            borderColor: '#3B5077',
                                        },
                                        emphasis: {
                                            areaColor: '#2B91B7'
                                        }
                                    },
                                    animation: false,
                                    data: data
                                },
                            ]
                        };
                        //3调用
                        myChart.setOption(option);
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            })
        },
        watch:{
            city(city,oldVal){
                mounted :{
                    let params = new URLSearchParams();
                    params.append("city",city);//vue post请求传参需new URLSearchParams();
                    axios
                    // .get('/users/getIntypeByCity?city=北京')
                        .post('/users/getIntypeByCity',params)
                        .then(/*function (res) {
                            console.log("res:"+res.data)
                            $.each(res,function (index,values) {
                                console.log("res2:"+values);
                               industryTypes[index] = values;
                            })
                        }*/
                            //res=>(this.industryTypes=res.data)//这种写法this才是指向的Vue
                            (response)=>{//这种写法this才是指向的Vue
                                this.industryTypes=response.data;
                                this.inType = "";
                            })
                        .catch(function (error) { // 请求失败处理
                            console.log("error:"+error);
                        });
                }
            },
            inType(inType,oldVal){
                mounted :{
                    let params = new URLSearchParams();
                    params.append("city",this.city);
                    params.append("name",inType);
                    axios
                        .post('/users/getYearByCityType',params)
                        .then(
                            (response)=>{
                                this.years=response.data;
                                this.year = "";
                            })
                        .catch(function (error) {
                            console.log("error:"+error);
                        });
                }
            },
            year(year,oldVal){
                mounted :{
                    let params = new URLSearchParams();
                    params.append("city",this.city);
                    params.append("name",this.inType);
                    params.append("year",year);
                    axios
                        .post('/users/getTableTitleByCiTyYe',params)
                        .then(
                            (response)=>{
                                this.tableTitles=response.data;
                                this.tableTitle = "";
                            })
                        .catch(function (error) {
                            console.log("error:"+error);
                        });
                }
            },
            tableTitle(tableTitle,oldVal){
                mounted :{
                    let params = new URLSearchParams();
                    params.append("city",this.city);
                    params.append("name",this.inType);
                    params.append("year",this.year);
                    params.append("tableTitle",tableTitle);
                    axios
                        .post('/users/getMonQuaTitleByCiTyYeTa',params)
                        .then(
                            (response)=>{
                                this.monQuas=response.data;
                                this.monQua = "";
                            })
                        .catch(function (error) {
                            console.log("error:"+error);
                        });
                }
            },
            monQua(monQua,oldVal){
                mounted :{
                    let params = new URLSearchParams();
                    params.append("city",this.city);
                    params.append("name",this.inType);
                    params.append("year",this.year);
                    params.append("tableTitle",this.tableTitle);
                    params.append("monQua",monQua);
                    axios
                        .post('/users/getConditionSerInfo',params)
                        .then(
                            (response)=>{
                                console.log("1:",response.data)
                                this.conTabInfoes = response.data;
                                console.log("2:",this.conTabInfoes.city)
                                if(this.conTabInfoes.city == null){
                                    this.isDefTables=true;
                                    this.isShow=true;
                                    this.isMyOverFooter=true;
                                    this.isMyDefFooter=false;
                                }else {
                                    this.isDefTables=false;
                                    this.isShow=false;
                                    this.isMyOverFooter=true;
                                    this.isMyDefFooter=false;
                                }})
                        .catch(function (error) {
                            console.log("error:"+error);
                        });
                     }
                    },
            currentPage(curPage,oldPage){
                mounted :{

                }
            },
            },
        methods: {
            /*allDefTableListes分页*/
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            //改变当前页
            handleCurrentChange(val) {
                this.currentPage = val
                let pages = this.currentPage*this.pagesize
                let dataList = new Array();
                if(pages-1<=this.allDefTableListes.length){
                    for(var i=pages-this.pagesize;i<=pages-1;i++){
                        dataList.push(this.allDefTableListes[i]);
                    }
                }else {
                    for(var i=pages-this.pagesize;i<=this.allDefTableListes.length-1;i++){
                        dataList.push(this.allDefTableListes[i]);
                    }
                }
                this.defTableListes = dataList
            },
            //是否登录
            getRegLog(){
                axios
                    .post('/universal/getUsers')
                    .then((res)=>{
                        if(res.data.account == undefined){
                           this.$message({
                               type: 'warning',
                               message: '请先登录！',
                               duration: 3000
                           })
                       }else{
                           window.location.href = "/users/getMyInfos";
                       }
                    })
                    .catch((re)=>{
                        console.log(re.message)
                    })
            },
            //可视化中心
            getVisualCenter(){
                axios
                    .get('/universal/getUsers')
                    .then((re)=>{
                        if(re.data.account == undefined){
                            this.$message({
                                type: 'warning',
                                message: '请先登录！',
                                duration: 3000
                            })
                        }else if(re.data.userstatus != '3'){
                            this.$message({
                                type: 'warning',
                                message: '请先开通会员后访问！',
                                duration: 3000});
                        }else{
                            window.location.href = "/universal/getVisualCenter";
                        }
                    })
                    .catch((re)=>{console.log(re.message)})
            },
        },
        })
  /*  $(function () {
    alert('click');
    $.ajax({
        url: '/users/getDefaultDatasList',
        type: 'get',
        success: function (res) {
            defTableListes = res;
            console.log(defTableListes )
        },
        error: function (res) {
            console.log(res.state())
        }
    })
})*/

