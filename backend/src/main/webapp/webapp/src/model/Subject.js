
class Subject {
    name;
    isActive;
    constructor(name,isActive) {
        this.name=name;
        this.isActive=isActive;
    }

    async getActiveSubjects(){
        var sub = new Subject();
        axios.get('http://192.168.1.11:8080/backend/api/lecture?path=').then(response => sub);

    }
}