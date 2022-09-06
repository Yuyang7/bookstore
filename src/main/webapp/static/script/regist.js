window.onload = function () {

    let vue = new Vue({
            el: "#registForm",
            data: {
                uname: "",
                pwd: "1111111111",
                pwdConfirm: "1111111111",
                email: "",
                verifyCode: "",
                unameFlag:"",
            },
            methods: {
                //用户名唯一
                ckUname() {
                    let ckUnameSpan = document.getElementById("ckUname");
                    axios.get(
                        "/book/user/ckUname/" + vue.uname
                    ).then(response => {
                        var ckUname=response.data.uname;
                        if (ckUname != null) {
                            ckUnameSpan.style.visibility = "visible";
                                vue.uname = "";
                                vue.unameFlag = "1";
                            } else {
                            ckUnameSpan.style.visibility = "hidden";
                                vue.unameFlag = "";
                            }
                        }
                    ).catch(reason => {
                        console.log(reason.message)
                    })
                },
                ckVerifyCode(){
                    //检查验证码
                    let verifyCodeSpan = document.getElementById("verifyCodeSpan");
                    axios.get(
                        "/book/user/ckVerifyCode/" + vue.verifyCode
                    ).then(response => {
                        var message=response.data;
                        console.log(message == null);
                        if (message == null) {
                            verifyCodeSpan.style.visibility = "visible";
                            vue.verifyCode = "";
                            vue.refresh();

                        } else {
                            verifyCodeSpan.style.visibility = "hidden";
                            if (vue.unameFlag.length==0){
                                document.getElementById("registForm").submit();
                            }
                        }
                    }).catch(function (reason) {
                        console.log(reason.message)
                    })
                },

                refresh() {
                    document.getElementById('captcha_img').src = "kaptcha?" + Math.random();
                },
                registerCheck: function () {

                    event.preventDefault();
                    this.ckUname();
                    let unameReg = /[0-9a-zA-Z]{6,16}/
                    let unameSpan = document.getElementById("unameSpan");
                    if (!unameReg.test(this.uname)) {
                        unameSpan.style.visibility = "visible";
                        return;
                    } else {
                        unameSpan.style.visibility = "hidden";
                    }

                    // 密码的长度至少为8位
                    let pwdReg = /[\w]{8,}/;
                    let pwdSpan = document.getElementById("pwdSpan");

                    if (!pwdReg.test(this.pwd)) {
                        pwdSpan.style.visibility = "visible";
                        return;
                    } else {
                        pwdSpan.style.visibility = "hidden";
                    }
                    let pwdSpan2 = document.getElementById("pwdSpan2");
                    // 密码两次输入不一致
                    if (this.pwd != this.pwdConfirm) {
                        pwdSpan2.style.visibility = "visible";
                        return;
                    } else {
                        pwdSpan2.style.visibility = "hidden";
                    }
                    // 请输入正确的邮箱格式
                    let emailReg = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
                    let emailSpan = document.getElementById("emailSpan");

                    if (!emailReg.test(this.email)) {
                        emailSpan.style.visibility = "visible";
                        return;
                    } else {
                        emailSpan.style.visibility = "hidden";
                    }
                    this.ckVerifyCode();
                }
            }
        });
}
