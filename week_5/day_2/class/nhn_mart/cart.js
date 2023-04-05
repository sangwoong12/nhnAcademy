window.addEventListener("DOMContentLoaded",function () { //돔트리 분석이 끝나면 발생 : DOMContentLoaded
    'use strict';

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

    // 비동기 방식이기 때문에 리턴을 하여 값을 출력한다고 해도 값이 나오지 않는다.
    form.addEventListener("submit", function (event) {
        event.preventDefault();//스크립트로 구현을 할거기 때문에 submit 행위를 막음

        if( validateForm(event.target)===false ){
            return ;
        }

        if (validate(event.target['userId'].value, event.target['userPassword'].value)) {
            const loginFormContainer = document.getElementById('loginFormContainer');
            const loginSuccessContainer = document.getElementById(`loginSuccessContainer`);
            loginFormContainer.setAttribute("style", "display:none");
            loginSuccessContainer.setAttribute("style", "display:block;");

            const userInfo = getUserInfo();
            document.getElementById("login-userId").textContent = userInfo.userId;
            document.getElementById("login-userName").textContent = userInfo.userName;
            document.getElementById("login-cartId").textContent = userInfo.cartId;


            makeList();
        }
        else{
            alert("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    })
    function makeList() {
        const cartTable = document.getElementById("cartTable");
        const tbody = cartTable.getElementsByTagName("tbody")[0];
        for (const cartListElement of getCartList()) {
            const tr = document.createElement("tr");

            const td1 = document.createElement("td");
            td1.innerText = cartListElement.productId;
            const td2 = document.createElement("td");
            td2.innerText = cartListElement.name;
            const td3 = document.createElement("td");
            td3.innerText = cartListElement.price;
            const td4 = document.createElement("td");
            td4.innerText = cartListElement.amount;
            const td5 = document.createElement("td");
            td5.innerText = cartListElement.totalPrice;

            tr.append(td1, td2, td3, td4, td5);
            tbody.append(tr);
        }
    }
    function validate(userId, userPassword) {
        const response = {
            "userId": "nhnacademy",
            "userPassword": 1234,
        }
        return response.userId == userId && response.userPassword == userPassword;
    }
    function getUserInfo() {
        const response = {
            "cartId": 1000,
            "userId": "nhnacademy",
            "userName": "아카데미",
            "userPassword": 1234
        }
        return response;
    }
    function getCartList() {
        const response = [
            {
                "productId": "A0003",
                "name": "양파",
                "price": 1006,
                "amount": 6,
                "totalPrice": 6036
            },
            {
                "productId": "A0001",
                "name": "감자",
                "price": 1006,
                "amount": 5,
                "totalPrice": 5030
            },
            {
                "productId": "A0009",
                "name": "수박",
                "price": 1008,
                "amount": 7,
                "totalPrice": 7056
            },
            {
                "productId": "A0008",
                "name": "귤",
                "price": 1003,
                "amount": 8,
                "totalPrice": 8024
            },
            {
                "productId": "A0006",
                "name": "대파",
                "price": 1007,
                "amount": 1,
                "totalPrice": 1007
            },
            {
                "productId": "A0007",
                "name": "사과",
                "price": 1009,
                "amount": 9,
                "totalPrice": 9081
            },
            {
                "productId": "A00010",
                "name": "라면",
                "price": 1001,
                "amount": 4,
                "totalPrice": 4004
            },
            {
                "productId": "A0002",
                "name": "오이",
                "price": 1008,
                "amount": 1,
                "totalPrice": 1008
            },
            {
                "productId": "A0004",
                "name": "우유",
                "price": 1006,
                "amount": 2,
                "totalPrice": 2012
            },
            {
                "productId": "A0005",
                "name": "계란",
                "price": 1007,
                "amount": 9,
                "totalPrice": 9063
            }
        ];
        return response;
    }
});