(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0ac00f"],{"184f":function(e,s,o){"use strict";o.r(s);var a=function(){var e=this,s=e.$createElement,o=e._self._c||s;return o("div",{attrs:{id:"individual"}},[o("el-card",{staticClass:"box-card"},[o("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[o("h3",[e._v("CHANGE PHONE")])]),o("div",[o("el-form",{ref:"phoneForm",staticClass:"demo-ruleForm",attrs:{model:e.phoneForm,rules:e.rules1,"label-width":"150px"}},[o("el-form-item",{attrs:{label:"old phone",prop:"oldphone",required:""}},[o("el-input",{model:{value:e.phoneForm.oldphone,callback:function(s){e.$set(e.phoneForm,"oldphone",s)},expression:"phoneForm.oldphone"}})],1),o("el-form-item",{attrs:{label:"new phone",prop:"newphone",required:""}},[o("el-input",{model:{value:e.phoneForm.newphone,callback:function(s){e.$set(e.phoneForm,"newphone",s)},expression:"phoneForm.newphone"}})],1),o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:function(s){return e.submitPhoneForm("phoneForm")}}},[e._v("Submit")])],1)],1)],1)]),o("br"),o("el-card",{staticClass:"box-card"},[o("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[o("h3",[e._v("CHANGE PASSWORD")])]),o("div",[o("el-form",{ref:"passwordForm",staticClass:"demo-ruleForm",attrs:{model:e.passwordForm,rules:e.rules2,"label-width":"150px"}},[o("el-form-item",{attrs:{label:"old password",prop:"oldpsw",required:""}},[o("el-input",{attrs:{type:"password"},model:{value:e.passwordForm.oldpsw,callback:function(s){e.$set(e.passwordForm,"oldpsw",s)},expression:"passwordForm.oldpsw"}})],1),o("el-form-item",{attrs:{label:"new password",prop:"newpsw",required:""}},[o("el-input",{attrs:{type:"password"},model:{value:e.passwordForm.newpsw,callback:function(s){e.$set(e.passwordForm,"newpsw",s)},expression:"passwordForm.newpsw"}})],1),o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:function(s){return e.submitPasswordForm("passwordForm")}}},[e._v("Submit")])],1)],1)],1)])],1)},r=[],t={name:"Family",data:function(){return{size:"",userPhone:"",userName:"",phoneForm:{previousPhone:"",newPhone:""},passwordForm:{oldpsw:"",newpsw:""}}},mounted:function(){this.getUserInfo()},methods:{submitPasswordForm:function(e){var s=this;this.$refs[e].validate((function(e){e&&s.$axios.post("/user/changepsw",{oldpsw:s.passwordForm.oldpsw,newpsw:s.passwordForm.newpsw}).then((function(e){"Success"===e.data.data.cpswState?s.$message({showClose:!0,message:"Change password successfully!",type:"success"}):"WrongPsw"===e.data.data.cpswState?s.$message({showClose:!0,message:"Wrong Password!",type:"error"}):"SamePsw"===e.data.data.cpswState?s.$message({showClose:!0,message:"You can not enter same password!",type:"error"}):"UnknownError"===e.data.data.cpswState&&s.$message({showClose:!0,message:"Change password failed, please retry!",type:"error"}),console.log(e)}))}))},submitPhoneForm:function(e){var s=this;this.$refs[e].validate((function(e){e&&s.$axios.post("/user/changephone",{oldphone:s.phoneForm.oldphone,newphone:s.phoneForm.newphone}).then((function(e){"Success"===e.data.data.cphState?s.$message({showClose:!0,message:"Change phone successfully!",type:"success"}):"WrongPhone"===e.data.data.cphState?s.$message({showClose:!0,message:"Wrong Phone number!",type:"error"}):"SamePhone"===e.data.data.cphState?s.$message({showClose:!0,message:"You can not enter same Phone number!",type:"error"}):"UnknownError"===e.data.data.cpswState&&s.$message({showClose:!0,message:"Change Phone failed, please retry!",type:"error"})}))}))},getUserInfo:function(){var e=this;this.$axios.get("/individual").then((function(s){e.userPhone=s.data.data.phoneNum,e.userName=s.data.data.userName,console.log(s)})).catch((function(e){console.log(e)}))}}},n=t,l=o("2877"),p=Object(l["a"])(n,a,r,!1,null,"3ed44928",null);s["default"]=p.exports}}]);