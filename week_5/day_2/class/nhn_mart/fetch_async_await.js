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

    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const userId = event.target['userId'].value;
        const userPassword = event.target['userPassword'].value

        let user = null;
        if (validateForm(event.target) === false) {
            return;
        }
        try {
            user = await doLogin(userId, userPassword);
            alert(user.userId+":"+user.userName);
            document.getElementById('loginFormContainer').setAttribute("style", "display:none");
            document.getElementById(`loginSuccessContainer`).setAttribute("style", "display:block;");
            document.getElementById("login-userId").textContent = user.userId;
            document.getElementById("login-userName").textContent = user.userName;
            document.getElementById("login-cartId").textContent = user.cartId;

        } catch (e) {
            alert(e);
            return;
        }
        try {
            const cart = await doCart(user.cartId,user.userId);
            
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
        }catch (e){
            alert(e);
        }
    })
    async function doLogin(userId, userPassword) {
        const url = "http://133.186.144.236:8100/api/users/login";
        const data = {
            userId : userId,
            userPassword : userPassword
        }
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }
        const user = await fetch(url, options).then(response=>{
            if (!response.ok) {
                throw new Error('login error');
            }
            return response.json();
        })
        return await user;
    }
    async function doCart(cartId, userId) {
        const url = "http://133.186.144.236:8100/api/nhnmart/shopping-cart/" + cartId;
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId
            }
        };
        const item = await fetch(url, options).then(response =>{
            if (!response.ok) {
                throw new Error('api error');
            }
            return response.json();
        })
        return await item;
    }
});