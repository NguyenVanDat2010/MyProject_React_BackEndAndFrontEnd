import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import {Link} from "react-router-dom";

export default class ListTutorials extends Component{
    constructor(props){
        super(props);
        this.getAllTutorials = this.getAllTutorials.bind(this);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveTutorial = this.setActiveTutorial.bind(this);
        this.removeAllTutorials = this.removeAllTutorials.bind(this);
        this.searchTitle = this.searchTitle.bind(this);

        this.state = {
            tutorials:[],
            currentTutorial: null,
            currentIndex: -1,
            searchTitle: ""
        };
    }

    //The same with ngOnInit() method in Angular, 
    //This method will be start first when call to list-tutorial.component.js 
    componentDidMount() {
        this.getAllTutorials();
    }

    onChangeSearchTitle(e){
        const searchTitle = e.target.value;

        this.setState({
            searchTitle: searchTitle
        });
    }

    getAllTutorials(){
        TutorialDataService.getAll().then(res => {
            this.setState({
                tutorials: res.data
            });
            console.log(res.data);
        }).catch(rej => {
            console.log(rej);
        })
    }

    refreshList(){
        this.getAllTutorials();
        this.setState({
            currentTutorial: null,
            currentIndex: -1
        });
    }

    setActiveTutorial(tutorial, index){
        this.setState({
            currentTutorial: tutorial,
            currentIndex: index
        });
    }

    removeAllTutorials(){
        TutorialDataService.deleteAll().then(res => {
            console.log(res.data);
            this.refreshList();
        }).catch(rej => {
            console.log(rej);
        });
    }

    searchTitle(){
        TutorialDataService.findByTitle(this.state.searchTitle).then(res => {
            this.setState({
                tutorials: res.data
            })
            console.log(res.data);
        }).catch(rej => {
            console.log(rej);
        })
    }
    render(){
        const { searchTitle, tutorials, currentTutorial, currentIndex } = this.state;
        return(
            <div className="list row">
                <div className="col-md-8">
                    <div className="input-group mb-3">
                        <input
                            type="text" 
                            className="form-control"
                            placeholder="Search by title"
                            value={searchTitle}
                            onChange={this.onChangeSearchTitle}
                        />
                        <div className="input-group-append">
                            <button 
                                className="btn btn-outline-secondary" 
                                type="button" value={searchTitle} 
                                onClick={this.searchTitle}
                            >
                                Search
                            </button>
                        </div>
                    </div>
                </div>
                
                <div className="col-md-6">
                    <h4>Tutorials list</h4>
                    {/* Mệnh đề điều kiện if-else rút gọn: condition ? true : false */}
                    <ul className="list-group">
                        {tutorials && tutorials.map((tutorial, index)=>(
                            <li 
                                className={"list-group-item " + (index === currentIndex ? "active" : "")} 
                                onClick={() => this.setActiveTutorial(tutorial, index)} 
                                key={index}
                            >
                                {tutorial.title}
                            </li>
                        ))}
                    </ul>
                    <button className="m-3 btn btn-sm btn-danger" onClick={this.removeAllTutorials}>Remove all</button>
                </div>
                
                <div className="col-md-6">
                    {currentTutorial ? (
                        <div>
                            <h4>Tutorial</h4>
                            <div>
                                <label>
                                    <strong>Title:</strong>
                                </label>{" "}
                                {currentTutorial.title}
                            </div>
                            <div>
                                <label>
                                    <strong>Description:</strong>
                                </label>{" "}
                                {currentTutorial.description}
                            </div>
                            <div>
                                <label>
                                    <strong>Status:</strong>
                                </label>{" "}
                                {currentTutorial.published ? "Published" : "Pending"}
                            </div>
                            <Link 
                                to={"/tutorials/" + currentTutorial.id} 
                                className="badge badge-warning"
                            >
                                Edit
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <br/>
                            <p>Please click on a Tutorial...</p>
                        </div>
                    )}
                </div>
            </div>
        )
    }
}