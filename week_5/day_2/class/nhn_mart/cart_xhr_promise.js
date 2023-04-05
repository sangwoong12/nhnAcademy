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

        doLogin(event.target['userId'].value, event.target['userPassword'].value)
            .then((user) => {
                document.getElementById('loginFormContainer').setAttribute("style", "display:none");
                document.getElementById(`loginSuccessContainer`).setAttribute("style", "display:block;");

                document.getElementById("login-userId").textContent = user.userId;
                document.getElementById("login-userName").textContent = user.userName;
                document.getElementById("login-cartId").textContent = user.cartId;

                return doCart(user.cartId,user.userId);

            }).catch(e => {
            alert("id password 를 확인해주세요!");
            throw new Error(e.message);
        }).catch(e => { //doCart의 로직이 끝난이후 cart 를 받게 되면 then 실행
            alert("해당 url 에 따른 cart api 가 없습니다.");
            throw new Error(e.message);
        }).then((cart) => {
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
});
function doLogin(userId, userPassword) {
    const url = "http://133.186.144.236:8100/api/users/login";

    const user = {
        "userId": userId,
        "userPassword": userPassword
    }
    const promise = new Promise(function (resolve, reject){
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
                //jsom String -> object
                const user = JSON.parse(this.responseText);
                resolve(user);
            } else {
                reject(new Error("error"));
            }
        })
    });
    return promise;
}
function doCart(cartId,userId) {
    const url = "http://133.186.144.236:8100/api/nhnmart/shopping-cart/"+cartId;
    const promise = new Promise(function (resolve, reject){
        const xhr = new XMLHttpRequest();

        xhr.open("get",url);
        xhr.setRequestHeader("Content-type","application/json");
        xhr.setRequestHeader("X-USER-ID",userId);
        xhr.send();//body를 담는 역활과 함께 xhr자체를 request 함.

        xhr.addEventListener("load",function (){
            if(this.status===200){
                console.log("response :", this.responseText);
                const cart = JSON.parse(this.responseText);
                resolve(cart);
            } else {
                reject(new Error("error"));
            }
        })
    })
    return promise
}