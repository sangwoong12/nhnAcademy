//https://developer.mozilla.org/ko/docs/Web/JavaScript/Closures
//https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Map
const memoryStore = function(){
    'use strict';
    const todoMap = new Map();
    const DAILY_MAX_TODO_COUNT=8;
    const api = new Object();
    console.log("memoryStore 생성");

    /*TODO#1저장 구현
        map을 이용해서 구현합니다.
        key = 2023-03-01
        value = {item,item,item} array 입니다.
        item 객체의 구조는 다음과 같습니다.
        {
            'id' : '36b8f84d-df4e-4d49-b662-bcde71a8764f',
            'todoDate' : '2023-03-01',
            'todoSubject' : 'js 하루종일 공부하기!'
        }
    */
    api.save = function( todoDate, todoSubject){
        /*TODO#1-1
            id는 uuid를 사용합니다. 아래를 참고해서 만들어 주세요
            https://developer.mozilla.org/en-US/docs/Web/API/Crypto/randomUUID
        */
        const id = self.crypto.randomUUID();
        /*TODO#1-2 해당 날짜에 >=DAILY_MAX_TODO_COUNT 이면 적절한 Error 발생 시키기.
         countByTodoDate(todoDate); 사용해서 등록 count를 구합니다.
        */
        if(countByTodoDate(todoDate) >= DAILY_MAX_TODO_COUNT){
            throw new Error("하루에 최대${DAILY_MAX_TODO_COUNT} 개 추가할 수 있습니다.");
        }
        /* TODO#1-3 map  key인 todoDate로 조회
            1. value  존재하지 않는다면 : array를 생성 후 item을 push
            2. value가 존재하면 array에 바로 push ..
        */
        const item = {
            id : id,
            todoDate : todoDate,
            todoSubject : todoSubject
        }

        if(todoMap.has(todoDate)){
            const todoItems = todoMap.get(todoDate);
            todoItems.push(item);
        } else {
            const todoItems = new Array();
            todoItems.push(item);
            todoMap.set(todoDate, todoItems);
        }
        //map에 잘 담겼는지 출력(디버깅 용도);
        printMap();
    }

    /*TODO#2- 삭제구현하기*/
    api.delete=function(todoDate,id){
        /*
            TODO#2-1 todoMap에  todoDate가 존재하는 확인 후  존재하면
            해당 배열의 몇번째 index인지 찾습니다.
            해당 인덱스에 해당하는 요소를 삭제합니다.
            아래 두 함수를 참고해서 구현합니다.
            //https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Array/findIndex
            //findIndex 인덱스를 찾아 주는 함수
            //https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Array/splice
            //splice(인덱스 선택,인덱스 ~ 인덱스 + value 까지 삭제 0이면 삭제 x,넣을 값,넣을 값...)
        */
        if(todoMap.has(todoDate)){
            let list = todoMap.get(todoDate);
            let findIndex = list.findIndex((value) => value.id === id);
            /*
                TODO#2-2 index가 존재하지 않는다면 적절한 Error를 발생해주세요.
                ex) `${id} not found` ..
            */
            if (findIndex > -1) {
                list.splice(findIndex,1);
            } else {
                throw new Error(id+" not found");
            }
        }
    }

    //TODO#3 해당 날짜에 존재하는 모든 todo 삭제하기
    api.deleteByTodoDate=function(todoDate){
        if(todoMap.has(todoDate)){
            todoMap.delete(todoDate);
        }
        // todoMap.get(todoDate).splice(0,todoMap.size);
    }

    //TODO#4 해당 날짜에 존재하는 모든 todo 조회, 존재하지 않는다면 빈 배열을 리턴합니다.
    api.getTodoItemList=function(todoDate){
        return todoMap.has(todoDate) ? todoMap.get(todoDate) : [];
    }

    //TODO#5 해당 날짜의 모든 todo item count, 참고로 countByTodoDate 함수는 api 내부에서만 접근가능 합니다.
    const countByTodoDate=function(todoDate){
        return todoMap.has(todoDate) ? todoMap.get(todoDate).length : 0;
    }

    //디버깅 용도 map에 있는 모든 entries 출력하기... 적절히 활용해주세요.
    function printMap(){
        //https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/push
        for (const [key, value] of todoMap.entries()) {
            console.log(`${key} = ${value}`);
        }
    }

    return api;
};