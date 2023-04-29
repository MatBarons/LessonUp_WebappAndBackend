import axios from "axios";

export async function getLecturesByDateAndSubject(subject,date){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesBySubjectAndStatusAndDate&subject='+subject+'&status=free&date='+date)
}

export async function getLecturesByStudentAndStatus(student,status){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesByStudentAndStatus&student='+student+'&status='+status)
}

export async function getAllLecturesByStatus(status){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesByStatus&status='+status)
}

export async function changeStatus(status,date,time,professor,subject){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=changeStatus&status='+status+"&professor=" + professor + "&subject=" + subject + "&date=" +date + "&time=" +time)
}

export async function changeStatusAndStudent(status,student,date,time,professor,subject){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=changeStatusAndStudent&status='+status+"&professor=" + professor + "&subject=" + subject + "&date=" +date + "&time=" +time + "&student=" + student
    )
}

export async function insertLecture(date,time,professor,subject,status,student){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=insertLecture&date='+date+"&time="+time+"&professor="+professor+"&subject="+subject+"&status="+status+"&student="+student
    )
}

export async function deleteLecture(date,time,professor,subject){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=deleteLecture&date='+date+"&time="+time+"&professor="+professor+"&subject="+subject
    )
}