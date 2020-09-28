import http from "../http-common";

class TutorialDataService{
    getAll(){
        return http.get("/tutorials")
    }

    getById(id){
        return http.get(`/tutorials/${id}`);
    }

    createTutorial(data){
        return http.post("/tutorials", data);
    }

    updateTutorial(data){
        return http.put("/tutorials", data);
    }

    deleteTutorialById(id){
        return http.delete(`/tutorials/${id}`);
    }

    deleteAll(){
        return http.delete("/tutorials");
    }

    findByTitle(title){
        return http.get(`/tutorials?title=${title}`);
    }
}

export default new TutorialDataService();