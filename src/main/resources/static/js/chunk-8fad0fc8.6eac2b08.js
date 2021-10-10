(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-8fad0fc8"],{5899:function(e,r){e.exports="\t\n\v\f\r                　\u2028\u2029\ufeff"},"58a8":function(e,r,t){var s=t("1d80"),o=t("577e"),a=t("5899"),n="["+a+"]",i=RegExp("^"+n+n+"*"),c=RegExp(n+n+"*$"),u=function(e){return function(r){var t=o(s(r));return 1&e&&(t=t.replace(i,"")),2&e&&(t=t.replace(c,"")),t}};e.exports={start:u(1),end:u(2),trim:u(3)}},"5e89":function(e,r,t){var s=t("861d"),o=Math.floor;e.exports=function(e){return!s(e)&&isFinite(e)&&o(e)===e}},7156:function(e,r,t){var s=t("861d"),o=t("d2bb");e.exports=function(e,r,t){var a,n;return o&&"function"==typeof(a=r.constructor)&&a!==t&&s(n=a.prototype)&&n!==t.prototype&&o(e,n),e}},"73cf":function(e,r,t){"use strict";t.r(r);var s=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",[t("div",{staticClass:"background"},[t("img",{attrs:{src:e.imgSrc,width:"100%",height:"100%",alt:""}})]),t("div",{staticClass:"top"},[t("el-card",{staticClass:"card"},[t("div",[t("el-form",{ref:"registerForm",staticClass:"demo-ruleForm",attrs:{model:e.registerForm,"status-icon":"",rules:e.rules,"label-position":"left","label-width":"80px"}},[t("h3",[e._v("Register")]),t("el-form-item",{attrs:{label:"username",prop:"userName",required:""}},[t("el-input",{attrs:{"prefix-icon":"el-icon-user"},model:{value:e.registerForm.userName,callback:function(r){e.$set(e.registerForm,"userName",e._n(r))},expression:"registerForm.userName"}})],1),t("el-form-item",{attrs:{label:"password",prop:"password",required:""}},[t("el-input",{attrs:{type:"password",autocomplete:"off","prefix-icon":"el-icon-lock"},model:{value:e.registerForm.password,callback:function(r){e.$set(e.registerForm,"password",r)},expression:"registerForm.password"}})],1),t("el-form-item",{attrs:{label:"confirm",prop:"checkPass",required:""}},[t("el-input",{attrs:{type:"password",autocomplete:"off","prefix-icon":"el-icon-lock"},model:{value:e.registerForm.checkPass,callback:function(r){e.$set(e.registerForm,"checkPass",r)},expression:"registerForm.checkPass"}})],1),t("el-form-item",{attrs:{label:"phone",prop:"userPhone",required:""}},[t("el-input",{attrs:{"prefix-icon":"el-icon-mobile-phone"},model:{value:e.registerForm.userPhone,callback:function(r){e.$set(e.registerForm,"userPhone",r)},expression:"registerForm.userPhone"}})],1),t("div",[t("el-button",{attrs:{type:"primary",plain:""},on:{click:function(r){return e.register("registerForm")}}},[e._v("Submit")]),t("el-button",{attrs:{plain:""},on:{click:function(r){return e.resetForm("registerForm")}}},[e._v("Reset")])],1),t("div",[t("div",{attrs:{id:"nav"}},[t("router-link",{attrs:{to:"/"}},[e._v("Already have an account? Click here")])],1)])],1)],1)])],1)])},o=[],a=(t("8ba4"),t("a9e3"),{name:"Register",data:function(){var e=this,r=function(e,r,t){if(!r)return t(new Error("username is required!"));Number.isInteger(r)?t(new Error("username can not be numbers!")):t()},s=function(e,r,t){if(!r)return t(new Error("phone is required!"));t()},o=/(?=.*[\d])/,a=/(?=.*[a-zA-Z])/,n=function(e,r,t){if(!r)return t(new Error("password is required!"));r.length>16?t(new Error("Password should not be more than 16 characters")):r.length<8?t(new Error("Password should not be less than 8 characters")):o.test(r)&&a.test(r)?t():t(new Error("The password must consist of numbers and letters!"))},i=function(r,t,s){""===t?s(new Error("please confirm your password!")):t!==e.registerForm.password?s(new Error("the two passwords are inconsistent!")):s()};return{imgSrc:t("89ea"),registerForm:{password:"",checkPass:"",userName:"",userPhone:""},rules:{password:[{validator:n,trigger:"blur"}],checkPass:[{validator:i,trigger:"blur"}],userName:[{validator:r,trigger:"blur"}],userPhone:[{validator:s,trigger:"blur"}]}}},methods:{register:function(e){var r=this;this.$refs[e].validate((function(e){if(!e)return!1;r.$axios.post("/register",{userName:r.registerForm.userName,password:r.registerForm.password,checkPass:r.registerForm.checkPass,userPhone:r.registerForm.userPhone}).then((function(e){"SUCCESS"===e.data.msg?(r.$message({showClose:!0,message:"Register successfully! You can login now",type:"success"}),r.$router.push("/")):"USER_NAME_EXIST"===e.data.msg?r.$message({showClose:!0,message:"this username already been used",type:"error"}):"UNKNOWN ERROR"===e.data.msg&&r.$message({showClose:!0,message:"some error, please register again",type:"error"})})).catch((function(e){console.log(e)}))}))},resetForm:function(e){this.$refs[e].resetFields()}}}),n=a,i=(t("7ae2"),t("2877")),c=Object(i["a"])(n,s,o,!1,null,"1d7d53a1",null);r["default"]=c.exports},"7ae2":function(e,r,t){"use strict";t("dfe8")},"89ea":function(e,r,t){e.exports=t.p+"img/register.ab7aadf7.jpg"},"8ba4":function(e,r,t){var s=t("23e7"),o=t("5e89");s({target:"Number",stat:!0},{isInteger:o})},a9e3:function(e,r,t){"use strict";var s=t("83ab"),o=t("da84"),a=t("94ca"),n=t("6eeb"),i=t("5135"),c=t("c6b6"),u=t("7156"),l=t("d9b5"),f=t("c04e"),m=t("d039"),p=t("7c73"),d=t("241c").f,g=t("06cf").f,h=t("9bf2").f,b=t("58a8").trim,w="Number",v=o[w],F=v.prototype,N=c(p(F))==w,E=function(e){if(l(e))throw TypeError("Cannot convert a Symbol value to a number");var r,t,s,o,a,n,i,c,u=f(e,"number");if("string"==typeof u&&u.length>2)if(u=b(u),r=u.charCodeAt(0),43===r||45===r){if(t=u.charCodeAt(2),88===t||120===t)return NaN}else if(48===r){switch(u.charCodeAt(1)){case 66:case 98:s=2,o=49;break;case 79:case 111:s=8,o=55;break;default:return+u}for(a=u.slice(2),n=a.length,i=0;i<n;i++)if(c=a.charCodeAt(i),c<48||c>o)return NaN;return parseInt(a,s)}return+u};if(a(w,!v(" 0o1")||!v("0b1")||v("+0x1"))){for(var k,I=function(e){var r=arguments.length<1?0:e,t=this;return t instanceof I&&(N?m((function(){F.valueOf.call(t)})):c(t)!=w)?u(new v(E(r)),t,I):E(r)},P=s?d(v):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger,fromString,range".split(","),x=0;P.length>x;x++)i(v,k=P[x])&&!i(I,k)&&h(I,k,g(v,k));I.prototype=F,F.constructor=I,n(o,w,I)}},dfe8:function(e,r,t){}}]);