import axios from "axios";
import {store} from "@/apiCalls/User";

export async function insertTeaching(teaching){
    return await axios.post(
        'http://localhost:8080/backend/api/teaching?path=insertTeaching',teaching,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}

export async function deleteTeaching(teaching){
    return await axios.delete(
        'http://localhost:8080/backend/api/teaching?path=deleteTeaching&email='+teaching.email + '&subject=' +teaching.course,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}

export async function getProfessorsBySubject(subject){
    return await axios.get(
        'http://localhost:8080/backend/api/teaching?path=getAllProfessorForASubject&subject=' + subject,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}