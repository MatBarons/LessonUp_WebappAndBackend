import axios from "axios";
import {store} from "@/apiCalls/User";

export async function getLecturesByDateAndSubject(subject,date){
    return await axios.get(
        'http://localhost:8080/backend/api/lecture?path=getAllLecturesBySubjectAndStatusAndDate&subject='+subject+'&status=free&date='+date,{
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}

export async function getLecturesByStudentAndStatus(student,status){
    return await axios.get(
        'http://localhost:8080/backend/api/lecture?path=getAllLecturesByStudentAndStatus&student='+student+'&status='+status,{
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}

export async function getAllLecturesByStatus(status){
    return await axios.get(
        'http://localhost:8080/backend/api/lecture?path=getAllLecturesByStatus&status='+status,{
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}

export async function changeStatus(lecture){
    return await axios.put(
        'http://localhost:8080/backend/api/lecture?path=changeStatus',lecture,
        {
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}

export async function changeStatusAndStudent(lecture){
    return await axios.put(
        'http://localhost:8080/backend/api/lecture?path=changeStatusAndStudent',lecture,
        {
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}

export async function insertLecture(lecture){
    return await axios.post(
        'http://localhost:8080/backend/api/lecture?path=insertLecture',lecture,
        {
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}

export async function deleteLecture(date,time,professor,subject){
    return await axios.delete(
        'http://localhost:8080/backend/api/lecture?path=deleteLecture&date='+date+"&time="+time+"&professor="+professor+"&subject="+subject,
        {
            headers: {
                'Authorization': store.data.token
            }
        }
    )
}