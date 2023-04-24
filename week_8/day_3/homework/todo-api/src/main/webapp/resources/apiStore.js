//https://developer.mozilla.org/ko/docs/Web/API/Fetch_API/Using_Fetch

const apiStore = function(){

    const SERVER_URL="http://localhost:8080";
    const X_USER_ID = "marco";
    const DAILY_MAX_TODO_COUNT=8;
    const api = new Object();
    const headers ={
        'Content-Type' : 'application/json',
        'X-USER-ID' : X_USER_ID,
    }

    api.save= async function(todoDate, todoSubject){

        const count = await countByTodoDate(todoDate);
        if(count >=DAILY_MAX_TODO_COUNT){
            throw new Error("DAILY_MAX_TODO_COUNT:"+ DAILY_MAX_TODO_COUNT);
        }

        const data = {
            subject : todoSubject,
            eventAt : todoDate
        }
        const url = SERVER_URL + "/api/calendar/events";
        const options = {
            method : 'POST',
            headers:{
                'Content-Type' : 'application/json',
                'X-USER-ID' : X_USER_ID,
            },
            body : JSON.stringify(data)
        }

        const response = await fetch(url,options);

        if(!response.ok){
            console.log(response.error);
            throw new Error('status:' +  response.status);
        }

    }

    api.delete = async function(todoDate,id){
        const url = SERVER_URL + "/api/calendar/events/" + id;
        const options = {
            method : 'DELETE',
            headers: headers
        }
        const response = await fetch(url,options);
        if(!response.ok){
            console.log(response.error);
            throw new Error('status:' +  response.status);
        }
    }

    api.deleteByTodoDate= async function(todoDate){
        const url = SERVER_URL + "/api/calendar/events/daily/" + todoDate;
        const options = {
            method : 'DELETE',
            headers: headers
        }
        const response = await fetch(url,options);

        if(!response.ok){
            throw new Error('status:' +  response.status);
        }
    }
    api.getTodoItemList= async function(todoDate){

        const arr = todoDate.split("-");
        let year = arr[0];
        let month =arr[1];
        let day = arr[2];

        const url = SERVER_URL + "/api/calendar/events?year=" + year + "&month=" + month + "&day=" + day;

        const options = {
            method : 'GET',
            headers:headers
        }

        const response = await fetch(url,options);

        if(!response.ok){
            console.log(response.error);
            throw new Error('status:' +  response.status);
        }

        const todoList = await response.json();
        const items = [];
        if(todoList){
            for (const todo of todoList) {
                const item = {
                    id : todo.id,
                    todoDate : todo.eventAt,
                    todoSubject : todo.subject
                };
                items.push(item);
            }
        }
        return items;
    }
    //달까지만 요청하여 들어오는 데이터를 map으로 만들어서 리턴
    api.getTodoItemListAll= async function(year,month){
        const url = SERVER_URL + "/api/calendar/events?year=" + year + "&month=" + month;

        const options = {
            method : 'GET',
            headers:headers
        }

        const response = await fetch(url,options);

        if(!response.ok){
            console.log(response.error);
            throw new Error('status:' +  response.status);
        }

        const todoList = await response.json();
        console.log("todoList : ",todoList);

        const total = new Map();
        if(todoList){
            for(const todo of todoList){
                const item = {
                    id : todo.id,
                    todoDate : todo.eventAt,
                    todoSubject : todo.subject
                };
                let list = [];
                if (total.has(todo.eventAt)) {
                    list = total.get(todo.eventAt);
                } else {
                    list = new Array();

                }
                list.push(item);
                total.set(todo.eventAt,list);
            }
        }
        return total;
    }

    async function countByTodoDate(todoDate){
        const url = SERVER_URL + "/api/calendar/daily-register-count?date=" + todoDate;
        const options = {
            method : 'GET',
            headers:headers
        }
        const response = await fetch(url,options);

        // if(!response.ok){
        //     throw new Error(response.status);
        // }
        const countObj = await response.json();
        return parseInt(countObj.count);
    }

    return api;
}