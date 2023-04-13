import axios from "axios";

export async function getLecturesByDateAndSubject(subject,date){
    return await axios.get('http://localhost:8080/backend/api/lecture?path=getAllLecturesBySubjectAndStatusAndDate&subject='+subject+'&status=free&date='+date)
}


