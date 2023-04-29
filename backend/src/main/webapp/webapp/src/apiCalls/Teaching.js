import axios from "axios";

export async function insertTeaching(professor,subject){
    return await axios.post('http://localhost:8080/backend/api/teaching?path=insertTeaching&email='+professor+'&subject='+subject)
}

export async function deleteTeaching(professor,subject){
    return await axios.post('http://localhost:8080/backend/api/teaching?path=deleteTeaching&email='+professor+'&subject='+subject)
}

export async function getProfessorsBySubject(subject){
    return await axios.get('http://localhost:8080/backend/api/teaching?path=getAllProfessorForASubject&subject=' + subject)
}