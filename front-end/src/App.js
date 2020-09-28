import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import AddTutorial from "./components/add-tutorial.component";
import ListTutorials from "./components/list-tutorial.component";
import Tutorial from "./components/tutorial.component";

class App extends Component {
  render() {
    return(
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/" className="navbar-brand">
            Kira
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/tutorials"} className="nav-link">
                Tutorials
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Add
              </Link>
            </li>
          </div>
        </nav>
        <div className="container mt-3">
          <Switch>
            <Route path="/tutorials/:id" component={Tutorial} />
            <Route exact path="/add" component={AddTutorial}></Route>
            <Route axact path={["/","/tutorials"]} component={ListTutorials}></Route>
          </Switch>
        </div>
      </div>
    )
  }
}

export default App;