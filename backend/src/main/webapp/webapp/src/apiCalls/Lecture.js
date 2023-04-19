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
