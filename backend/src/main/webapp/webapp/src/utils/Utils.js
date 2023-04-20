import moment from "moment";

export function capitalize(string){
    return string.substring(0,1).toUpperCase() + string.substring(1)
}


export function format_date(value){
    if (value) {
        return moment(String(value)).format('yyyy-MM-DD')
    }
}

export function parse_string_to_date(value){
    let year,month,day;
    day = value.substring(0,2)
    month = value.substring(3,5)
    year = value.substring(6,10)
    return year + "-" + month + "-" + day
}

export function parse_string_to_time(value){
    let hour,minute;
    hour = value.toString().substring(0,2)
    minute = value.toString().substring(3,5)
    return hour + ":" + minute + ":00"
}