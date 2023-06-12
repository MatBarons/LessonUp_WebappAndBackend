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

export async function deleteTeaching(professor,subject){
    return await axios.delete(
        'http://localhost:8080/backend/api/teaching?path=deleteTeaching&email='+professor + '&subject=' +subject,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}
export async function getSubjectsByProfessor(professor){
    return await axios.get(
        'http://localhost:8080/backend/api/teaching?path=getAllSubjectForAProfessor&email='+professor,{
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

export async function getAllProfessorRemainingForASubject(subject){
    return await axios.get(
        "http://localhost:8080/backend/api/teaching?path=getAllProfessorWithoutASubject&subject=" + subject,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}

export async function getAllSubjectRemainingForAProfessor(professor){
    return await axios.get(
        'http://localhost:8080/backend/api/teaching?path=getAllSubjectWithoutAProfessor&email='+professor,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}


export async function getAllSubject(){
    return await axios.get(
        'http://localhost:8080/backend/api/teaching?path=getAllCourses',{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}

export async function getAllProfessor(){
    return await axios.get(
        'http://localhost:8080/backend/api/teaching?path=getAllProfessors',{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}