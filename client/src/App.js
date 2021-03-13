import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./views/Home";
import Login from './views/Login';
import Signup from './views/Signup';
import Quiz from './views/Quiz';
import Search from './views/Search';
import Header from './components/Header';

const App = () => {
  const sections = [
    { title: 'Quiz', url: '/quiz' },
    { title: 'Search', url: '/search' },
    { title: 'Login', url: '/login' },
    { title: 'Signup', url: '/signup' },
  ];

  return (
    <div className="App">
      <Header title="Mixer" sections={sections} />
      <Switch>
        <Route exact path="/"><Home /></Route>
        <Route path="/quiz"><Quiz /></Route>
        <Route path="/search"><Search /></Route>
        <Route path="/login"><Login /></Route>
        <Route path="/signup"><Signup /></Route>
      </Switch >
    </div >
  );
}

export default App;
