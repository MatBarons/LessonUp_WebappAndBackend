import axios from "axios";
export async function getActiveCourses() {
    return await axios.get('http://localhost:8080/backend/api/course?path=getAllCoursesByActivity&isActive=true')
}

export async function getAllCourses(){
    return await axios.get('http://localhost:8080/backend/api/course?path=getAllCourses')
}

export async function insertCourse(name,activity){
    return await axios.post('http://localhost:8080/backend/api/course?path=insertCourse&name='+ name + "&isActive" + activity)
}

export async function toggleCourse(name,activity){
    return await axios.post('http://localhost:8080/backend/api/course?path=toggleCourse&name='+name+"&isActive" + activity)
}