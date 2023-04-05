'use strict';
const navi = new Navigator("btn-prev-month","btn-next-month","btn-current-month");
const store = apiStore();

console.log("year:" + navi.getYear());
console.log("month:" + navi.getMonth());

(function(){
    'use strict';

    window.addEventListener("DOMContentLoaded",(event)=>{
        document.getElementById("todo-nav-year").innerText = navi.getYear();
        document.getElementById("todo-nav-month").innerText = navi.getMonth();
        createTodoList();
    });
    
    async function createTodoList() {

        const daysInMonth = getDaysInMonth(navi.getYear(), navi.getMonth());
        const todoItemContainer = document.getElementById("todo-item-container");
        const template = document.getElementById("todo-item-template");
        //한번에 받아오는 별도의 메서드를 만들어 맵으로 받아온다.
        const map = await store.getTodoItemListAll(navi.getYear(),navi.getMonth());

        for (let i = 1; i <= daysInMonth; i++) {
            const todoItem = document.importNode(template.content, true);
            const todoItemDay = todoItem.querySelector(".todo-item-day");

            //날짜표시
            todoItemDay.innerText = "";
            const span1 = document.createElement("span");
            span1.innerText = String(i);
            todoItemDay.appendChild(span1);

            const todoDate = todoItem.querySelector("input[name=todoDate]");
            const zeroDate = navi.getYear() + "-" + navi.getMonth() + "-" + navi.convertToZeroMonthAndDay(i);
            todoDate.value = zeroDate;

            const form = todoItem.querySelector("form");
            form.addEventListener("submit", todoSubmit);

            const todoItemList = todoItem.querySelector(".todo-item-list");
            todoItemList.setAttribute("id", "todo-item-list-" + zeroDate);

            const btnRemoveAll = todoItem.querySelector(".btn-remove-all");
            btnRemoveAll.setAttribute("todoDate", zeroDate);
            btnRemoveAll.addEventListener("click", removeAllByTodoDate);

            todoItemContainer.appendChild(todoItem);

            await displayTodoItemListOfMap(zeroDate, map);
        }//end for
    }

    async function todoSubmit(event){
        event.preventDefault();
        const validateForm =(form)=>{
            if(form['todoDate'].value.trim()===0){
                alert("todoDate Empty!");
                return false;
            }else if(form['todoSubject'].value.trim()===0){
                alert("todo-subject is empty!");
                form['todoSubject'].focus();
                return false;
            }
            return true;
        };

        if(validateForm(event.target)){

            //save 데이터 저장
            console.log("save");
            const todoDate = event.target['todoDate'].value;
            const todoSubject = event.target['todoSubject'].value;
            try{
                await store.save(todoDate, todoSubject);
            }catch(e){
                alert(e);
            }finally{
                event.target['todoSubject'].value="";
                displayTodoItemList(todoDate);
            }
        }
    }

    async function removeAllByTodoDate(event){
        const todoDate  = event.target.getAttribute("todoDate");
        const answer =confirm("모두삭제 하시겠습니까?");
        
        if(answer){
            try{
                await store.deleteByTodoDate(todoDate);
            }catch(e){
                alert(e);
            }finally{
                clearTodoItemList(todoDate);
            }
        }
    }

    function getDaysInMonth(targetYear, targetMonth){
        return new Date(targetYear, parseInt(targetMonth), 0).getDate();
    }

    function clearTodoItemList(todoDate){
        const todoItemList = document.getElementById("todo-item-list-" + todoDate);
        while(todoItemList.firstChild){
            todoItemList.removeChild(todoItemList.firstChild)
        }
    }

    async function displayTodoItemList(todoDate){
        clearTodoItemList(todoDate);
        const todoItemList = document.getElementById("todo-item-list-" + todoDate);
        try{
            //TODO : await 추가
            const todoItems = await store.getTodoItemList(todoDate);
            for(let i=0; i<todoItems.length; i++){
                const li = document.createElement("li");
                li.innerText=todoItems[i].todoSubject;
                li.setAttribute("todoDate",todoDate);
                li.setAttribute("id", todoItems[i].id);
                li.addEventListener("click", removeTodoItem)
                todoItemList.appendChild(li);
            }
        }catch(e){
            alert(e);
        }
    }
    //기존의 코드는 살리고 따로 맵을 활용하여 출력하는 메서드
    function displayTodoItemListOfMap(todoDate,map){
        clearTodoItemList(todoDate);
        let todoDateList = map.get(todoDate)
        const todoItemList = document.getElementById("todo-item-list-" + todoDate);
        //클리어 이후 map에 존재하는 todo들만 반복문을 돌게함.
        if(todoDateList !== undefined){
            for(let i=0; i<todoDateList.length; i++){
                const li = document.createElement("li");
                li.innerText=todoDateList[i].todoSubject;
                li.setAttribute("todoDate",todoDate);
                li.setAttribute("id", todoDateList[i].id);
                li.addEventListener("click", removeTodoItem)
                todoItemList.appendChild(li);
            }
        }
    }

    async function removeTodoItem(event){
        const id = event.target.getAttribute("id");
        const todoDate = event.target.getAttribute("todoDate");
        const answer = confirm("삭제하시겠습니까?");

        if(answer){
            try{
                await store.delete(todoDate,id);
            }catch(e){
                alert(e);
            }finally{
                displayTodoItemList(todoDate);
            }
        }
    }

})();


