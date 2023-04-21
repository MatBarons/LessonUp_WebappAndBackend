import axios from "axios";
import {reactive} from 'vue';

export async function logMeIn(email,password){
    return await axios.get('http://localhost:8080/backend/api/user?path=logMeIn&email='+email+'&password='+password)
}


export const store = reactive({
    email: null,
    name: null,
    cart_list: new Set(),
    setEmail(value){
        this.email = value
    },
    setName(value){
        this.name = value
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