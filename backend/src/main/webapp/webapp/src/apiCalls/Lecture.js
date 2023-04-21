import axios from "axios";

export async function getLecturesByDateAndSubject(subject,date){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesBySubjectAndStatusAndDate&subject='+subject+'&status=free&date='+date)
}

export async function getLecturesByStudentAndStatus(student,status){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesByStudentAndStatus&student='+student+'&status='+status)
}

export async function getLecturesByStudentAndStatusAndDateAndSubject(student,status,date,subject){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesByStudentAndStatusAndDateAndSubject&student='+student+'&status='+status+'&date='+date+'&subject='+subject)
}

export async function changeStatus(status,date,time,professor,subject){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=changeStatus&status='+status+"&professor=" + professor + "&subject=" + subject + "&date=" +date + "&time=" +time)
}

export async function changeStatusAndStudent(status,student,date,time,professor,subject){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=changeStatusAndStudent&status='+status+"&professor=" + professor + "&subject=" + subject + "&date=" +date + "&time=" +time + "&student=" + student
    )
    //http://127.0.0.1:8080/backend/api/lecture?path=changeStatusAndStudent&status=booked&professor=lucia.calandra@mail.com&date=2023-04-20&time=14:00:00&subject=francese&student=martina.barone@mail.com
}