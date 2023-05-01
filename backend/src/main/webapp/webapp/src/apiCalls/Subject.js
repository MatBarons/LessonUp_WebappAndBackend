import axios from "axios";
import {store} from "@/apiCalls/User";
export async function getActiveCourses() {
    return await axios.get(
        'http://localhost:8080/backend/api/course?path=getAllCoursesByActivity&isActive=true',{
            headers:{
                'Authorization' : store.data.token
            }
        }
    )
}

export async function getAllCourses(){
    return await axios.get(
        'http://localhost:8080/backend/api/course?path=getAllCourses',{
            headers:{
                'Authorization' : store.data.token
            }
        }
    )
}

export async function insertCourse(course){
    return await axios.post(
        'http://localhost:8080/backend/api/course?path=insertCourse&session='+store.email,course,{
            headers:{
                'Content-Type': 'application/json',
                'Authorization' : store.data.token
            }
        }
    )
}

export async function toggleCourse(course){
    return await axios.put(
        'http://localhost:8080/backend/api/course', course,
        {
            headers:{
                'Content-Type': 'application/json',
                'Authorization' : store.data.token
            }
        }
    )
}