import axios from "axios";
export async function getActiveCourses() {
    return await axios.get('http://localhost:8080/backend/api/course?path=getAllCoursesByActivity&isActive=true')
}

export async function getAllCourses(){
    return await axios.get('http://localhost:8080/backend/api/course?path=getAllCourses')
}