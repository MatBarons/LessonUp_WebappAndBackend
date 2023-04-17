import axios from "axios";

export async function getLecturesByDateAndSubject(subject,date){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesBySubjectAndStatusAndDate&subject='+subject+'&status=free&date='+date)
}

export async function getLecturesByStudentAndStatus(student,status){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesByStudentAndStatus&student='+student+'&status='+status)
}


