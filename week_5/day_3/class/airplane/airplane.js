
const flightScheduleApi = (function () {
    'use strict';
    const SERVICE_KEY = "EMNEszo9cmQAqJgEa6QHo6d3gZ3mDMY6X0GYG4w2oGrWcWh9IjM68dR989WztGAXliIT0hK9A5R%2BIYNRSWCnGw%3D%3D";

    //운행스케줄 api
    const api = new Object();

    async function getAirlineList() {
        let url = 'http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getAirmanList'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
        url += queryParams;
        //TODO#1 항공사 리스트 구하기

        const options = {
            method: 'GET'
        };

        const airlineList = fetch(url, options)
            .then(response => response.json())
            .then((json) => json.response.body.items.item);
        console.log("airlineList : 확인");
        console.log(airlineList);
        return await airlineList;
    }

    api.getAirportList = async function () {
        let url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList";
        let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
        url += queryParams;
        //TODO#2 공항리스트 구하기

        const options = {
            method: 'GET'
        };

        const airportList = fetch(url, options)
            .then(response => response.json())
            .then((json) => json.response.body.items.item);
        console.log("airportList : 확인");
        console.log(airportList);
        return await airportList;
    }

    /*
        * @param {*} depAirportId  출발공항 아이디
        * @param {*} arrAirportId  도착공항 아이디
        * @param {*} depPlandTime  출발시간 : 20230321
        * @param {*} airlineId     항공사 아이디
    */

    //getFlightSchedule("NAARKJJ","NAARKPC","20201202","AAR");
    async function getFlightSchedule(depAirportId, arrAirportId, depPlandTime, airlineId) {
        let url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList";
        var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
        queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
        queryParams += '&' + encodeURIComponent('depAirportId') + '=' + encodeURIComponent(depAirportId); /**/
        queryParams += '&' + encodeURIComponent('arrAirportId') + '=' + encodeURIComponent(arrAirportId); /**/
        queryParams += '&' + encodeURIComponent('depPlandTime') + '=' + encodeURIComponent(depPlandTime); /**/
        queryParams += '&' + encodeURIComponent('airlineId') + '=' + encodeURIComponent(airlineId); /**/
        url = url + queryParams;

        //TODO#3 항공운항정보 조회
        const options = {
            method: 'GET'
        };
        const list = [];
        const flightSchedule = await fetch(url, options);
        if (flightSchedule.ok) {


            console.log("data : 확인");
            console.log(data);
            return data;
        }
        return list;
    }

    api.search = async function (depAirportId, arrAirportId, depPlandTime) {
        const airlineList = await getAirlineList();
        //조회로직 실행
        depPlandTime = depPlandTime.replaceAll("-", "");
        const promiseList = [];
        for (const airline of airlineList) {

            const flightSchedule = await getFlightSchedule(depAirportId, arrAirportId, depPlandTime, airline.airlineId);
            promiseList.push(flightSchedule);
            //TODO#4 항공사별 운항정보를 얻어서 하나의 리스트로 리턴
        }
        console.log(promiseList);
        const value = Promise.all(promiseList)
            .then(list =>{
                const a =[];
                const filterList = list.filter(function (x){
                    return x!== undefined;
                })
                for (const element of filterList) {
                    for (const e of element) {
                        a.push(e);
                    }
                }
                return a;
            })
        console.log(value);
        return value;
    }

    return api;
})();

window.addEventListener("DOMContentLoaded", async function () {
    'use strict'

    const departureId = document.getElementById("departureId");
    const arrivalId = document.getElementById("arrivalId");

    //비행날짜
    const plandDate = document.getElementById("plandDate");
    //TODO#5 기본 날짜를 오늘로 설정
    plandDate.value = new Date().toISOString().substring(0, 10);
    //FIXME #6 공항리스트 호출.
    const api = flightScheduleApi;

    const airportList = await api.getAirportList();

    // <select id="arrivalId" name="arrivalId" required>
    //     <option value="" >공항선택(도착)</option>
    // </select>
    const selectArrivalId = document.getElementById('arrivalId');
    const selectDepartureId = document.getElementById('departureId');

    for (const item of airportList) {
        let option = document.createElement("option");
        option.innerText = item.airportNm;
        option.value = item.airportId;
        selectArrivalId.append(option);

        let option2 = document.createElement("option");
        option2.innerText = item.airportNm;
        option2.value = item.airportId;
        selectDepartureId.append(option2);
    }

    const validateForm = function (form) {
        const departureId = form["departureId"];
        const arrivalId = form["arrivalId"];
        const departureIdValue = departureId.options[departureId.selectedIndex].value;
        const arrivalIdValue = arrivalId.options[arrivalId.selectedIndex].value;

        //TODO#8 form validation
        // departureId, arrivalId 선택여부 체크
        // 출발(공항) == 도착(공항) retun false
        return !(departureIdValue === arrivalIdValue);
    };

    const findForm = document.getElementById("find-form");

    findForm.addEventListener("submit", async function (event) {
        event.preventDefault();
        if (validateForm(event.target) == false) {
            return;
        }
        console.log("validate 통과");

        //schedule 조회
        try {
            const depPlandTime = document.getElementById("plandDate").value;
            const items = await flightScheduleApi.search(departureId.value, arrivalId.value, depPlandTime);
            console.log("searchResult 호출");
            searchResult(items);

        } catch (e) {
            alert(e);
        }
    });

});

function searchResult(items) {
    const scheduleTbl = document.getElementById("schedule-tbl");
    const tbody = scheduleTbl.getElementsByTagName("tbody")[0];
    while(tbody.firstChild){
        //TODO#9 tbody에 담겨있는 모든 <tr> 삭제
        tbody.firstChild.remove();
    }
    console.log("searchResult 입장");
    console.log(items.length);
    for (let i = 0; i < items.length; i++) {
        const tr = document.createElement("tr");
        tr.setAttribute("id","tr");

        //TODO#10 tbdoy에 <tr><td>...</td> ... </tr> 만들어서 넣기
        //숫자 서식 관려해서는 다음을 참고하기
        //https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat
        //날짜 변환에 대해서는 convertDate(str) 함수를 이용해주세요
        const td0 = document.createElement("td");
        td0.innerText = items[i].vihicleId;
        const td1 = document.createElement("td");
        td1.innerText = items[i].airlineNm;
        const td2 = document.createElement("td");
        td2.innerText = convertDate(items[i].arrPlandTime);
        const td3 = document.createElement("td");
        td3.innerText = convertDate(items[i].depPlandTime);
        const td4 = document.createElement("td");
        if(items[i].economyCharge === undefined){
            td4.innerText = '0';
        }else{
            td4.innerText = items[i].economyCharge;
        }
        const td5 = document.createElement("td");
        if(items[i].economyCharge === undefined){
            td5.innerText = '0';
        }else{
            td5.innerText = items[i].prestigeCharge;
        }
        const td6 = document.createElement("td");
        td6.innerText = items[i].arrAirportNm;
        const td7 = document.createElement("td");
        td7.innerText = items[i].depAirportNm;
        tr.append(td0, td1, td2, td3, td4, td5, td6, td7);
        tbody.append(tr);
    }
}

function convertDate(str) {
    str = str.toString();
    //202303221125 -> 2023 03 22 11:25
    return str.substring(0, 4)
        + "-" + str.substring(4, 6)
        + "-" + str.substring(6, 8)
        + " " + str.substring(8, 10)
        + ":" + str.substring(10, 12);
}