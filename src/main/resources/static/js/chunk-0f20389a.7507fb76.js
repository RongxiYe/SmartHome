(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0f20389a"],{2819:function(e,r,t){"use strict";t("886c")},5899:function(e,r){e.exports="\t\n\v\f\r                　\u2028\u2029\ufeff"},"58a8":function(e,r,t){var s=t("1d80"),o=t("577e"),n=t("5899"),a="["+n+"]",i=RegExp("^"+a+a+"*"),u=RegExp(a+a+"*$"),c=function(e){return function(r){var t=o(s(r));return 1&e&&(t=t.replace(i,"")),2&e&&(t=t.replace(u,"")),t}};e.exports={start:c(1),end:c(2),trim:c(3)}},"5e89":function(e,r,t){var s=t("861d"),o=Math.floor;e.exports=function(e){return!s(e)&&isFinite(e)&&o(e)===e}},7156:function(e,r,t){var s=t("861d"),o=t("d2bb");e.exports=function(e,r,t){var n,a;return o&&"function"==typeof(n=r.constructor)&&n!==t&&s(a=n.prototype)&&a!==t.prototype&&o(e,a),e}},"73cf":function(e,r,t){"use strict";t.r(r);var s=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"register"},[t("el-form",{ref:"registerForm",staticClass:"demo-ruleForm",attrs:{model:e.registerForm,"status-icon":"",rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"username",prop:"userName",required:""}},[t("el-input",{attrs:{"prefix-icon":"el-icon-user"},model:{value:e.registerForm.userName,callback:function(r){e.$set(e.registerForm,"userName",e._n(r))},expression:"registerForm.userName"}})],1),t("el-form-item",{attrs:{label:"password",prop:"password",required:""}},[t("el-input",{attrs:{type:"password",autocomplete:"off","prefix-icon":"el-icon-lock"},model:{value:e.registerForm.password,callback:function(r){e.$set(e.registerForm,"password",r)},expression:"registerForm.password"}})],1),t("el-form-item",{attrs:{label:"confirm",prop:"checkPass",required:""}},[t("el-input",{attrs:{type:"password",autocomplete:"off","prefix-icon":"el-icon-lock"},model:{value:e.registerForm.checkPass,callback:function(r){e.$set(e.registerForm,"checkPass",r)},expression:"registerForm.checkPass"}})],1),t("el-form-item",{attrs:{label:"phone",prop:"userPhone",required:""}},[t("el-input",{attrs:{"prefix-icon":"el-icon-mobile-phone"},model:{value:e.registerForm.userPhone,callback:function(r){e.$set(e.registerForm,"userPhone",r)},expression:"registerForm.userPhone"}})],1),t("el-form-item",[t("el-button",{attrs:{type:"primary",plain:""},on:{click:function(r){return e.register("registerForm")}}},[e._v("Submit")]),t("el-button",{attrs:{plain:""},on:{click:function(r){return e.resetForm("registerForm")}}},[e._v("Reset")])],1)],1)],1)},o=[],n=(t("8ba4"),t("a9e3"),{name:"Register",data:function(){var e=this,r=function(e,r,t){if(!r)return t(new Error("Please enter user name!"));Number.isInteger(r)?t(new Error("User name can not be numbers!")):t()},t=function(e,r,t){if(!r)return t(new Error("Please enter your phone!"));t()},s=function(r,t,s){""===t?s(new Error("Please confirm your password!")):t!==e.registerForm.password?s(new Error("The two passwords are inconsistent!")):s()};return{registerForm:{password:"",checkPass:"",userName:"",userPhone:""},rules:{password:[{message:"Please enter password",trigger:"blur"},{min:6,max:20,message:"Password should be 6 ～ 20 characters",trigger:"blur"}],checkPass:[{validator:s,trigger:"blur"}],userName:[{validator:r,trigger:"blur"}],userPhone:[{validator:t,trigger:"blur"}]}}},methods:{register:function(e){var r=this;this.$refs[e].validate((function(e){if(!e)return!1;r.$axios.post("/register",{userName:r.registerForm.userName,password:r.registerForm.password,checkPass:r.registerForm.checkPass,userPhone:r.registerForm.userPhone}).then((function(e){"SUCCESS"===e.data.msg?(r.$message({showClose:!0,message:"Register successfully! You can login now",type:"success"}),r.$router.push("/")):"USER_NAME_EXIST"===e.data.msg?r.$message({showClose:!0,message:"this username already been used",type:"error"}):"UNKNOWN ERROR"===e.data.msg&&r.$message({showClose:!0,message:"some error, please register again",type:"error"})})).catch((function(e){console.log(e)}))}))},resetForm:function(e){this.$refs[e].resetFields()}}}),a=n,i=(t("2819"),t("2877")),u=Object(i["a"])(a,s,o,!1,null,"407291af",null);r["default"]=u.exports},"886c":function(e,r,t){},"8ba4":function(e,r,t){var s=t("23e7"),o=t("5e89");s({target:"Number",stat:!0},{isInteger:o})},a9e3:function(e,r,t){"use strict";var s=t("83ab"),o=t("da84"),n=t("94ca"),a=t("6eeb"),i=t("5135"),u=t("c6b6"),c=t("7156"),l=t("d9b5"),m=t("c04e"),f=t("d039"),p=t("7c73"),g=t("241c").f,d=t("06cf").f,h=t("9bf2").f,b=t("58a8").trim,w="Number",F=o[w],N=F.prototype,v=u(p(N))==w,E=function(e){if(l(e))throw TypeError("Cannot convert a Symbol value to a number");var r,t,s,o,n,a,i,u,c=m(e,"number");if("string"==typeof c&&c.length>2)if(c=b(c),r=c.charCodeAt(0),43===r||45===r){if(t=c.charCodeAt(2),88===t||120===t)return NaN}else if(48===r){switch(c.charCodeAt(1)){case 66:case 98:s=2,o=49;break;case 79:case 111:s=8,o=55;break;default:return+c}for(n=c.slice(2),a=n.length,i=0;i<a;i++)if(u=n.charCodeAt(i),u<48||u>o)return NaN;return parseInt(n,s)}return+c};if(n(w,!F(" 0o1")||!F("0b1")||F("+0x1"))){for(var P,I=function(e){var r=arguments.length<1?0:e,t=this;return t instanceof I&&(v?f((function(){N.valueOf.call(t)})):u(t)!=w)?c(new F(E(r)),t,I):E(r)},k=s?g(F):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger,fromString,range".split(","),x=0;k.length>x;x++)i(F,P=k[x])&&!i(I,P)&&h(I,P,d(F,P));I.prototype=N,N.constructor=I,a(o,w,I)}}}]);
//# sourceMappingURL=chunk-0f20389a.7507fb76.js.map