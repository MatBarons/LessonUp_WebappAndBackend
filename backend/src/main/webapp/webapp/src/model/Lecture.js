import axios from "axios";

export async function getLecturesByDateAndSubject(subject,date){
    return await axios.get('http://192.168.1.11:8080/backend/api/course?path=getAllLecturesBySubjectAndStatusAndDate&subject='+subject+'&status=free&date='+date)
}


