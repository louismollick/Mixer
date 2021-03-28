<<<<<<< HEAD
import { useState, useEffect } from 'react';
import { Route, Switch } from "react-router-dom";
import Home from "./views/Home";
import Login from './views/Login';
import Signup from './views/Signup';
import Profile from './views/Profile';
import Inventory from './views/Inventory';
=======
import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./views/Home";
import Login from './views/Login';
import Quiz from './views/Quiz';
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
import Search from './views/Search';
import Header from './components/Header';

const App = () => {
<<<<<<< HEAD
  const [loggedIn, setLoggedIn] = useState(false);

  const sections = [
    { title: 'Inventory', url: '/inventory' },
    { title: 'Search', url: '/search' },
    { title: 'Login', url: '/login' },
    { title: 'Signup', url: '/signup' },
    { title: 'Profile', url: '/profile' }
  ];

  useEffect(() => {
    if (window.localStorage.getItem('token') != null)
      setLoggedIn(true);
  }, []);

  return (
    <div className="App">
      <Header title="Mixer" sections={sections} loggedIn={loggedIn} />
      <Switch>
        <Route exact path="/"><Home /></Route>
        <Route path="/inventory"><Inventory loggedIn={loggedIn}/></Route>
        <Route path="/search"><Search /></Route>
        <Route path="/login"><Login setLoggedIn={setLoggedIn} /></Route>
        <Route path="/signup"><Signup /></Route>
        <Route path="/profile"><Profile loggedIn={loggedIn} setLoggedIn={setLoggedIn} /></Route>
=======
  const sections = [
    { title: 'Quiz', url: '/quiz' },
    { title: 'Search', url: '/search' },
    { title: 'Login', url: '/login' },
  ];

  return (
    <div className="App">
      <Header title="Mixer" sections={sections} />
      <Switch>
        <Route exact path="/"><Home /></Route>
        <Route path="/quiz"><Quiz /></Route>
        <Route path="/search"><Search /></Route>
        <Route path="/login" component={Login}></Route>
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
      </Switch >
    </div >
  );
}

export default App;
