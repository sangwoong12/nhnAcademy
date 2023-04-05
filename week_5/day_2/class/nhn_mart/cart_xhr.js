//콜백 함수 : 비동기 프로그래밍에서 한 테스크가 끝나기 까지 기다렸다가 실행되는 함수
//use : 다른함수가 파라미터에 함수를 인자로 받는다고 할때 이때 들어가게 되는 함수를 콜백 함수라고 하고 말그대로 인자로 넣어 사용
window.addEventListener("DOMContentLoaded",function () {

    const form = document.getElementById("loginFormContainer");

    const validateForm=function(form){
        if(form['userId'].value.trim() === '' ){
            alert("userId 입력해주세요");
            form['userId'].focus();
            return false;
        }
        if(form['userPassword'].value.trim() === '' ){
            alert("userPassword 입력해주세요");
            form['userPassword'].focus();
            return false;
        }
    }

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        if( validateForm(event.target)===false ){
            return ;
        }

        doLogin(event.target['userId'].value, event.target['userPassword'].value,function (user){
            document.getElementById('loginFormContainer').setAttribute("style", "display:none");
            document.getElementById(`loginSuccessContainer`).setAttribute("style", "display:block;");

            document.getElementById("login-userId").textContent = user.userId;
            document.getElementById("login-userName").textContent = user.userName;
            document.getElementById("login-cartId").textContent = user.cartId;

            doCart(user.cartId,user.userId,function (cart){

                const cartTable = document.getElementById("cartTable");
                const tbody = cartTable.getElementsByTagName("tbody")[0];
                for (const cartElement of cart) {
                    const tr = document.createElement("tr");

                    const td1 = document.createElement("td");
                    td1.innerText = cartElement.productId;
                    const td2 = document.createElement("td");
                    td2.innerText = cartElement.name;
                    const td3 = document.createElement("td");
                    td3.innerText = cartElement.price;
                    const td4 = document.createElement("td");
                    td4.innerText = cartElement.amount;
                    const td5 = document.createElement("td");
                    td5.innerText = cartElement.totalPrice;

                    tr.append(td1, td2, td3, td4, td5);
                    tbody.append(tr);
                }
            });
        });
    })
    function doLogin(userId, userPassword,loginSuccess) {
        const url = "http://133.186.144.236:8100/api/users/login";

        const user = {
            "userId": userId,
            "userPassword": userPassword
        }
        const userJson = JSON.stringify(user);
        console.log(userJson);

        const xhr = new XMLHttpRequest();
        xhr.open("POST",url);
        xhr.setRequestHeader("Content-type","application/json");
        // xhr.responseType="json";
        xhr.send(userJson);

        xhr.addEventListener("load",function (){
            if(this.status===200){
                console.log("response :", this.responseText);
                //json String -> object
                const user = JSON.parse(this.responseText);
                loginSuccess(user);
            } else {
                console.log("error");
                alert("아이디 또는 비밀번호가 틀렸습니다.");
            }
        })
    }
    function doCart(cartId,userId,successGetCart) {
        const url = "http://133.186.144.236:8100/api/nhnmart/shopping-cart/"+cartId;
        const xhr = new XMLHttpRequest();

        xhr.open("get",url);
        xhr.setRequestHeader("Content-type","application/json");
        xhr.setRequestHeader("X-USER-ID",userId);
        xhr.send();

        xhr.addEventListener("load",function (){
            if(this.status===200){
                console.log("response :", this.responseText);
                const cart = JSON.parse(this.responseText);
                successGetCart(cart);
            } else {
            }
        })
    }
});