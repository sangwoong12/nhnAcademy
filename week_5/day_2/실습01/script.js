// alert("hello");
// console.log("hello");
//
// let name = 'global Name';
//
// {
//     let name = "marco";
//     console.log(name);
//     language = "javascript";
// }
//
// let user = {
//     name: 'sangwoong',
//     age: 30,
//     getName : function (){
//         return this.name;
//     }
// }
//
//
// console(user.name);

console.log(greenColor());
console.log(message);

function greenColor(){
    return "green";
}
var message = "hello";
//익명 함수는 호이스팅 영향을 받지 않는다.
var blueColor = function (){
    return "blue";
}
console.log(blueColor());

function Person(name, age){
    this.name = name;
    this.age = age;
}
const person1 = new Person("sangwoong",26);

const newPerson = {
    name : "sangwoong",
    age : 26
};

const newPerson2 = {};
for(key in newPerson){
    newPerson2[key] = newPerson[key];
}

const newPerson3 = Object.assign({},newPerson2);

