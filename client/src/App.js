import { useState, useEffect } from 'react';
import { Route, Switch } from "react-router-dom";
import Home from "./views/Home";
import Login from './views/Login';
import Signup from './views/Signup';
import Profile from './views/Profile';
import Search from './views/Search';
import Header from './components/Header';

const App = () => {
  const [loggedIn, setLoggedIn] = useState(true);

  const sections = [
    { title: 'Home', url: '/' },
    { title: 'Login', url: '/login' },
    { title: 'Signup', url: '/signup' },
    { title: 'Profile', url: '/profile' }
  ];
  useEffect(() => {
    if (window.localStorage.getItem('token') == null)
      setLoggedIn(false);
  }, []);
  return (
    <div className="App">
      <Header title="Mixer" sections={sections} loggedIn={loggedIn} />
      <Switch>
        <Route exact path="/"><Home loggedIn={loggedIn} /></Route>
        <Route path="/search"><Search loggedIn={loggedIn} /></Route>
        <Route path="/login"><Login setLoggedIn={setLoggedIn} /></Route>
        <Route path="/signup"><Signup /></Route>
        <Route path="/profile"><Profile loggedIn={loggedIn} setLoggedIn={setLoggedIn} /></Route>
      </Switch>
    </div>
  );
}

export default App;