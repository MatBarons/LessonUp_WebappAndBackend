import axios from "axios";
import {reactive} from 'vue';

export async function logMeIn(email,password){
    return await axios.get('http://localhost:8080/backend/api/user?path=logMeIn&email='+email+'&password='+password)
}

export async function changePassword(email,oldPassword,newPassword){
    return await axios.put(
        'http://localhost:8080/backend/api/user?path=changePassword&email='+ email + "&oldPassword=" + oldPassword + "&newPassword=" + newPassword,{},{
            headers: {
                'Authorization' : store.data.token
            }
        }
    )
}

export async function getAllUsersByRole(role){
    return await axios.get(
        'http://localhost:8080/backend/api/user?path=getAllUsersByRole&role=' + role,{
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}


export async function insertUser(user){
    return await axios.post(
        'http://localhost:8080/backend/api/user?path=insertUser', user,
        {
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}

export async function toggleUser(user){
    return await axios.put(
        'http://localhost:8080/backend/api/user?path=toggleUser', user,
        {
            headers:{
                'Authorization': store.data.token
            }
        }
    )
}

export const store = reactive({
    email: null,
    data: {
        name: "",
        surname: "",
        role: "",
        token: ""
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