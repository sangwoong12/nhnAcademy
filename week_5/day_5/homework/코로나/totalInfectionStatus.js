const covidApi = (function (){
    'use strict';

    const SERVICE_KEY =  "NieXPvwV%2BPT8sI5GwKxxD0JID1S0i60Mp6ykVijEe%2FuHYn5hb%2FXDVHezk0lc9X%2FNp3qLOZKm4Icbnjyydyf%2BVw%3D%3D";
    const api = new Object();
    //반복문을 만들어서 누적 사망자 , 누적 확진자수를 구하는걸 만들었지만 확인해보니
    //pPntCnt = 사망자수 (X) 누적 사망자 수 (O)
    //accExamCnt = 누적 검사수 (O)
    //hPntCnt = 확진자수 (X) 누적 확진자수 (O)
    api.getTotalInfectionStatusList = async function (statusDt){
        let url = 'http://apis.data.go.kr/1352000/ODMS_COVID_02/callCovid02Api'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '='+SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('500'); /**/
        queryParams += '&' + encodeURIComponent('apiType') + '=' + encodeURIComponent('JSON'); /**/
        queryParams += '&' + encodeURIComponent('status_dt') + '=' + encodeURIComponent(statusDt); /**/

        url = url + queryParams;

        const options = {
            method: 'GET'
        };

        const response = await fetch(url, options).then(response =>{
            if(response.ok){
                return response.json();
            }
            return new Error("보건복지부_코로나19 감염현황 총괄 통계 api 에 오류가 발생하였습니다.");
        });
        // response : items, numOfRows, pageNo, resultCode, resultMsg, totalCount 의 정보를 가지고 있다.
        //이중 items 의 gPntCnt, hPntCnt, accExamCnt 만 필요하다.
        console.log("총괄",response);

        const item = {
            accGPntCnt : response.items[0].gPntCnt,
            accHPntCnt : response.items[0].hPntCnt,
            accExamCnt : response.items[0].accExamCnt
        }
        return item;
    }
    api.getDomesticOccurrenceStatusList = async function (){
        let url = 'http://apis.data.go.kr/1790387/covid19CurrentStatusKorea/covid19CurrentStatusKoreaJason'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '='+SERVICE_KEY; /*Service Key*/

        url = url + queryParams;

        const options = {
            method: 'GET'
        };

        const response = await fetch(url, options).then(response =>{
            if(response.ok){
                return response.json();
            }
            return new Error("보건복지부_코로나19 국내발생현황 api 에 오류가 발생하였습니다.");
        });
        // console.log(response);
        //이중 items 의 rate_deaths, cnt_confirmations, cnt_deaths, cnt_hospitalizations, cnt_severe_symptoms
        const item = {
            rateDeaths : response.response.result[0].rate_deaths,
            cntConfirmations : response.response.result[0].cnt_confirmations,
            cntDeaths : response.response.result[0].cnt_deaths,
            cntHospitalizations : response.response.result[0].cnt_hospitalizations,
            cntSevereSymptoms : response.response.result[0].cnt_severe_symptoms
        }
        return item;
    }
    api.getGenderAndAgeList = async function (statusDt){
        let url = 'http://apis.data.go.kr/1352000/ODMS_COVID_05/callCovid05Api'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '='+SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('500'); /**/
        queryParams += '&' + encodeURIComponent('apiType') + '=' + encodeURIComponent('JSON'); /**/
        queryParams += '&' + encodeURIComponent('create_dt') + '=' + encodeURIComponent(statusDt); /**/

        url = url + queryParams;

        const options = {
            method: 'GET'
        };
        const map = new Map();
        const response = await fetch(url, options).then(response =>{
            if(response.ok){
                return response.json();
            }
            return new Error("보건복지부_코로나19 확진자 성별 연령별 api 에 오류가 발생하였습니다.");
        });
        console.log(response);

        const maxValue = {
            criticalRate : 0,
            death : 0,
            confCase : 0,
            deathRate : 0,
            confCaseRate : 0
        }

        for (const date of response.items) {
            //최대값 찾기
            maxValue.criticalRate = maxValue.criticalRate < date.criticalRate ? date.criticalRate : maxValue.criticalRate;
            maxValue.death = maxValue.death < date.death ? date.death : maxValue.death;
            maxValue.confCase = maxValue.confCase < date.confCase ? date.confCase : maxValue.confCase;
            maxValue.deathRate = maxValue.deathRate < date.deathRate ? date.deathRate : maxValue.deathRate;
            maxValue.confCaseRate = maxValue.confCaseRate < date.confCaseRate ? date.confCaseRate : maxValue.confCaseRate;

            //이중 items 의 criticalRate, death, confCase, deathRate, confCaseRate
            const item = {
                criticalRate : date.criticalRate,
                death : date.death,
                confCase : date.confCase,
                deathRate : date.deathRate,
                confCaseRate : date.confCaseRate
            }
            //date 가 원하는 순서대로 들어오지않아 map 으로 하여 쉽게 사용할 수 있도록 처리
            map.set(date.gubun,item);
        }
        return [maxValue, map];
    }
    return api;
})();
