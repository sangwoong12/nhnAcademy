function Navigator(uiBtnPrevMonthId, uiBtnNextMonthId, uiBtnCurrentMonthId){
    //TODO#0 strict mode 선언하기
    'use strict';
    let year = null;
    let month = null;
    let day = null;

    const searchParam = new URLSearchParams(document.location.search);
    year = searchParam.get('year');
    month = searchParam.get('month');
    day = searchParam.get('day');

    const today = new Date();

    if(year == null){
        year = today.getFullYear();
    }

    if(month == null){
        month = _convertToZeroMonthAndDay(today.getMonth() + 1);
    }

    if(day == null){
        day = _convertToZeroMonthAndDay(today.getDate());
    }
    if((new Date(year,month-1,day).getTime() - new Date().getTime()) > 0){
        alert("검색할 수 없는 날짜 입니다. 오늘 날짜로 다시 검색합니다.");
        let origin = document.location.origin;
        let pathName = document.location.pathname;

        location.assign(origin+pathName+"?year="+today.getFullYear()+"&month="+_convertToZeroMonthAndDay(today.getMonth()+1)+"&day="+_convertToZeroMonthAndDay(today.getDate()));
    }

    console.log(year+":"+month+":"+day);
    window.addEventListener("DOMContentLoaded",function(){
        const btnPrevMonth = document.getElementById(uiBtnPrevMonthId);
        const btnNextMonth = document.getElementById(uiBtnNextMonthId);
        const btnCurrentMonth = document.getElementById(uiBtnCurrentMonthId);
        if(btnPrevMonth == null){
            throw new Error("이전 button 가 없습니다.");
        }
        if(btnNextMonth == null){
            throw new Error("다음 button 가 없습니다.");

        }
        if(btnCurrentMonth == null){
            throw new Error("오늘 button 가 없습니다.");
        }

        btnPrevMonth.addEventListener("click",function(){
            let targetYear;
            let targetMonth;
            let targetDay;
            if (parseInt(day) === 1) {
                if (parseInt(month) === 1) {
                    targetYear= parseInt(year) - 1;
                    targetMonth="12";
                    targetDay="31";
                } else {
                    targetYear= year;
                    targetMonth = _convertToZeroMonthAndDay(parseInt(month) - 1);
                    targetDay = getDaysInMonth(year,parseInt(month) - 1);
                }
            } else {
                targetYear= year;
                targetMonth = month;
                targetDay = _convertToZeroMonthAndDay(parseInt(day) - 1);
            }
            _navigate(targetYear,targetMonth,targetDay);
        });
        //다음
        btnNextMonth.addEventListener("click",function(){
            let targetYear;
            let targetMonth;
            let targetDay;
            if (parseInt(day) === getDaysInMonth(year,month)) {
                if (parseInt(month) === 12) {
                    targetYear= parseInt(year) + 1;
                    targetMonth="01";
                    targetDay="01";
                } else {
                    targetYear= year;
                    targetMonth = _convertToZeroMonthAndDay(parseInt(month) + 1);
                    targetDay = "01";
                }
            } else{
                targetYear= year;
                targetMonth = month;
                targetDay = _convertToZeroMonthAndDay(parseInt(day) + 1);
            }
            _navigate(targetYear,targetMonth,targetDay);
        });
        //오늘
        btnCurrentMonth.addEventListener("click",function(){
            const today = new Date();
            let targetYear= today.getFullYear();
            let targetMonth= _convertToZeroMonthAndDay(today.getMonth()+1);
            let targetDay = _convertToZeroMonthAndDay(today.getDate());

            _navigate(targetYear,targetMonth,targetDay);
        });
    });

    function _navigate(targetYear,targetMonth,targetDay){
        let origin = document.location.origin;
        let pathName = document.location.pathname;
        location.assign(origin+pathName+"?year="+targetYear+"&month="+targetMonth+"&day="+targetDay);
    }
    function _convertToZeroMonthAndDay(d){
        if(d >= 10){
            return d;
        }
        return "0"+String(d);
    }
    function getDaysInMonth(targetYear, targetMonth){
        return new Date(targetYear, parseInt(targetMonth), 0).getDate();
    }

    return {
        getYear : function(){
            return year;
        },
        getMonth : function(){
            return month;
        },
        getDay : function (){
            return day;
        },
        getStatusDt : function (){
            return year+month+day;
        }
    }

}