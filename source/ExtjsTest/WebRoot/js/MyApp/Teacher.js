/***
 * 本例创建MyApp.Teacher类，起别名Tcher。
 * 练习，通过Ext.require()方式手动引入，要调用的JS文件。
 * 
 * 
 * 以下是动态引入例子：
 *  window.rootUrl = "@Url.Content("~/")";
 *      Ext.Loader.setConfig({
 *           enabled: true,
 *           paths: {
 *               MyApp: rootUrl + "Resources/js/MyApp"
 *           }
 *      });
 *      
 *       我们可以将这段代码写在Layout中，因为它在每个页面中都要用到。
 *       在完成Loader 的配置以后，我们就可以移除掉对Person.js的引用了，然后我们的程序依然能够正确的运行：
 *       var Tom = Ext.create("MyApp.Person", {
 *           Name: 'Tom',
 *           Age: 26
 *       });
 *       Tom.Say("Hello");
 *       注意，我们这里使用的是类的全称，因为使用别名的时候没有办法找到正确的路径。
 *       加载器会通过类名类匹配路径中的MyApp，然后加载Person.js。
 **/

Ext.define('MyApp.Teacher',{
    config : {
        name : '',
        age : 0,
        school : '泰安市一中',
        gradeClass : '三年级二班'
    },
    alias : 'Tcher',
    sayHi : function(){
        console.log("大家好，我是"+this.name+"，我来自"+this.school+"，"+this.gradeClass+"。");
    },
    constructor : function(config){
        this.initConfig(config);
    }
});