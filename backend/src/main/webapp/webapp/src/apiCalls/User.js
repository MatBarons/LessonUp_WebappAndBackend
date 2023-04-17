import axios from "axios";

export async function login(email,password){
    return await axios.get('http://localhost:8080/backend/api/user?path=logMeIn&email='+email+'&password='+password)
}