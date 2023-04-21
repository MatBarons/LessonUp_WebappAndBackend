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
    addElementInCartList(value){
        if(this.isElementInCartList(value) !== true){
            this.cart_list.push(value)
        }
    },
    removeElementInCartList(value){
        let my_lecture;
        if(this.isElementInCartList(value) === true){
            my_lecture = this.cart_list.indexOf(value)
            this.cart_list.splice(my_lecture,1)
        }

    },
    isElementInCartList(value){
        return this.cart_list.includes(value,0)
    }

})