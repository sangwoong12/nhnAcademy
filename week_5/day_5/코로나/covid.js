'use strict';
const navi = new Navigator("btn-prev-month","btn-next-month","btn-current-month");
const api = covidApi;
console.log("year:" + navi.getYear());
console.log("month:" + navi.getMonth());
console.log("day:" + navi.getDay());

(function(){
    'use strict';

    window.addEventListener("DOMContentLoaded",(event) => {
        const statusDt = navi.getStatusDt();
        createTotalInfectionStatus(statusDt);
        createTodoList(navi.getYear()+"-"+navi.getMonth()+"-"+navi.getDay());
    });

    //TODO 1: 칠판 데이터 넣기
    async function createTotalInfectionStatus(statusDt) {
        try{
            const list = await api.getTotalInfectionStatusList(statusDt);
            document.getElementById("acc-g-pnt-cnt").innerText = list.accGPntCnt;
            document.getElementById("acc-exam-cnt").innerText = list.accExamCnt;
            document.getElementById("acc-h-pnt-cnt").innerText = list.accHPntCnt;

            const list2 = await api.getDomesticOccurrenceStatusList();
            //rate_deaths, cnt_confirmations, cnt_deaths, cnt_hospitalizations, cnt_severe_symptoms
            document.getElementById("rate-deaths").innerText = list2.rateDeaths;
            document.getElementById("cnt-confirmations").innerText = list2.cntConfirmations;
            document.getElementById("cnt-deaths").innerText = list2.cntDeaths;
            document.getElementById("cnt-hospitalizations").innerText = list2.cntHospitalizations;
            document.getElementById("cnt-severe-symptoms").innerText = list2.cntSevereSymptoms;
        }catch (e){
            alert(e);
        }
    }
    //TODO 2: 날짜에 따른 데이터 만들기
    async function createTodoList(statusDt) {
        try{
            const mapAndMaxValue = await api.getGenderAndAgeList(statusDt);

            const maxValue = mapAndMaxValue[0];
            const map = mapAndMaxValue[1];
            const table = document.getElementById("covid-table");
            const tbody = table.getElementsByTagName("tbody")[0];
            const keys = ['0-9','10-19','20-29','30-39','40-49','50-59','60-69','70-79','80 이상','남성','여성'];

            for (const key of keys) {
                const item = map.get(key);
                const tr = document.createElement("tr");

                const td1 = document.createElement("td")
                td1.innerText = key;

                const td2 = document.createElement("td")
                td2.innerText = item.criticalRate;
                paintYellow(td2,item.criticalRate,maxValue.criticalRate);
                const td3 = document.createElement("td")
                td3.innerText = item.death;
                paintYellow(td3,item.death,maxValue.death);
                const td4 = document.createElement("td")
                td4.innerText = item.confCase;
                paintYellow(td4,item.confCase,maxValue.confCase);
                const td5 = document.createElement("td")
                td5.innerText = item.deathRate;
                paintYellow(td5,item.deathRate,maxValue.deathRate);
                const td6 = document.createElement("td")
                td6.innerText = item.confCaseRate;
                paintYellow(td6,item.confCaseRate,maxValue.confCaseRate);

                tr.append(td1, td2, td3, td4, td5, td6);
                tbody.append(tr);
            }
            table.append(tbody);
        }catch (e){
            alert(e);
        }
    }
    function paintYellow(td,value,maxValue){
        if(value === maxValue){
            td.setAttribute("style","background-color : yellow");
        }
    }
})();


