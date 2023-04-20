import axios from "axios";
import {reactive} from 'vue';

export async function logMeIn(email,password){
    return await axios.get('http://localhost:8080/backend/api/user?path=logMeIn&email='+email+'&password='+password)
}


export const store = reactive({
    email: null,
    cart_list: [],
    setEmail(value){
        this.email = value
    },
    setElementInCartList(value){
      this.cart_list.push(value)
    }
})