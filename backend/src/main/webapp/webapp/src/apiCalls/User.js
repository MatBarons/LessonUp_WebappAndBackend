import axios from "axios";
import {reactive} from 'vue';

export async function logMeIn(email,password){
    return await axios.get('http://localhost:8080/backend/api/user?path=logMeIn&email='+email+'&password='+password)
}

export async function changePassword(email,oldPassword,newPassword){
    return await axios.post('http://localhost:8080/backend/api/user?path=changePassword&email='+ email + "&oldPassword=" + oldPassword + "&newPassword=" + newPassword)
}

export async function getAllUsersByRole(role){
    return await axios.get('http://localhost:8080/backend/api/user?path=getAllUsersByRole&role=' + role)
}


export async function insertUser(name,surname,email,password,role,activity){
    console.log("user: "+name + " " + surname + " " + email + " " + password + " " + role + " " + activity)
    return await axios.post('http://localhost:8080/backend/api/user?path=insertUser&name='+name+"&surname="+surname+"&email="+email+"&password="+password+"&role="+role+"&isActive="+activity)
}

export async function toggleUser(email,activity){
    return await axios.post('http://localhost:8080/backend/api/user?path=toggleUser&email='+email+"&isActive="+activity)
}

export const store = reactive({
    email: null,
    data: {
        name: "",
        surname: "",
        role: "",
    },
    cart_list: new Set(),
    setEmail(value){
        this.email = value
    },
    setData(value){
        this.data = value
    },
    addElementInCartList(value){
        this.cart_list.add(value)
    },
    removeElementInCartList(value){
        this.cart_list.delete(value)
    },
    isElementInCartList(value){
        return this.cart_list.has(value)
    }

})